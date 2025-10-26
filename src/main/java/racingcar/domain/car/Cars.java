package racingcar.domain.car;

import java.util.Collections;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * 자동차 이름 목록으로부터 Cars 객체 생성
     *
     * @param names 자동차 이름 목록
     * @return 생성된 Cars 객체
     * @throws IllegalArgumentException 이름이 유효하지 않은 경우 예외 발생
     */
    public static Cars from(List<String> names) {
        List<Car> cars = names.stream()
                              .map(Car::createCar)
                              .toList();
        return new Cars(cars);
    }

    /**
     * 모든 자동차 1회 이동
     */
    public void moveAll() {
        for (Car car : cars) {
            car.move();
        }
    }

    /**
     * 가장 멀리 이동한 자동차(들)의 이름 반환
     *
     * @return 우승자 이름 목록
     */
    public List<String> findWinners() {
        int maxPosition = findMaxPosition();
        return findCarsByPosition(maxPosition);
    }

    /**
     * 자동차들 중 최대 이동거리 반환
     *
     * @return 최대 이동 거리
     */
    private int findMaxPosition() {
        return cars.stream()
                   .mapToInt(Car::getPosition)
                   .max()
                   .orElse(0);
    }

    /**
     * 특정 위치에 있는 자동차(들)의 이름을 모두 반환
     *
     * @param targetPosition 찾을 타겟 위치
     * @return 해당 타겟 위치에 있는 자동차 이름 목록
     */
    private List<String> findCarsByPosition(int targetPosition) {
        return cars.stream()
                   .filter(car -> car.getPosition() == targetPosition)
                   .map(Car::getName)
                   .toList();
    }

    /**
     * 모든 자동차의 불변 리스트 반환
     *
     * @return 자동차 목록
     */
    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
