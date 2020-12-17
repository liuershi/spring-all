package cn.infocore.springboot.json.controller;

import cn.infocore.springboot.json.domain.User;
import cn.infocore.springboot.json.domain.UserTwo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/17 19:56
 * @Description
 */

/**
 * @RestController： 将对象序列化成json对象默认使用的ObjectMapper实现的，使用自定义的ObjectMapper实现对日期格式的自定义定制
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private ObjectMapper mapper;

    private static List<User> users = new ArrayList<>();
    private static List<UserTwo> userTwos = new ArrayList<>();

    static {
        users.add(new User(1L, "zhangsan", "张三", "111111", 25, "浙江杭州", new Date()));
        users.add(new User(2L, "lisi", "李四", "222222", 20, "青海", new Date()));
        users.add(new User(3L, "wangwu", "王五", "333333", 21, "上海", new Date()));
        users.add(new User(4L, "zhaoliu", "赵六", "444444", 32, "北京", new Date()));
        users.add(new User(5L, "caocao", "曹操", "888888", 56, "许昌", new Date()));

        userTwos.add(new UserTwo(1L, "zhangsan", "张三", "111111", 25, "浙江杭州", new Date()));
        userTwos.add(new UserTwo(2L, "lisi", "李四", "222222", 20, "青海", new Date()));
        userTwos.add(new UserTwo(3L, "wangwu", "王五", "333333", 21, "上海", new Date()));
        userTwos.add(new UserTwo(4L, "zhaoliu", "赵六", "444444", 32, "北京", new Date()));
        userTwos.add(new UserTwo(5L, "caocao", "曹操", "888888", 56, "许昌", new Date()));
    }

    @GetMapping(value = "/list/{id}")
    public User getById(@PathVariable("id") long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * 使用 writeValueAsString 将对象序列化成字符串
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping(value = "serializable/{id}")
    public String serializable(@PathVariable("id")long id) throws JsonProcessingException {
        for (User user : users) {
            if (user.getId() == id) {
                // 例：{"id":5,"username":"caocao","name":"曹操","password":"888888","age":56,"address":"许昌","birthday":"2020-12-17 20:11:34"}
                return mapper.writeValueAsString(user);
            }
        }
        return null;
    }

    /**
     * 当采用树遍历的方式时，JSON被读入到JsonNode对象中，可以像操作XML DOM那样读取JSON。
     *
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping(value = "deserializable/{id}")
    public String deserializable(@PathVariable("id")long id) throws JsonProcessingException {
        for (User user : users) {
            if (user.getId() == id) {
                // 例：{"id":5,"username":"caocao","name":"曹操","password":"888888","age":56,"address":"许昌","birthday":"2020-12-17 20:11:34"}
                String serializable = mapper.writeValueAsString(user);

                // 反序列化
                JsonNode jsonNode = mapper.readTree(serializable);
                String username = jsonNode.get("username").asText();
                return new StringBuilder(jsonNode.get("id").asText()).append("-").append(username)
                        .append("-").append(jsonNode.get("age").asText()).append("-")
                        .append(jsonNode.get("address").asText()).toString();
            }
        }
        return null;
    }

    /**
     * 我们也可以将Java对象和JSON数据进行绑定
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping(value = "readJsonAsObject/{id}")
    public User readJsonAsObject(@PathVariable("id") long id) throws JsonProcessingException {
        for (User user: users) {
            if (user.getId() == id){
                String serializable = mapper.writeValueAsString(user);

                User value = mapper.readValue(serializable, User.class);
                return value;
            }
        }
        return null;
    }

    /**
     * 测试自定义序列化机制
     * @return
     */
    @GetMapping(value = "customize/deserializable")
    public User testCustomizeDeserializable() throws JsonProcessingException {
        String string = "{\"id\":5,\"user-name\":\"caocao\",\"name\":\"曹操\"}";
        User user = mapper.readValue(string, User.class);
        return user;
    }




    //==========================================================================
    //=============================     测试@JsonView     =======================
    //==========================================================================

    /**
     * Spring中Controller方法允许使用@JsonView指定一个组名，被序列化的对象只有在这个组的属性才会被序列化
     * @param id
     * @return
     */
    @GetMapping("list/username/{id}")
    @JsonView(value = UserTwo.UserNameView.class)
    public UserTwo getUserUsername(@PathVariable("id")long id){
        for (UserTwo userTwo: userTwos){
            if (userTwo.getId() == id){
                return userTwo;
            }
        }
        return null;
    }
    @GetMapping("list/all/{id}")
    @JsonView(value = UserTwo.AllFieldView.class)
    public UserTwo getUser(@PathVariable("id")long id){
        for (UserTwo userTwo: userTwos){
            if (userTwo.getId() == id){
                return userTwo;
            }
        }
        return null;
    }


    //==========================================================================
    //=============================     集合反序列化     =========================
    //==========================================================================

    /**
     * 可以使用@RequestBody将提交的JSON自动映射到方法参数上
     * @param users
     * @return
     */
    @GetMapping(value = "/size")
    public int getUsersSize(@RequestBody List<UserTwo> users) {
        return users.size();
    }

    /**
     * error demo
     * 请求该方法抛出：java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to cn.infocore.springboot.json.domain.UserTwo
     * 原因是在运行时刻，集合的泛型被擦除。
     */
    @RequestMapping("customize/error")
    @ResponseBody
    public String customizeError() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"username\":\"zhangsan\",\"age\":26},{\"username\":\"scott\",\"age\":27}]";
        List<UserTwo> list = mapper.readValue(jsonStr, List.class);
        String msg = "";
        for (UserTwo user : list) {
            msg += user.getUsername();
        }
        return msg;
    }

    /**
     * correct demo
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping("customize/correct")
    @ResponseBody
    public String customizeCorrect() throws JsonParseException, JsonMappingException, IOException {
        String jsonStr = "[{\"username\":\"zhangsan\",\"age\":26},{\"username\":\"scott\",\"age\":27}]";

        JavaType type = mapper.getTypeFactory().constructParametricType(List.class, UserTwo.class);
        List<UserTwo> list = mapper.readValue(jsonStr, type);
        StringBuilder builder = new StringBuilder();
        for (UserTwo userTwo : list){
            builder.append(userTwo.getUsername()).append(" | ");
        }
        return builder.toString();
    }
}
