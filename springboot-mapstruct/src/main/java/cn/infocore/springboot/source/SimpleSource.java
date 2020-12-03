package cn.infocore.springboot.source;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 14:42
 * @Description
 */
@Data
@AllArgsConstructor
public class SimpleSource {
    private String name;
    private String description;
    private Integer age;
}