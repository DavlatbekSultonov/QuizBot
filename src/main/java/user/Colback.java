package user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum Colback {
    CREAT_QUIZ("1"),

    MY_QUIZ("1");
    @Getter
    private final String value;

}
