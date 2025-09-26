package streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainUser {

    public static void main(String[] args) {
        User user1 = new User("1", "John1");
        User user2 = new User("2", "Jane");
        User user3 = new User("3", "Jack");
        User user4 = new User("4", "John1");

        Stream.of(user1, user2, user3, user4)
                .filter(user -> "admin".equals(user.getRole()))
                .forEach(admin -> System.out.println(admin.getName()));

        final Map<String, String> nameToRole = Stream.of(user1, user2, user3, user4)
                .collect(Collectors.toMap(User::getName, User::getRole));
        System.out.println(nameToRole);
        System.out.println("------------".repeat(2));

        final List<User> sortedByName = Stream.of(user1, user2, user3, user4)
                .sorted(Comparator.comparing(User::getName))
                .toList();
        System.out.println(sortedByName);
        System.out.println("------------".repeat(2));

        final Map<String, Long> groupedByNameAndCount = Stream.of(user1, user2, user3, user4)
                .collect(Collectors.groupingBy(User::getRole, Collectors.counting()));
        System.out.println(groupedByNameAndCount);

    }

}
