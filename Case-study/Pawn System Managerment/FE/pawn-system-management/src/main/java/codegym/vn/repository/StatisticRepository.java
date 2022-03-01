package codegym.vn.repository;

import codegym.vn.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatisticRepository extends JpaRepository<Contract,String> {
    @Query(value = "select *" +
            "from contract c " +
            "join type_contract tc on tc.type_contract_id=c.type_contract_id " +
            "join status_contract sc on sc.status_contract_id=c.status_contract_id " +
            "where sc.status_contract_id=3 and (c.start_date>= :startDate  and c.end_date<= :endDate)", nativeQuery = true)
    List<Contract> statisticInterest(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "select *" +
            "from contract c " +
            "join type_contract tc on tc.type_contract_id=c.type_contract_id " +
            "join status_contract sc on sc.status_contract_id=c.status_contract_id " +
            "where tc.type_contract_id=2 and sc.status_contract_id=3 and (c.start_date>= :startDate  and c.end_date<= :endDate)", nativeQuery = true)
    List<Contract> statisticLiquidation(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "select *" +
            "from contract c " +
            "join type_contract tc on tc.type_contract_id=c.type_contract_id " +
            "join status_contract sc on sc.status_contract_id=c.status_contract_id " +
            "where sc.status_contract_id=1 and (c.start_date>= :startDate  and c.end_date<= :endDate)", nativeQuery = true)
    List<Contract> statisticExpected(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
