package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 5글자 초과 이름")
    void longName_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 빈 이름 입력")
    void emptyName_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 공백만 있는 이름")
    void blankName_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi, ,woni", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 중복된 이름")
    void duplicateName_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,pobi", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 숫자가 아닌 시도 횟수")
    void nonNumericRoundCount_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "abc"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 0 이하의 시도 횟수")
    void zeroRoundCount_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("예외 테스트: 음수 시도 횟수")
    void negativeRoundCount_ExceptionTest() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", "-1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
