package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.car.Cars;
import racingcar.domain.game.RacingGame;
import racingcar.ui.input.InputHandler;
import racingcar.ui.output.GameResultDisplay;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            InputHandler inputHandler = new InputHandler();
            GameResultDisplay gameResultDisplay = new GameResultDisplay();

            List<String> carNames = inputHandler.readCarNames();
            int roundCount = inputHandler.readRoundCount();

            Cars cars = Cars.from(carNames);
            RacingGame racingGame = new RacingGame(cars, gameResultDisplay);

            racingGame.playGame(roundCount);
        } finally {
            Console.close();
        }
    }
}
