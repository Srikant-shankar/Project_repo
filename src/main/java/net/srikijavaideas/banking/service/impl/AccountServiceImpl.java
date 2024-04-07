package net.srikijavaideas.banking.service.impl;

import net.srikijavaideas.banking.dto.AccountDto;
import net.srikijavaideas.banking.entity.Account;
import net.srikijavaideas.banking.mapper.AccountMapper;
import net.srikijavaideas.banking.repository.AccountRepository;
import net.srikijavaideas.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.maoToAccount(accountDto);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {

       Account account = accountRepository
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
       Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        if (account.getBalance() < amount){
            throw new RuntimeException(" Insufficient fund to make successful withdraw ");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
       return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);

    }
}
