package ru.job4j.net;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {
    UserStorage storage = new UserStorage();
    User user1 = new User(1, 1000);
    User user2 = new User(2, 2000);

    @Before
    public void initStorage() {
        storage.add(user1);
        storage.add(user2);
    }

    @Test
    public void whenAddNewUsers() {
        assertEquals(2, storage.size());
    }

    @Test
    public void whenTransferBetweenUsers() throws Exception {
        storage.transfer(1, 2, 330);
        assertEquals(670, user1.getAmount());
        assertEquals(2330, user2.getAmount());
    }
}
