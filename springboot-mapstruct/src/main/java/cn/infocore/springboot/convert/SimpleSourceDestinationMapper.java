package cn.infocore.springboot.convert;

import cn.infocore.springboot.description.SimpleDestination;
import cn.infocore.springboot.source.SimpleSource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 14:45
 * @Description
 */

/**
 * @Mapper: 声明它是一个 MapStruct Mapper 映射器
 */
@Mapper
public interface SimpleSourceDestinationMapper {

    /**
     * 配合lombok时必须使用，为了获得 MapStruct 帮我们自动生成的 SimpleSourceDestinationMapper 实现类的对象
     */
    SimpleSourceDestinationMapper INSTANCE = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    /**
     * 从 SimpleSource 到 SimpleDestination 的转换接口
     * @param source
     * @return SimpleDestination
     */
    SimpleDestination sourceToDestination(SimpleSource source);

    /**
     * 从 SimpleDestination 到 SimpleSource 的转换接口
     * @param destination
     * @return SimpleDestination
     */
    SimpleSource destinationToSource(SimpleDestination destination);
}
