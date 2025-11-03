package lotto.view;

import java.util.*;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {
    public void outputPurchasedLottoList(List<Lotto> purchasedLottoList) {
        System.out.println(purchasedLottoList.size() + "개를 구매했습니다.");

        for (Lotto lotto : purchasedLottoList) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printWinningStatistics(Map<Rank, Integer> statistics,  double rateOfReturn) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        System.out.printf("%d개 일치 (%,d원) - %d개\n",
                Rank.FIFTH.getMatchCount(),
                Rank.FIFTH.getPrize(),
                statistics.getOrDefault(Rank.FIFTH, 0)
        );
        System.out.printf("%d개 일치 (%,d원) - %d개\n",
                Rank.FOURTH.getMatchCount(),
                Rank.FOURTH.getPrize(),
                statistics.getOrDefault(Rank.FOURTH, 0)
        );
        System.out.printf("%d개 일치 (%,d원) - %d개\n",
                Rank.THIRD.getMatchCount(),
                Rank.THIRD.getPrize(),
                statistics.getOrDefault(Rank.THIRD, 0)
        );
        System.out.printf("%d개 일치, 보너스 볼 일치 (%,d원) - %d개\n",
                Rank.SECOND.getMatchCount(),
                Rank.SECOND.getPrize(),
                statistics.getOrDefault(Rank.SECOND, 0)
        );

        System.out.printf("%d개 일치 (%,d원) - %d개\n",
                Rank.FIRST.getMatchCount(),
                Rank.FIRST.getPrize(),
                statistics.getOrDefault(Rank.FIRST, 0)
        );

        System.out.printf("총 수익률은 %.1f%%입니다.", rateOfReturn);
    }

    public void printError(String error) {
        System.out.println(error);
    }
}
