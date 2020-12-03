package cn.infocore.springboot.convert;

import cn.infocore.springboot.description.ComplexDestination;
import cn.infocore.springboot.description.SimpleDestination;
import cn.infocore.springboot.source.ComplexSource;
import cn.infocore.springboot.source.SimpleSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
public interface ComplexSourceDestinationMapper {

    /**
     * 配合lombok时必须使用，为了获得 MapStruct 帮我们自动生成的 SimpleSourceDestinationMapper 实现类的对象
     */
    ComplexSourceDestinationMapper INSTANCE = Mappers.getMapper(ComplexSourceDestinationMapper.class);

    /**
     * 从 ComplexSource 到 ComplexDestination 的转换接口
     * @param source
     * @return ComplexDestination
     */
    @Mappings({
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "passwd", target = "password")
    })
    ComplexDestination sourceToDestination(ComplexSource source);

    /**
     * 从 ComplexDestination 到 ComplexSource 的转换接口
     * @param destination
     * @return ComplexSource
     */
    @Mappings({
            @Mapping(source = "name", target = "username"),
            @Mapping(source = "password", target = "passwd")
    })
    ComplexSource destinationToSource(ComplexDestination destination);
}
