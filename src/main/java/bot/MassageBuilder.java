package bot;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonPollType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import quiz.Quiz;
import user.Colback;
import user.User;

import javax.security.auth.callback.Callback;
import java.util.List;
import java.util.concurrent.Callable;

public class MassageBuilder {
    private final InlineBuilder builder = new InlineBuilder();

    public SendMessage getMainMenu(User user){
        SendMessage sendMessage = new SendMessage(user.getId(), "bu bot sizga quiz yaratishda yordam beradi");


        InlineKeyboardMarkup build = builder.build((List.of(
                List.of(Pair.of("Quiz yaratish", Colback.CREAT_QUIZ)),
                List.of(Pair.of("Quizlarni ko'rish", Colback.MY_QUIZ)))));
        sendMessage.setReplyMarkup(build);
        return sendMessage;

    }
    public SendMessage getWelcomeMassage(User user){
        SendMessage sendMessage = new SendMessage(user.getId(), "Botimizga xush kelibsiz");
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        return sendMessage;
    }

    public SendMessage creatQuiz(Update update , User user){
        return new SendMessage(user.getId(), "Quiz nomini kiriting");

    }


    public SendMessage requestQuiz(User user) {
        SendMessage sendMessage = new SendMessage(user.getId(), "menga Poll yuboring");
        KeyboardButton button = new KeyboardButton("savol yarating");

        button.setRequestPoll(KeyboardButtonPollType.builder().type("Quiz").build());
        KeyboardRow row = new KeyboardRow(List.of(button));
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(List.of(row));
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;

    }

    public SendMessage showQuizes(List<Quiz> quizzes, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getId());
        String massage ="";

        for (int i = 0; i < quizzes.size(); i++) {
            massage += i+". " + quizzes.get(i).getName() +  " - " + quizzes.get(i).getStatus();
        }
        sendMessage.setText(massage);

        return sendMessage;
    }
}
