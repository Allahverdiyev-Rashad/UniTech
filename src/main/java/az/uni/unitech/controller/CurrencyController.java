package az.uni.unitech.controller;

import az.uni.unitech.dto.response.CurrencyResponse;
import az.uni.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/latest")
    public ResponseEntity<CurrencyResponse> currencyRates() {
        return ResponseEntity
                .ok(currencyService.allCurrencyRates());
    }

    @GetMapping("/convert")
    public ResponseEntity<Double> convertAmount(@RequestParam Double amount,
                                                @RequestParam String source,
                                                @RequestParam String target) {
        return ResponseEntity
                .ok(currencyService.convertAmount(amount, source, target));
    }

    @GetMapping("/specific")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String source,
                                                  @RequestParam String target) {
        return ResponseEntity
                .ok(currencyService.getSpecificExchangeRate(source, target));
    }

}