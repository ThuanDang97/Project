package codegym.vn.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Contract {
    @Id
    private String contractId;
    private String productImg;
    private String productName;
    private Integer interestMoney;
    private Integer receiveMoney;
    private Integer loanMoney;
    private Date liquidationDate;
    private Date startDate;
    private Date endDate;
    private Integer quantity;

    @JsonManagedReference
    @ManyToOne(targetEntity = StatusContract.class)
    @JoinColumn(name = "status_contract_id", referencedColumnName = "statusContractId")
    private StatusContract statusContract;

    @JsonManagedReference
    @ManyToOne(targetEntity = TypeProduct.class)
    @JoinColumn(name = "type_product_id", referencedColumnName = "typeProductId")
    private TypeProduct typeProduct;

    @JsonManagedReference
    @ManyToOne(targetEntity = TypeContract.class)
    @JoinColumn(name = "type_contract_id", referencedColumnName = "typeContractId")
    private TypeContract typeContract;


    @JsonManagedReference
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
    private Employee employee;

    @JsonBackReference
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    public Contract() {
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(Integer interestMoney) {
        this.interestMoney = interestMoney;
    }

    public Integer getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Integer receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Integer loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Date getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(Date liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatusContract getStatusContract() {
        return statusContract;
    }

    public void setStatusContract(StatusContract statusContract) {
        this.statusContract = statusContract;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public TypeContract getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(TypeContract typeContract) {
        this.typeContract = typeContract;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
