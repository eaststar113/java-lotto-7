package lotto.validation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchasePriceValidatorTest {
    @DisplayName("구입 금액이 자연수가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "abc"})
    void 구입_금액이_자연수가_아닌_경우_예외가_발생한다(String price) {
        assertThatThrownBy(() -> PurchasePriceValidator.validator(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 자연수가 아닙니다.");
    }

    @DisplayName("구입 금액이 1000으로 나누어 떨어지지 않는 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1500", "250"})
    void 구입_금액이_1000으로_나누어떨어지지_않는_경우_예외가_발생한다(String price) {
        assertThatThrownBy(() -> PurchasePriceValidator.validator(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1000원으로 나누어떨어지지 않습니다.");
    }

    @DisplayName("구입 금액이 자연수이면서 1000으로 나누어떨어지는 경우 예외가 발생하지 않는다.")
    @Test
    void 구입_금액이_자연수_이면서_1000으로_나누어떨어지는_경우_예외가_발생하지_않는다() {
        PurchasePriceValidator.validator("1000");
        PurchasePriceValidator.validator("5000");
        PurchasePriceValidator.validator("100000");
    }
}