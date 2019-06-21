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
public class UsersServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder("<table border='1' width='100%' cellpadding='5'>");
        sb.append("<tr><td>Id</td><td>Name</td><td>Login</td><td>Email</td><td>Create Date</td></tr>");
        for (User user : logic.findAll()) {
            sb.append("<tr>"
                    + "<td>" + user.getId() + "</td>"
                    + "<td>" + user.getName() + "</td>"
                    + "<td>" + user.getLogin() + "</td>"
                    + "<td>" + user.getEmail() + "</td>"
                    + "<td>" + user.getCreateDate() + "</td>"
                    + "<td><form action='" + req.getContextPath() + "/edit'"
                    + "method='get'><input type='hidden' name='id' value='" + user.getId() + "'/>"
                    + "<input type='submit' value='edit'/></form>" + "</td>"
                    + "<td><form action='" + req.getContextPath() + "/list'"
                    + "method='post'><input type='hidden' name='id' value='" + user.getId() + "'/>"
                    + "<input type='submit' value='delete'/></form>" + "</td>"
                    + "</tr>");
        }
        sb.append("</table>");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Users list</title>"
                + "</head>"
                + "<body>"
                + sb.toString()
                + "<br/><form action='" + req.getContextPath() + "/create' method='get'>"
                + "<input type='submit' value='Add user'/>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (!id.isEmpty()) {
            logic.delete(new User(id));
        }
        this.doGet(req, resp);
    }
}