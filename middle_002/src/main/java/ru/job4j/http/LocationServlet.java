package ru.job4j.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 10.07.2019
 */
public class LocationServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> countries = logic.getCountries();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCountries = objectMapper.writeValueAsString(countries);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonCountries);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String country = req.getParameter("country");
        List<String> result = logic.getCitiesByCountry(country);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCities = objectMapper.writeValueAsString(result);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonCities);
        writer.flush();
    }
}