package ru.job4j.concurrent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindIndexTest {
    @Test
    public void whenMoreTenUnit() {
        User user = User.of("John");
        User user2 = User.of("John2");
        User user3 = User.of("John3");
        User user4 = User.of("John4");
        User user5 = User.of("John5");
        User user6 = User.of("John6");
        User user7 = User.of("John7");
        User user8 = User.of("John8");
        User user9 = User.of("John9");
        User user10 = User.of("John10");
        User user11 = User.of("John11");
        User user12 = User.of("John12");
        User user13 = User.of("John13");
        User user14 = User.of("John14");
        User[] array = {user, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14};

        FindIndex<User> findIndex = new FindIndex<>(array, user5);
        int index = findIndex.invoke();
        assertEquals(4, index);
    }

    @Test
    public void whenOneUnit() {
        User user = User.of("John");
        User[] array = {user};

        FindIndex<User> findIndex = new FindIndex<>(array, user);
        int index = findIndex.invoke();
        assertEquals(0, index);
    }

    @Test
    public void whenTenUnit() {
        User user = User.of("John");
        User user2 = User.of("John2");
        User user3 = User.of("John3");
        User user4 = User.of("John4");
        User user5 = User.of("John5");
        User user6 = User.of("John6");
        User user7 = User.of("John7");
        User user8 = User.of("John8");
        User user9 = User.of("John9");
        User[] array = {user, user2, user3, user4, user5, user6, user7, user8, user9};

        FindIndex<User> findIndex = new FindIndex<>(array, user5);
        int index = findIndex.invoke();
        assertEquals(4, index);
    }
}