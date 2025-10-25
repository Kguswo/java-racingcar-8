package racingcar.domain.car;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int INITIAL_POSITION = 0; // 기본 시작 위치
    private static final int MAX_NAME_LENGTH = 5; // 최대 이름 길이
    private static final int RANDOM_MIN = 0; // 랜덤 값 범위 최소값
    private static final int RANDOM_MAX = 9; // 랜덤 값 범위 최대값
    private static final int MOVE_THRESHOLD = 4; // 이동 조건 한계값 (4 이상일 시 이동)

    private final String name;
    private int position;

    private Car(String name) {
        this.name = name;
        this.position = INITIAL_POSITION;
    }

    /**
     * 이름을 통해 자동차를 생성
     *
     * @param name 자동차 이름 (5자 이하, 빈 값 불가능)
     * @return 생성된 자동차 Car 객체
     * @throws IllegalArgumentException 이름이 null이거나 빈 값이거나 5글자 초과인 경우 예외 발생
     */
    public static Car createCar(String name) {
        validateName(name);
        return new Car(name);
    }

    /**
     * 자동차 이름 유효성 검사
     *
     * @param name 검증할 이름
     * @throws IllegalArgumentException 이름이 유효하지 않은 경우 예외 발생
     */
    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 빈 값이 불가능합니다.");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5글자를 초과할 수 없습니다.");
        }
    }

    /**
     * 무작위 값이 4 이상이면 전진
     */
    public void move() {
        int randomNumber = generateRandomNumber();
        if (randomNumber >= MOVE_THRESHOLD) {
            position++;
        }
    }

    /**
     * 무작위 값(0~9)을 생성
     */
    private int generateRandomNumber() {
        return Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
