package cn.infocore.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/8 19:40
 * @Description 自定义mapper接口，所有的mapper都继承它，这样就能继承我们自定义的方法了
 */
public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 自定义一个查询方法，所有mapper继承后都能继承这个方法
     * @return
     */
    List<T> customizeFindAll();
}
