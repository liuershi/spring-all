package cn.infocore.springboot.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/8 20:47
 * @Description 自定义枚举类，与数据库表字段对应
 */
@Getter
public enum SexEnum {
    /**
     * 性别枚举：男
     */
    MAN(1, "男"),
    /**
     * 性别枚举：女
     */
    WOMAN(2, "女");

    @EnumValue
    private Integer value;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonValue
    private String desc;

    @Override
    public String toString() {
        return desc;
    }
}
