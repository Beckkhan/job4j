package ru.job4j.generic;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserStoreTest {
    @Test
    public void whenCreateDatabase() {
        SimpleArray<User> database = new SimpleArray<>(15);
        UserStore users = new UserStore(database);
        User user = new User("IVAN");
        users.add(user);
        User result = users.findById("IVAN");
        assertThat(result, is(user));
    }
    @Test
    public void whenTryToReplace() {
        SimpleArray<User> database = new SimpleArray<>(15);
        UserStore users = new UserStore(database);
        User first = new User("Fdksi");
        User second = new User("Sasdo");
        User third = new User("Asdwq");
        User replacer = new User("Sasdo");
        users.add(first);
        users.add(second);
        users.add(third);
        boolean result = users.replace("Sasdo", replacer);
        assertThat(result, is(true));
        User user = users.findById("Sasdo");
        assertThat(user, is(replacer));
    }

    @Test
    public void whenDeleteUser() {
        SimpleArray<User> database = new SimpleArray<>(15);
        UserStore users = new UserStore(database);
        User first = new User("Fdksi");
        User second = new User("Sasdo");
        User third = new User("Asdwq");
        users.add(first);
        users.add(second);
        users.add(third);
        users.delete("Fdksi");
        assertThat(users.findById("Fdksi"), is(nullValue()));
    }
}
