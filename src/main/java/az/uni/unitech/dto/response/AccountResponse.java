package az.uni.unitech.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountResponse {

    private String accountNumber;
    private Boolean status;
    private BigDecimal balance;

}