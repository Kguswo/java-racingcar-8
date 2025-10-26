package racingcar.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Cars;
import racingcar.ui.output.GameResultDisplay;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNoException;

class RacingGameTest {

    private GameResultDisplay gameResultDisplay;

    @BeforeEach
    void setUp() {
        gameResultDisplay = new GameResultDisplay();
    }

    @Test
    @DisplayName("여러 대의 자동차로 게임을 시작할 수 있다")
    void playGame_WithMultipleCars_Success() {
        Cars cars = Cars.from(List.of("pobi", "woni", "jun"));
        RacingGame game = new RacingGame(cars, gameResultDisplay);

        assertThatCode(() -> game.playGame(5))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("라운드 횟수만큼 게임을 진행한다")
    void playGame_WithRoundCount_Success() {
        Cars cars = Cars.from(List.of("pobi", "woni"));
        RacingGame game = new RacingGame(cars, gameResultDisplay);

        assertThatNoException()
            .isThrownBy(() -> game.playGame(1));

        assertThatNoException()
            .isThrownBy(() -> game.playGame(10));
    }
}