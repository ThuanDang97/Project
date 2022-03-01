package codegym.vn.entity;

import javax.persistence.*;

@Entity
public class RegisterPawn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer registerPawnId;
    private String name;
    private String email;
    private String address;
    private String phone;
    private boolean status;
    private String note;

    @ManyToOne(targetEntity = PawnType.class)
    @JoinColumn(name = "pawn_type_id", referencedColumnName = "pawnTypeId")
    private PawnType pawnType;

    public RegisterPawn() {
    }

    public Integer getRegisterPawnId() {
        return registerPawnId;
    }

    public void setRegisterPawnId(Integer registerPawnId) {
        this.registerPawnId = registerPawnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PawnType getPawnType() {
        return pawnType;
    }

    public void setPawnType(PawnType pawnType) {
        this.pawnType = pawnType;
    }
}
