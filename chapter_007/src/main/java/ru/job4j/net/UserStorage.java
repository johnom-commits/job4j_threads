package ru.job4j.net;

import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserStorage {
    private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.put(user.getId(), user) != null;
    }

    public synchronized boolean update(int id, User user) {
        return map.replace(id, map.get(id), user);
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId()) != null;
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User userFrom = map.get(fromId);
        User userTo = map.get(toId);
        int amountFrom = userFrom.getAmount();
        userFrom.setAmount(amountFrom -= amount);
        int amountTo = userTo.getAmount();
        userTo.setAmount(amountTo += amount);
    }
}
