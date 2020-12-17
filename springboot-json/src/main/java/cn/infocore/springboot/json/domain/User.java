package cn.infocore.springboot.json.domain;

import cn.infocore.springboot.json.config.MyUserDeserializable;
import cn.infocore.springboot.json.config.MyUserSerializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/17 19:54
 * @Description
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
/**
 * @JsonIgnoreProperties: 作用在类上，同@JsonIgnore作用一样，只是它可能忽略一组属性
 * @JsonSerialize: 序列化注解，使用自定义序列化注解类（MyUserSerializable）
 * @JsonDeserialize: 反序列化注解，使用自定义反序列化注解类（MyUserDeserializable）
 */
@JsonIgnoreProperties(value = {"age", "address"})
//@JsonSerialize(using = MyUserSerializable.class)
//@JsonDeserialize(using = MyUserDeserializable.class)
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User implements Serializable {

    private static final long serialVersionUID = 358691188507843311L;

    private Long id;
    private String username;
    private String name;

    /**
     * @JsonIgnore: 作用在属性上，用来忽略此属性
     */
    @JsonIgnore
    private String password;
    private Integer age;
    private String address;

    /**
     * @JsonProperty: 可以为JSON指定一个key别名
     * @JsonFormat: 用于日期格式化
     */
    @JsonProperty(value = "bir")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date birthday;
}
