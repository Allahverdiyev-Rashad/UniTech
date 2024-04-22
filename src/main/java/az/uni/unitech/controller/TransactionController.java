package az.uni.unitech.controller;

import az.uni.unitech.dto.request.TransferRequest;
import az.uni.unitech.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public void moneyTransfer(@RequestBody TransferRequest transferRequest) {
        transactionService.moneyTransfer(transferRequest);
    }

}