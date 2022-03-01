package codegym.vn.repository;

import codegym.vn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUserName(String userName);
}
