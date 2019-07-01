package ru.job4j.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 7.0
 * @since 01.07.2019
 */
public class UserUpdateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        req.setAttribute("user", logic.findById(new User(id)));
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String oldLogin = req.getParameter("oldLogin");
        String oldPassword = req.getParameter("oldPassword");
        String newLogin = req.getParameter("login");
        String newPassword = req.getParameter("password");
        String email = req.getParameter("email");
        String strRole = req.getParameter("role");
        Role role = strRole.equals("ADMIN") ? Role.ADMIN : Role.USER;
        logic.update(new User(id, name, newLogin, newPassword, email, role));
        if (!oldLogin.equals(newLogin) || !oldPassword.equals(newPassword)) {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("users", ValidateService.getInstance().findAll());
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }
}