package codegym.vn.service.impl;

import codegym.vn.entity.AccRole;
import codegym.vn.entity.Account;
import codegym.vn.repository.AccRoleRepository;
import codegym.vn.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccRoleRepository accRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUserName(userName);

        if (account == null) {
//            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

//        System.out.println("Found User: " + userName);
        // [ROLE_USER, ROLE_ADMIN,..]
        List<AccRole> roles = this.accRoleRepository.findAllByAccount(account);
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (AccRole role1 : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role1.getRole().getName());
                grantList.add(authority);
            }
        }

        return new User(account.getUserName(), account.getPassword(), grantList);
    }
}
