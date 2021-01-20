package cn.infocore.excel.myenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:44
 * @Description
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
    WOMAN(0, "女");

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
