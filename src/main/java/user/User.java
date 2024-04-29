package user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import command.BaseEntity;
import command.Repository;
import lombok.*;

import java.util.List;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class User extends BaseEntity<String> {
   private String PhoneNumber;
   private List<UUID> quizes;
   private UserState state;
   private Role role;
 @Builder

    public User(String id, String phoneNumber, List<UUID> quizes, UserState state, Role role) {
        super(id);
        PhoneNumber = phoneNumber;
        this.quizes = quizes;
        this.state = state;
        this.role = role;
    }
}
