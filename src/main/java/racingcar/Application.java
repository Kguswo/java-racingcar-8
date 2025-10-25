package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.car.Cars;
import racingcar.domain.game.RacingGame;
import racingcar.view.input.InputView;
import racingcar.view.output.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            InputView inputView = new InputView();
            OutputView outputView = new OutputView();

            List<String> carNames = inputView.readCarNames();
            int roundCount = inputView.readRoundCount();

            Cars cars = Cars.from(carNames);
            RacingGame racingGame = new RacingGame(cars, outputView);

            racingGame.playGame(roundCount);
        } finally {
            Console.close();
        }
    }
}
