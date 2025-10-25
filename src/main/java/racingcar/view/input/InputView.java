package racingcar.view.input;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_COUNT_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private static final String SEPARATOR = ","; // 이름 구분자 쉼표

    /**
     * 자동차 이름 입력받아 리스트로 반환
     *
     * @return 자동차 이름 목록
     * @throws IllegalArgumentException 잘못된 형식 또는 중복된 이름이 있는 경우 예외 발생
     */
    public List<String> readCarNames() {
        System.out.println(CAR_NAMES_INPUT_MESSAGE);
        String input = Console.readLine();

        List<String> names = parseCarNames(input);
        validateDuplicateNames(names);

        return names;
    }

    /**
     * 쉼표 `,` 로 구분된 문자열을 파싱하여 리스트로 변환
     *
     * @param input 입력 문자열
     * @return 파싱된 이름 목록
     */
    private List<String> parseCarNames(String input) {
        return Arrays.stream(input.split(SEPARATOR))
                .toList();
    }

    /**
     * 중복된 이름 검증
     *
     * @param names 자동차 이름 목록
     * @throws IllegalArgumentException 중복된 이름 있는 경우 예외 발생
     */
    private void validateDuplicateNames(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

    /**
     * 시도 횟수 입력 받기
     *
     * @return 시도 횟수
     * @throws IllegalArgumentException 숫자가 아니거나 양수가 아닌 경우 예외 발생
     */
    public int readRoundCount() {
        System.out.println(ROUND_COUNT_INPUT_MESSAGE);
        String input = Console.readLine();
        int roundCount = parseRoundCount(input);
        validatePositiveInteger(roundCount);
        return roundCount;
    }

    /**
     * 문자열을 정수로 변환
     *
     * @param input 입력 문자열
     * @return 변환된 정수
     * @throws IllegalArgumentException 숫자가 아닌 경우 예외 발생
     */
    private int parseRoundCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자 타입이어야 합니다.");
        }
    }

    /**
     * 시도 횟수가 1 이상 정수인지 검증
     *
     * @param roundCount 시도 횟수
     * @throws IllegalArgumentException 0 이하인 경우 예외 발생
     */
    private void validatePositiveInteger(int roundCount) {
        if (roundCount <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상 정수이어야 합니다.");
        }
    }
}
