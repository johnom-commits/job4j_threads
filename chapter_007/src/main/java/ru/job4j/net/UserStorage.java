package ru.job4j.net;

import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ThreadSafe
public final class UserStorage {
    private final Map<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        Objects.requireNonNull(user);
        return map.put(user.getId(), user) != null;
    }

    public synchronized boolean update(int id, User user) {
        Objects.requireNonNull(user);
        return map.replace(id, map.get(id), user);
    }

    public synchronized boolean delete(User user) {
        Objects.requireNonNull(user);
        return map.remove(user.getId()) != null;
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void transfer(int fromId, int toId, int amount) throws Exception {
        User userFrom = map.get(fromId);
        if (userFrom == null) {
            throw new NullPointerException("Передан не существующий идентификатор пользователя.");
        }
        int amountFrom = userFrom.getAmount();
        if (amountFrom < amount) {
            throw new Exception("Недостаточно суммы на счёте!");
        }
        User userTo = map.get(toId);
        int amountTo = userTo.getAmount();
        userFrom.setAmount(amountFrom -= amount);
        userTo.setAmount(amountTo += amount);
    }
}
