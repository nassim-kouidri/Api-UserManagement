package com.nassim.usermanagement.Repository;

import com.nassim.usermanagement.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
