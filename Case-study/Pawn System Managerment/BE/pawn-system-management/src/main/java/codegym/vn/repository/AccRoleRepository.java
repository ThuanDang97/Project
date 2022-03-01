package codegym.vn.repository;

import codegym.vn.entity.AccRole;
import codegym.vn.entity.AccRoleKey;
import codegym.vn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccRoleRepository extends JpaRepository<AccRole, AccRoleKey> {
    List<AccRole> findAllByAccount(Account account);
}
