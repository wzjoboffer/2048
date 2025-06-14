package com.awzhan.game._2048;

import com.awzhan.game._2048.model.Card;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardUtils {

    public static void cleanup(@NonNull final Card[][] cards) {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                cards[i][j].setMerge(false);
            }
        }
    }

    public static void moveUp(@NonNull final Card[][] cards, int i, int j) {
        if (i == 0 || cards[i][j].getValue() == 0) {
            return;
        }

        final Card curr = cards[i][j];
        final Card prev = cards[i-1][j];
        mergeCard(curr, prev);
    }

    public static void moveDown(@NonNull final Card[][] cards, int i, int j) {
        if (i == cards.length - 1 || cards[i][j].getValue() == 0) {
            return;
        }

        final Card curr = cards[i][j];
        final Card prev = cards[i+1][j];
        mergeCard(curr, prev);
    }

    public static void moveLeft(@NonNull final Card[][] cards, int i, int j) {
        if (j == 0 || cards[i][j].getValue() == 0) {
            return;
        }

        final Card curr = cards[i][j];
        final Card prev = cards[i][j-1];
        mergeCard(curr, prev);
    }

    public static void moveRight(@NonNull final Card[][] cards, int i, int j) {
        if (j == cards[0].length - 1 || cards[i][j].getValue() == 0) {
            return;
        }

        final Card curr = cards[i][j];
        final Card prev = cards[i][j+1];
        mergeCard(curr, prev);
    }

    private static void mergeCard(final Card curr, final Card prev) {
        if (prev.getValue() == 0) {
            prev.setValue(curr.getValue());
            curr.setValue(0);
        }
        else if (!prev.isMerge() && prev.getValue() == curr.getValue()) {
            prev.setValue(prev.getValue() * 2);
            curr.setValue(0);
            prev.setMerge(true);
        }
    }
}
