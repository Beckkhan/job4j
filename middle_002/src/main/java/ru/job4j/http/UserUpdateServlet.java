package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 22.06.2019
 */
public class UserUpdateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/edit.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        logic.update(new User(id, name, login, email));
        resp.sendRedirect(String.format("%s/edit.jsp?id=%s", req.getContextPath(), id));
    }
}