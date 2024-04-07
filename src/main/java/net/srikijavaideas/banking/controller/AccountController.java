package net.srikijavaideas.banking.controller;

import net.srikijavaideas.banking.dto.AccountDto;
import net.srikijavaideas.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Adding Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){

        return  new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Getting Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");

       AccountDto accountDto =  accountService.deposit(id,amount);
       return ResponseEntity.ok(accountDto);

    }

    //Withdrawl REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw (@PathVariable Long id,
                                                @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get all accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts (){
       List<AccountDto> accounts = accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }

    // Delete an account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Successfully...!!!");
    }
}
