package by.htp.play;

import by.htp.play.Condition;

public class Game {

    public static void main(String[] args) {

        String userMoveResult;
        String computerMoveResult = "";

        Condition play = new Condition();
        play.initCities();
        play.printIntro();

        while (true) {
            userMoveResult = play.userMove(computerMoveResult);

            if (play.isValidate(userMoveResult)) {
                computerMoveResult = play.computerMove(userMoveResult);
                System.out.println(computerMoveResult);
                if (computerMoveResult.equals(GameMessage.USER_WIN))
                    break;
            }
            else
                System.out.println(userMoveResult);
            if (userMoveResult.equals(GameMessage.COMP_WIN))
                break;
        }
    }
}
