package net.srikijavaideas.banking.repository;

import net.srikijavaideas.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
