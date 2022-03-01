package codegym.vn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class PawnType {
    @Id
    private Integer pawnTypeId;
    private String name;

    @OneToMany(mappedBy = "pawnType")
    private Set<RegisterPawn> registerPawns;

    public PawnType() {
    }

    public Integer getPawnTypeId() {
        return pawnTypeId;
    }

    public void setPawnTypeId(Integer pawnTypeId) {
        this.pawnTypeId = pawnTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RegisterPawn> getRegisterPawns() {
        return registerPawns;
    }

    public void setRegisterPawns(Set<RegisterPawn> registerPawns) {
        this.registerPawns = registerPawns;
    }
}
