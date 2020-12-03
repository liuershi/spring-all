package cn.infocore.springboot.description;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 16:33
 * @Description 复杂一点的目的实体类，映射属性名不一致的情况
 */
@Data
@AllArgsConstructor
public class ComplexDestination {
    private String id;
    private String name;
    private String password;
}
