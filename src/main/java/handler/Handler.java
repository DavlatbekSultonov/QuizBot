package handler;

import bot.MassageBuilder;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import quiz.Quiz;
import quiz.QuizRepository;
import quiz.QuizStatus;
import user.User;
import user.UserState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Handler {
    private final MassageBuilder massageBuilder=new MassageBuilder();
     private final DefaultAbsSender sender;
     private final QuizRepository quizRepository = new QuizRepository();
    public Handler(DefaultAbsSender sender){
        this.sender=sender;
    }

    public void handler1(Update update, User user){
        switch (user.getState()){
            case NEW -> requestPhonenumber(update,user);
            case MAIN -> handleMain(update,user);
            case INTER_QUIZ_NAME -> creatQuiz(update,user);
        }

    }
    @SneakyThrows
    private void creatQuiz(Update update, User user) {
        if (update.getMessage().hasText()) {

            String quizName = update.getMessage().getText();
            Optional<Quiz> optionalQuiz = quizRepository.findByUserAndStatus(user.getId(), QuizStatus.IN_PROGRES);

            if (quizName.equals("/done")){
                optionalQuiz.get().setStatus(QuizStatus.FENISHED);
                user.setState(UserState.MAIN);
                sender.execute(massageBuilder.getWelcomeMassage(user));
                sender.execute(massageBuilder.getMainMenu(user));
                return;
            }
            if (optionalQuiz.isPresent()){
                return;
            }
            Quiz build = Quiz.builder().
                    name(quizName)
                    .poll(new ArrayList<>())
                    .creatorID(user.getId())
                    .status(QuizStatus.IN_PROGRES)
                    .build();
            quizRepository.creat(build);
        }
        if (update.getMessage().hasText()) {
            Poll poll = update.getMessage().getPoll();
            Quiz quiz = quizRepository.findByUserAndStatus(user.getId(), QuizStatus.IN_PROGRES).get();
            List<Poll > polls =quiz.getPoll();
            polls.add(poll);
        }
        SendMessage sendMessage = massageBuilder.requestQuiz(user);
        sender.execute(sendMessage);
    }

    @SneakyThrows
    private void handleMain(Update update, User user) {
        if (update.hasMessage()){
            sender.execute(massageBuilder.getMainMenu(user));
        }else {
            switch (update.getCallbackQuery().getData()){
                case "1" -> {
                    sender.execute(massageBuilder.creatQuiz(update,user));
                    user.setState(UserState.INTER_QUIZ_NAME);
                }
                case "2" -> showMyQuiz(user);
            }
        }
    }
  @SneakyThrows
    private void showMyQuiz(User user) {

        List<Quiz> quizzes = quizRepository.findByUserId(user.getId());
            sender.execute(massageBuilder.showQuizes(quizzes,user));
    }

    @SneakyThrows
    private void requestPhonenumber(Update update,User user) {
     if (update.getMessage().hasContact()) {
         String phoneNumber = update.getMessage().getContact().getPhoneNumber();
         user.setPhoneNumber(phoneNumber);
         user.setState(UserState.MAIN);
         sender.execute(massageBuilder.getWelcomeMassage(user));
         sender.execute(massageBuilder.getMainMenu(user));

     } else {

         SendMessage sendMessage = new SendMessage(user.getId(), "Tel: raqam yuborilsin ");
         KeyboardButton button = new KeyboardButton("Tel raqamni kiriting");
         button.setRequestContact(true);
         KeyboardRow row = new KeyboardRow(List.of(button));
         ReplyKeyboardMarkup Keybord = new ReplyKeyboardMarkup(List.of(row));
         Keybord.setResizeKeyboard(true);
         sendMessage.setReplyMarkup(Keybord);
         sender.execute(sendMessage);
     }
 }

}
