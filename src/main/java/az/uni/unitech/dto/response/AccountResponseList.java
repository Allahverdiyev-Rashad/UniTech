package az.uni.unitech.dto.response;

import az.uni.unitech.dto.AccountDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AccountResponseList {

    List<AccountDto> accountResponseList;

}