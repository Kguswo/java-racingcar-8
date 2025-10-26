package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @Nested
    @DisplayName("Cars 생성 테스트")
    class CreateCarsTest {

        @Test
        @DisplayName("유효한 이름 목록으로 Cars를 생성할 수 있다")
        void from_WithValidNames_Success() {
            List<String> names = List.of("pobi", "woni", "jun");

            Cars cars = Cars.from(names);

            assertThat(cars.getCars()).hasSize(3);

            assertThat(cars.getCars())
                .extracting("name")  // 또는 Car::getName
                .containsExactly("pobi", "woni", "jun");  // 순서까지 정확히 일치
        }

        @Test
        @DisplayName("빈 이름이 포함되면 예외가 발생한다")
        void from_WithEmptyName_ThrowsException() {
            List<String> names = List.of("pobi", "", "jun");

            assertThatThrownBy(() -> Cars.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값");
        }

        @Test
        @DisplayName("5글자를 초과하는 이름이 포함되면 예외가 발생한다")
        void from_WithLongName_ThrowsException() {
            // given
            List<String> names = List.of("pobi", "toolong", "jun");

            // when & then
            assertThatThrownBy(() -> Cars.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5글자");
        }
    }

    @Nested
    @DisplayName("Cars 이동 테스트")
    class MoveAllTest {

        @Test
        @DisplayName("moveAll 호출 시 모든 자동차가 이동 시도한다")
        void moveAll_MovesAllCars() {
            List<String> names = List.of("pobi", "woni", "jun");
            Cars cars = Cars.from(names);

            cars.moveAll();

            assertThat(cars.getCars())
                .allMatch(car -> car.getPosition() >= 0);
        }

        @Test
        @DisplayName("여러 번 moveAll 호출 시 모든 자동차 위치는 증가하거나 유지된다")
        void moveAll_MultipleTimes_AllPositionsIncreaseOrStay() {
            List<String> names = List.of("pobi", "woni");
            Cars cars = Cars.from(names);

            for (int i = 0; i < 5; i++) {
                cars.moveAll();
            }

            List<Car> carList = cars.getCars();
            for (Car car : carList) {
                assertThat(car.getPosition()).isGreaterThanOrEqualTo(0);
            }
        }
    }

    @Nested
    @DisplayName("우승자 찾기 테스트")
    class FindWinnersTest {

        @Test
        @DisplayName("가장 멀리 이동한 자동차를 우승자로 반환한다")
        void findWinners_ReturnsCarWithMaxPosition() {
            List<String> names = List.of("pobi", "woni", "jun");
            Cars cars = Cars.from(names);

            for (int i = 0; i < 10; i++) {
                cars.moveAll();
            }
            List<String> winners = cars.findWinners();

            assertThat(winners).isNotEmpty();
        }

        @Test
        @DisplayName("동점인 경우 여러 명의 우승자를 반환한다")
        void findWinners_WithTie_ReturnsMultipleWinners() {
            List<String> names = List.of("pobi", "woni");
            Cars cars = Cars.from(names);

            // 이동하지 않으면 모두 위치 0이므로 모두 우승자
            List<String> winners = cars.findWinners();

            assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
        }
    }

    @Nested
    @DisplayName("getCars 테스트")
    class GetCarsTest {

        @Test
        @DisplayName("getCars는 올바른 개수의 자동차를 반환한다")
        void getCars_ReturnsCorrectSize() {
            List<String> names = List.of("pobi", "woni", "jun");
            Cars cars = Cars.from(names);

            List<Car> carList = cars.getCars();

            assertThat(carList).hasSize(3);
        }

        @Test
        @DisplayName("getCars는 불변 리스트를 반환한다 (선택 사항)")
        void getCars_ReturnsUnmodifiableList() {
            List<String> names = List.of("pobi", "woni");
            Cars cars = Cars.from(names);

            List<Car> carList = cars.getCars();

            // 리스트에 추가하려고 하면 예외 발생
            assertThatThrownBy(() -> carList.add(Car.createCar("jun")))
                .isInstanceOf(UnsupportedOperationException.class);
        }
    }
}