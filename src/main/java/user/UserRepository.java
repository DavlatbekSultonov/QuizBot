package user;

import command.BaseRepository;
import command.Repository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository extends BaseRepository<User,String> {

    private static final UserRepository repository = new UserRepository();


    public static UserRepository getInstance() {
        return repository;
    }

}
