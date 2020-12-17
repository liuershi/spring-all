package cn.infocore.springboot.json.config;

import cn.infocore.springboot.json.domain.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/17 20:32
 * @Description 自定义序列化机制
 */
public class MyUserSerializable extends JsonSerializer<User> {

    /**
     * 这里我们只序列化username属性，并且以 user-name 的形式呈现
     * @param user
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("user-name", user.getUsername());
        jsonGenerator.writeEndObject();
    }
}
