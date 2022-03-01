package codegym.vn.entity;

import javax.persistence.*;

@Entity
public class AccRole {
    @EmbeddedId
    private AccRoleKey accRoleKey;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "user_name")
    private Account account;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    public AccRole() {
    }

    public AccRoleKey getAccRoleKey() {
        return accRoleKey;
    }

    public void setAccRoleKey(AccRoleKey accRoleKey) {
        this.accRoleKey = accRoleKey;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
