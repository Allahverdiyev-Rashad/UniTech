package az.uni.unitech.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentCurrencyScheduler {

    private final CurrencyService currencyService;

    @Scheduled(cron = "0 * * * * *")
    public void recentCurrencyData() {
        log.info("recentCurrencyData : ");
        currencyService.updateRecentCurrentData();
    }

}