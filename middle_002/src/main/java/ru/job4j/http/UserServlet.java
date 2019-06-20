package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 20.06.2019
 */
public class UserServlet extends HttpServlet {
    /**
     * Validate instance.
     */
    private final Validate logic = ValidateService.getInstance();

    private final Map<String, Function<User, String>> actions = new HashMap<>();

    {
        actions.put("add", logic::add);
        actions.put("update", logic::update);
        actions.put("delete", logic::delete);
    }

    private User buildUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        return new User(id, name, login, email);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        if (id == null) {
            writer.append(logic.findAll());
        } else {
            writer.append(logic.findById(buildUser(req)));
        }
        writer.flush();
    }

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