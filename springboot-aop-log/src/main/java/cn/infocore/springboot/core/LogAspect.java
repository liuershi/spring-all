package cn.infocore.springboot.core;

import cn.infocore.springboot.domain.SysLogDO;
import cn.infocore.springboot.service.SyslogService;
import cn.infocore.springboot.util.HttpContextUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 19:33
 * @Description 定义一个LogAspect类，使用@Aspect标注让其成为一个切面，切点为使用@Log注解标注的方法，使用@Around环绕通知
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SyslogService service;

    @Pointcut("@annotation(cn.infocore.springboot.core.Log)")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        // 开始时间
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 执行方法花费时间(毫秒)
        long time = System.currentTimeMillis() - start;
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();

        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        SysLogDO sysLogDO = new SysLogDO();
        if (log != null) {
            // 注解所描述的操作
            sysLogDO.setOperation(log.value());
        }
        // 请求方法
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogDO.setMethod(className + "." + methodName);

        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        if (args != null && parameterNames != null) {
            List<String> list = Arrays.asList(parameterNames);
            list.forEach(name -> sysLogDO.setParams(name + ":" + args[list.indexOf(name)]));
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLogDO.setIp(request.getRemoteAddr());

        // 模拟一个用户名
        sysLogDO.setUsername("zhangsan");
        sysLogDO.setTime(time);
        sysLogDO.setCreateTime(new Date());
        // 保存系统日志
        service.save(sysLogDO);
    }
}
