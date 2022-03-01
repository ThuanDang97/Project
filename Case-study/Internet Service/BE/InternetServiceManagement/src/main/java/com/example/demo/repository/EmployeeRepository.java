package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    @Query("select e from Employee e " +
            "join Position  p on e.position.positionId = p.positionId where (e.employeeId like %?1%) " +
            "and (e.dateOfBirth between ?2 and ?3) and (e.startWorkDate between ?4 and ?5) " +
            "and (e.address like %?6%) " +
            "and (e.position.positionName like %?7%) order by e.level")
    Page<Employee> searchEmployee(String idEmp, String dateStart, String dateEnd, String workStart,
                                  String workEnd, String address, String positionId,
                                  Pageable pageable);

    Employee findByAccount_UserName(String userName);

}