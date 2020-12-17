package cn.infocore.springboot.json.config;

import cn.infocore.springboot.json.domain.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/17 20:38
 * @Description 自定义反序列化机制
 */
public class MyUserDeserializable extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        String userName = treeNode.get("user-name").asText();
        User user = new User().setUsername(userName);
        return user;
    }
}
