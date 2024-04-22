package az.uni.unitech.dto.request;

import az.uni.unitech.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountRequest {

    private String accountNumber;
    private Boolean status;
    private BigDecimal balance;
    private User user;

}