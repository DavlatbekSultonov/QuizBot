package bot;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import user.Colback;

import java.util.ArrayList;
import java.util.List;

public class InlineBuilder {

    public static InlineKeyboardMarkup build(List<List<org.apache.commons.lang3.tuple.Pair<String,Colback>>> buttons){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> inline = new ArrayList<>();
        for (List<org.apache.commons.lang3.tuple.Pair<String,Colback>> rows : buttons){
            List<InlineKeyboardButton> row =new ArrayList<>();
            for (Pair<String,Colback> button : rows){
                InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
                keyboardButton.setText(button.getLeft());
                keyboardButton.setCallbackData(button.getRight().getValue());
                row.add(keyboardButton);
            }
            inline.add(row);
        }
        inlineKeyboardMarkup.setKeyboard(inline);

        return inlineKeyboardMarkup;
    }
}
