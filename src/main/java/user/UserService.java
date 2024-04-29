package user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService service=new UserService();
    private final UserRepository repository = UserRepository.getInstance();

    public static UserService getInstance() {
        return service;
    }

    public User getOrElseCreat(Long chatId) {
        Optional<User> optionalUser = repository.findById(chatId.toString());
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        User user = User.builder().id(chatId.toString())
                .quizes(new ArrayList<>())
                .role(Role.USER)
                .state(UserState.NEW)
                .build();
        repository.creat(user);
        return user;
    }
}
