package cn.infocore.springboot.description;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 14:43
 * @Description
 */
@Data
@AllArgsConstructor
public class SimpleDestination {
    private String name;
    private String description;
    private Integer age;
}
