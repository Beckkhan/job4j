package ru.job4j.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 23.06.2019
 */
public class UsersServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder(
                "<table style=\"border: 1p solid black;\" cellpading=\"1\" border=\"1\">"
        );
        if (logic.findAll().size() == 0) {
            sb.append("Storage is empty.");
        } else {
            sb.append(
                    "<tr>"
                            + "<th>Id</th>"
                            + "<th>Name</th>"
                            + "<th>Login</th>"
                            + "<th>E-Mail</th>"
                            + "<th>Actual Date</th>"
                            + "</tr>"
            );
            for (User user : logic.findAll()) {
                sb.append(
                        "<tr>"
                                + "<td>" + user.getId() + "</td>"
                                + "<td>" + user.getName() + "</td>"
                                + "<td>" + user.getLogin() + "</td>"
                                + "<td>" + user.getEmail() + "</td>"
                                + "<td>" + user.getCreateDate() + "</td>"
                                + "<td>" + "<form action='" + req.getContextPath() + "/edit'"
                                + "method='get'><input type='hidden' name='id' value='" + user.getId() + "'/>"
                                + "<input type='submit' value='Edit'/></form>" + "</td>"
                                + "<td>" + "<form action='" + req.getContextPath() + "/list'"
                                + "method='post'><input type='hidden' name='id' value='" + user.getId() + "'/>"
                                + "<input type='submit' value='Delete'/></form>" + "</td>"
                                + "</tr>");
            }
            sb.append("</table>");
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>List</title>"
                + "</head>"
                + "<body>"
                + sb.toString()
                + "<br/>"
                + "<form action='" + req.getContextPath() + "/create' method='get'>"
                + "<input type='submit' value='Create New User'/>"
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