package ru.job4j.js;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 07.07.2019
 */
public class JsonController extends HttpServlet {

    ConcurrentHashMap<Integer, Person> store = new ConcurrentHashMap();

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter printWriter = resp.getWriter();
        List<Person> resultList = new ArrayList<>(store.values());
        ObjectMapper mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(resultList);
        printWriter.append(toJson);
        printWriter.flush();
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String string;
        while ((string = br.readLine()) != null) {
            sb.append(string);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(sb.toString(), Person.class);
        store.put((store.size() + 1), person);
        String backJson = objectMapper.writeValueAsString(store);
        resp.setContentType("text/json");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        System.out.println(backJson);
        printWriter.append(backJson);
        printWriter.flush();
    }
}