package codegym.vn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer financeId;

    private Integer moneyOfSafe;
    private Integer currentCapital;
    private Integer investmentMoney;
    private Integer expectedProfit;

    public Finance() {
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public Integer getMoneyOfSafe() {
        return moneyOfSafe;
    }

    public void setMoneyOfSafe(Integer moneyOfSafe) {
        this.moneyOfSafe = moneyOfSafe;
    }

    public Integer getCurrentCapital() {
        return currentCapital;
    }

    public void setCurrentCapital(Integer currentCapital) {
        this.currentCapital = currentCapital;
    }

    public Integer getInvestmentMoney() {
        return investmentMoney;
    }

    public void setInvestmentMoney(Integer investmentMoney) {
        this.investmentMoney = investmentMoney;
    }

    public Integer getExpectedProfit() {
        return expectedProfit;
    }

    public void setExpectedProfit(Integer expectedProfit) {
        this.expectedProfit = expectedProfit;
    }
}
