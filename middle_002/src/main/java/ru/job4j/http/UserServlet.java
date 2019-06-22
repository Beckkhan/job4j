package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 22.06.2019
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
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (actions.containsKey(action)) {
            User user = buildUser(req);
            actions.get(action).apply(user);
            resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}