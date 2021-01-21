package cn.infocore.redis.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/21 15:06
 * @Description
 */
@RestController
@RequestMapping(value = "session")
public class SessionManagerController {

    @GetMapping("test")
    public void sessionTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> names = (List<String>) request.getSession().getAttribute("names");

        if (Objects.isNull(names)) {
            names = new ArrayList<>();
        }

        names.add("zhangsan");

        request.getSession().setAttribute("names", names);
        response.getWriter().println("names size : " + names.size());
        response.getWriter().println("session id : " + request.getSession().getId());
    }

    /**
     * 退出注销session
     * @param request
     * @param response
     */
    @GetMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.getWriter().println("logout success!!!");
    }
}
