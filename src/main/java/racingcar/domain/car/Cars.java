package racingcar.domain.car;

import java.util.List;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(List<String> names) {
        List<Car> cars = names.stream()
                .map(Car::createCar)
                .toList();
        return new Cars(cars);
    }
}
