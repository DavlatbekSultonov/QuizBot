package quiz;

import command.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QuizRepository extends BaseRepository<Quiz, UUID> {

    private  static final QuizRepository quizrepository = new QuizRepository();

    public static QuizRepository getInstance() {
        return quizrepository;
    }

    public Optional<Quiz> findByUserAndStatus(String id, QuizStatus quizStatus) {
        List<Quiz> all = findAll();

        return all.stream().filter(quiz -> quiz.getCreatorID().equals(id))
                .filter(quiz -> quiz.getStatus()==quizStatus)
                .findFirst();

    }

    public List<Quiz> findByUserId(String id) {

        return findAll().stream().filter(quiz -> quiz.getCreatorID().equals(id)).toList();

    }
}
