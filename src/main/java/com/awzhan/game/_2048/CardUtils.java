package com.awzhan.game._2048;

import com.awzhan.game._2048.model.Card;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardUtils {

    public static void moveUp(@NonNull final Card[][] cards, int i, int j) {
        if (i == 0) {
            return;
        }

        final Card curr = cards[i][j];
        final Card prev = cards[i-1][j];
        if (prev.getValue() == 0) {
            prev.setValue(curr.getValue());
            curr.setValue(0);
        }
        else if (prev.getValue() == curr.getValue()) {

        }
    }
}
