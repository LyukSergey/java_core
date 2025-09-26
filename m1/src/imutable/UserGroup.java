package imutable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class UserGroup {

    private final String groupName;
    private final List<User> users;

    public UserGroup(String groupName, List<User> users) {
        this.groupName = groupName;
        List<User> deepCopy = new ArrayList<>();
        for (User user : users) {
            deepCopy.add(new User(user)); // Використовуємо конструктор копіювання
        }
        this.users = List.copyOf(deepCopy);
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getUsers() {
        // КРОК 2: Глибоке копіювання в геттері
        // Ми не можемо повернути посилання навіть на наші внутрішні об'єкти,
        // бо вони змінні. Потрібно повернути копії.
        return this.users.stream()
                .map(User::new) // Для кожного User створюємо новий User
                .collect(Collectors.toList());
    }
}
