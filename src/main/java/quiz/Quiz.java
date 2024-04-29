package quiz;

import command.BaseEntity;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;

import java.util.List;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Quiz extends BaseEntity<UUID> {
    private List<Poll> poll;
    private String name;
    private QuizStatus status;
    private String creatorID;

    @Builder
    public Quiz(UUID uuid, List<Poll> poll, String name, QuizStatus status, String creatorID) {
        super(uuid);
        this.poll = poll;
        this.name = name;
        this.status = status;
        this.creatorID = creatorID;
    }
}
