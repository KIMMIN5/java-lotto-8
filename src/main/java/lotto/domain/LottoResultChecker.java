package lotto.domain;

import java.util.*;

public class LottoResultChecker {
    public Map<Rank, Integer> CheckResults(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for(Rank rank: Rank.values()) {
            statistics.put(rank, 0);
        }

        for(Lotto lotto: purchasedLottos) {
            long matchCount = lotto.getNumbers().stream()
                    .filter(winningLotto.getNumbers()::contains)
                    .count();

            boolean hasBonus = lotto.getNumbers().contains(winningLotto.getBonusNumber());

            Rank rank = Rank.valueOf((int)matchCount, hasBonus);
            statistics.put(rank, statistics.get(rank) + 1);
        }
        return statistics;
    }
}
