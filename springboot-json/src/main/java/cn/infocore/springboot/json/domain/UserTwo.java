package cn.infocore.springboot.json.domain;

import com.fasterxml.jackson.annotation.*;
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
public class UserTwo implements Serializable {

    private static final long serialVersionUID = 8598859435314432825L;

    /**
     * 对于UserTwo对象，某些情况下只返回userName属性就行，而某些情况下需要返回全部属性。 因此User对象可以定义成如下.
     *
     * User定义了两个接口类，一个为userNameView，另外一个为AllUserFieldView继承了userNameView接口。这两个接口代表了两个序列化组的名称。
     * 属性userName使用了@JsonView(UserNameView.class)，而剩下属性使用了@JsonView(AllUserFieldView.class)。
     */
    public interface UserNameView{}
    public interface AllFieldView extends UserNameView{}

    @JsonView(value = AllFieldView.class)
    @JsonIgnore
    private Long id;

    @JsonView(value = UserNameView.class)
    private String username;

    @JsonView(value = AllFieldView.class)
    private String name;
    @JsonView(value = AllFieldView.class)
    private String password;
    @JsonView(value = AllFieldView.class)
    private Integer age;
    @JsonView(value = AllFieldView.class)
    private String address;
    @JsonView(value = AllFieldView.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date birthday;
}
