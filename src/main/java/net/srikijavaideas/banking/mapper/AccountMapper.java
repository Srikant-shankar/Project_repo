package net.srikijavaideas.banking.mapper;

import net.srikijavaideas.banking.dto.AccountDto;
import net.srikijavaideas.banking.entity.Account;

public class AccountMapper {
    public static Account maoToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()

        );

        return accountDto;
    }
}
