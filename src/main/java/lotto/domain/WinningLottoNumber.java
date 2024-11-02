package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLottoNumber {
    //당첨번호와 보너스번호 받고 유효성 검증 따로
    private Lotto winningNumber;
    private BonusNumber bonusNumber;

    public WinningLottoNumber(Lotto winningNumber, String bonusNumber){
        this.winningNumber = winningNumber;
        this.bonusNumber = parseBonusNumber(bonusNumber, this.winningNumber);
    }
    //당첨번호와 보너스번호 유효성 검증

    public static Lotto parseWinningNumber(String winningNumber) {
        List<String> list = new ArrayList<>(Arrays.asList(winningNumber.split(",", -1)));
        List<Integer> winningNumbers = new ArrayList<>();

        for (String lottoNumber : list) {
            try {
                winningNumbers.add(Integer.parseInt(lottoNumber));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 유효한 당첨 번호를 입력해주세요.");
            }
        }
        return new Lotto(winningNumbers);
    }

    public static BonusNumber parseBonusNumber(String bonusNumber, Lotto winningNumber) {
        int bonus;
        try {
            bonus = Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 보너스 번호를 입력해주세요.");
        }
        return new BonusNumber(bonus, winningNumber);
    }

    public Lotto getWinningNumber() {
        return winningNumber;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}