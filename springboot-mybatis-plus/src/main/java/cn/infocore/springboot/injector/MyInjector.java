package cn.infocore.springboot.injector;

import cn.infocore.springboot.customizrmethod.CustomizeFindAllMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/8 19:38
 * @Description 集成DefaultSqlInjector，将要注入的方法包装在LIST中再返回（注意：不要直接继承AbstractSqlInjector，因为它自带的增删改查方法在DefaultSqlInjector中重写并注入了）
 */
public class MyInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        // 获取父类中以及集成进去的方法(这一步一定要，否则mapper自带的方法就丢失了，只注入了我们自定义的方法)
        List<AbstractMethod> methods = new ArrayList<>(super.getMethodList(mapperClass));

        // 集成自定义的方法
        methods.add(new CustomizeFindAllMethod());

        return methods;
    }
}
