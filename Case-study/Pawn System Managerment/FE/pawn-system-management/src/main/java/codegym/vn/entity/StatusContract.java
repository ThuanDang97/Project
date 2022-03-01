package codegym.vn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class StatusContract {
    @Id
    private Integer statusContractId;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "statusContract")
    private Set<Contract> contracts;

    public StatusContract() {
    }

    public Integer getStatusContractId() {
        return statusContractId;
    }

    public void setStatusContractId(Integer statusContractId) {
        this.statusContractId = statusContractId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
