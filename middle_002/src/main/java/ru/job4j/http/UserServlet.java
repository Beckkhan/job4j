package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 23.06.2019
 */
public class UserServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    private final Map<String, Function<User, String>> actions = new HashMap<>();

    {
        actions.put("add", logic::add);
        actions.put("update", logic::update);
        actions.put("delete", logic::delete);
    }

    private User buildUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        if (id == null) {
            id = "";
        }
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        return new User(id, name, login, email);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> users = this.logic.findAll();
        if (users.size() == 0) {
            writer.append("Storage is empty.");
        } else {
            users.forEach(user -> {
                writer.append(user.toString() + System.lineSeparator());
            });
        }
        writer.flush();   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (actions.containsKey(action)) {
            User user = buildUser(req);
            resp.setContentType("text/html");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(actions.get(action).apply(user));
            writer.flush();
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}