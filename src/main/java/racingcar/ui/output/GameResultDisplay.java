package racingcar.ui.output;

import racingcar.domain.car.Car;

import java.util.List;

public class GameResultDisplay {
    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String POSITION_MARKER = "-";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String WINNER_PREFIX = "최종 우승자 : ";
    private static final String WINNER_SEPARATOR = ", ";

    public void printResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void printRoundResult(List<Car> cars) {
        StringBuilder roundResult = new StringBuilder();
        for (Car car : cars) {
            roundResult.append(currentCarStatus(car)).append("\n");
        }
        System.out.println(roundResult);
    }

    private String currentCarStatus(Car car) {
        String positionBar = POSITION_MARKER.repeat(car.getPosition());
        return car.getName() + NAME_POSITION_SEPARATOR + positionBar;
    }

    public void printWinnerResult(List<String> winners) {
        String winnersText = String.join(WINNER_SEPARATOR, winners);
        System.out.println(WINNER_PREFIX + winnersText);
    }
}
