package ru.job4j.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 02.07.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletsTest {

    @Test
    public void whenAddUserThenPutInStore() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("User Name");
        when(req.getParameter("login")).thenReturn("User Login");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("User Name"));
        assertThat(validate.findAll().iterator().next().getLogin(), is("User Login"));
    }

    @Test
    public void whenDeleteUserThenStoreIsEmpty() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        User user = new User("ID", "User Name", "User Login", "Password", "user@gmail.com");
        validate.add(user);
        assertThat(validate.findAll().size(), is(1));
        when(req.getParameter("id")).thenReturn("0");
        new UsersServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }

    @Test
    public void whenUpdateUserThenNewDataInStore() throws ServletException, IOException, ServletException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        validate.add(new User("ID", "User Name", "User Login", "Password", "user@gmail.com"));
        User user = validate.findAll().iterator().next();
        assertThat(user.getId(), is("0"));
        assertThat(user.getName(), is("User Name"));
        assertThat(user.getLogin(), is("User Login"));
        assertThat(user.getPassword(), is("Password"));
        assertThat(user.getEmail(), is("user@gmail.com"));
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("New User Name");
        when(req.getParameter("oldLogin")).thenReturn("User Login");
        when(req.getParameter("oldPassword")).thenReturn("Password");
        when(req.getParameter("login")).thenReturn("New User Login");
        when(req.getParameter("password")).thenReturn("New Password");
        when(req.getParameter("email")).thenReturn("user@icloud.com");
        when(req.getParameter("role")).thenReturn("USER");
        new UserUpdateServlet().doPost(req, resp);
        User next = validate.findAll().iterator().next();
        assertThat(next.getName(), is("New User Name"));
        assertThat(next.getLogin(), is("New User Login"));
        assertThat(next.getPassword(), is("New Password"));
        assertThat(next.getEmail(), is("user@icloud.com"));
        assertThat(next.getRole(), is(Role.USER));
    }
}