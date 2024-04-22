package az.uni.unitech.service;

import az.uni.unitech.dto.response.CurrencyResponse;
import az.uni.unitech.error.ErrorMessage;
import az.uni.unitech.error.exception.IllegalArgumentException;
import az.uni.unitech.repository.CurrencyRepository;
import az.uni.unitech.domain.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Value("${currency.api.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    public CurrencyResponse allCurrencyRates() {
        return restTemplate.getForObject(url, CurrencyResponse.class);
    }

    public double getSpecificExchangeRate(String sourceCurrency, String targetCurrency) {
        CurrencyResponse response = restTemplate.getForObject(url, CurrencyResponse.class);
        if (response == null) {
            throw IllegalArgumentException.getInstance(ErrorMessage.INVALID_DATA);
        }
        double sourceRate = response.getRates().get(sourceCurrency);
        double targetRate = response.getRates().get(targetCurrency);
        if (sourceRate == 0 || targetRate == 0) {
            throw IllegalArgumentException.getInstance(ErrorMessage.INVALID_PAIR);
        }
        Currency currency = new Currency();
        currency.setCurrencyType(sourceCurrency);
        currency.setRate(targetRate / sourceRate);
        currency.setUpdatedDate(LocalDateTime.now());
        currencyRepository.save(currency);
        return targetRate / sourceRate;
    }

    public double convertAmount(double amount, String source, String target) {
        double exchangeRate = getSpecificExchangeRate(source, target);
        if (exchangeRate == 0) {
            throw IllegalArgumentException.getInstance(ErrorMessage.INVALID_PAIR);
        }
        return amount * exchangeRate;
    }

    public void updateRecentCurrentData() {
        LocalDateTime now = LocalDateTime.now().minusMinutes(1);
        List<Currency> currencies = currencyRepository.findByUpdatedDateBefore(now);
        for (Currency currency : currencies) {
            currency.setUpdatedDate(LocalDateTime.now());
            currencyRepository.save(currency);
        }
    }

}