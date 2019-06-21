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
public class UserCreateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>CreateUser</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post'>"
                + "Name : <input type='text' name='name'/><br/>"
                + "Login : <input type='text' name='login'/><br/>"
                + "Email : <input type='text' name='email'/><br/>"
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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        logic.add(new User(name, login, email));
        doGet(req, resp);
    }
}