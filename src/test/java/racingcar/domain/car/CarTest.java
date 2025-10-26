package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Nested
    @DisplayName("Car 생성 테스트")
    class CreateCarTest {

        @Test
        @DisplayName("유효한 이름으로 자동차를 생성할 수 있다.")
        void createCar_WithValidName_Success() {
            String name = "pobi";

            Car car = Car.createCar(name);

            assertThat(car.getName()).isEqualTo(name);
            assertThat(car.getPosition()).isZero();
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
        @DisplayName("1이상 5이하의 문자열로 자동차 생성할 수 있다.")
        void createCar_WithValidLengthName_Success(String name) {
            Car car = Car.createCar(name);

            assertThat(car.getName()).isEqualTo(name);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" "})
        @DisplayName("이름이 null이거나 빈 값이면 예외가 발생한다.")
        void createCar_WithNullOrEmptyName_ThrowsException(String name) {
            assertThatThrownBy(() -> Car.createCar(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 빈 값이 불가능합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"pobi123", "abcdef", "longname"})
        @DisplayName("이름이 5글자를 초과하면 예외가 발생한다.")
        void createCar_WithNameLongerThan5Characters_ThrowsException(String name) {
            assertThatThrownBy(() -> Car.createCar(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5글자를 초과할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("Car 이동 테스트")
    class MoveTest {

        @Test
        @DisplayName("move 호출 시 현재 위치는 음수가 아니다")
        void move_Always_PositionIsNotNegative() {
            Car car = Car.createCar("pobi");

            car.move();

            assertThat(car.getPosition()).isGreaterThanOrEqualTo(0);
        }

        @Test
        @DisplayName("여러 번 move 호출 시 위치는 증가하거나 유지된다")
        void move_MultipleTimes_PositionIncreasesOrStays() {
            Car car = Car.createCar("pobi");
            int initialPosition = car.getPosition();

            for (int i = 0; i < 10; i++) {
                car.move();
            }

            assertThat(car.getPosition()).isGreaterThanOrEqualTo(initialPosition);
        }
    }

    @Nested
    @DisplayName("Getter 테스트")
    class GetterTest {

        @Test
        @DisplayName("getName은 생성 시 입력한 이름을 반환한다")
        void getName_ReturnsCorrectName() {
            String name = "pobi";
            Car car = Car.createCar(name);

            assertThat(car.getName()).isEqualTo(name);
        }

        @Test
        @DisplayName("getPosition은 초기값 0을 반환한다")
        void getPosition_InitiallyReturnsZero() {
            Car car = Car.createCar("pobi");

            assertThat(car.getPosition()).isZero();
        }
    }
}