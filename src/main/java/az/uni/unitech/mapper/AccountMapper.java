package az.uni.unitech.mapper;

import az.uni.unitech.domain.Account;
import az.uni.unitech.dto.request.AccountRequest;
import az.uni.unitech.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account toDomain(AccountRequest accountRequest);

    AccountResponse toDto(Account account);

}