package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 21.06.2019
 */
public class UserUpdateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        User user = logic.findById(new User(id));
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>CreateUser</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit' method='post'>"
                + "Name : <input type='text' name='name' value='" + user.getName() + "'/><br/>"
                + "Login : <input type='text' name='login' value='" + user.getLogin() + "'/><br/>"
                + "Email : <input type='text' name='email' value='" + user.getEmail() + "'/><br/>"
                + "<input type='hidden' name='id' value='" + id + "'/>"
                + "<input type='submit'>"
                + "</form>"
                + "<br/>"
                + "<form action='" + req.getContextPath() + "/list' method='get'>"
                + "<input type='submit' value=\"User's list\"/></form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        logic.update(new User(id, name, login, email));
        doGet(req, resp);
    }
}