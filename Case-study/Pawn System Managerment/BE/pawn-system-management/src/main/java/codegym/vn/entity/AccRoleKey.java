package codegym.vn.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AccRoleKey implements Serializable {
    @Column(name = "user_name")
    private String userName;
    @Column(name = "role_id")
    private Integer idRole;

    public AccRoleKey() {
    }

    public AccRoleKey(String userName, Integer idRole) {
        this.userName = userName;
        this.idRole = idRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
