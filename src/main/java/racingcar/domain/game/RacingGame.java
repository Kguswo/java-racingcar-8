package racingcar.domain.game;

import racingcar.domain.car.Cars;
import racingcar.ui.output.GameResultDisplay;

import java.util.List;

/**
 * 자동차 경주 게임 진행 클래스
 */
public class RacingGame {
    private final Cars cars;
    private final GameResultDisplay gameResultDisplay;

    public RacingGame(Cars cars, GameResultDisplay gameResultDisplay) {
        this.cars = cars;
        this.gameResultDisplay = gameResultDisplay;
    }

    /**
     * 라운드 횟수 만큼 경기 진행
     *
     * @param roundCount 시도 횟수
     */
    public void playGame(int roundCount) {
        gameResultDisplay.printResultHeader();

        for (int i = 0; i < roundCount; i++) {
            playRound();
        }

        printWinners();
    }

    /**
     * 각 라운드 진행
     */
    private void playRound() {
        cars.moveAll();
        gameResultDisplay.printRoundResult(cars.getCars());
    }

    /**
     * 최종 우승 결과 출력
     */
    private void printWinners() {
        List<String> winners = cars.findWinners();
        gameResultDisplay.printWinnerResult(winners);
    }
}
