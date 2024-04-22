package az.uni.unitech.controller;

import az.uni.unitech.dto.request.AccountRequest;
import az.uni.unitech.dto.response.AccountResponse;
import az.uni.unitech.dto.response.AccountResponseList;
import az.uni.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<AccountResponse> createAccount(@PathVariable(name = "userId") Long userId,
                                                         @RequestBody AccountRequest accountRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.createAccount(userId, accountRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AccountResponseList> allActiveAccounts(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity
                .ok(accountService.allActiveAccounts(userId));
    }

    @PutMapping("/change-status/{userId}")
    public ResponseEntity<AccountResponse> changeStatusAccount(@PathVariable(name = "userId") Long userId,
                                                               @RequestBody AccountRequest request) {
        return ResponseEntity
                .ok(accountService.changeStatusAccount(userId, request));
    }

}