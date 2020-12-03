package cn.infocore.springboot.source;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 16:34
 * @Description 复杂一点的源实体类，映射属性名不一致的情况
 */
@Data
@AllArgsConstructor
public class ComplexSource {
    private long id;
    private String username;
    private String passwd;
}
