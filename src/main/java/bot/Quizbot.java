package bot;

import handler.Handler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import user.User;
import user.UserService;

public class Quizbot extends TelegramLongPollingBot {

      private UserService service = UserService.getInstance();
      private final Handler handler = new Handler(this);

    @Override
    public String getBotToken() {
        return "6504421376:AAGDYC9eXyDDie6JoycOUaUfGXujfqKkoLo";
    }
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
       if (update.hasMessage()){
           User user = service.getOrElseCreat(update.getMessage().getChatId());
            handle(update,user);
       }

       if (update.hasCallbackQuery()){
           User user = service.getOrElseCreat(update.getCallbackQuery().getFrom().getId());
           handle(update,user);

       }
    }

    private void handle(Update update,User user ){
        switch (user.getRole()){
            case USER -> {
               handler.handler1(update,user);
            }
        }

    }

    @Override
    public String getBotUsername() {

        return "Quizbot1406bot";
    }
}
