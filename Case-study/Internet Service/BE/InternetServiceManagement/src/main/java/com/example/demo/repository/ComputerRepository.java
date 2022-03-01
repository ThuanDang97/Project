package com.example.demo.repository;

import com.example.demo.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ComputerRepository extends JpaRepository<Computer, String> {
    @Query(value = "select * \n " +
            "from computer c \n " +
            "inner join `type` t on c.type_id = t.type_id \n" +
            "inner join status s on c.status_id = s.status_id \n " +
            "where (c.computer_id like %:computerId% or c.computer_id is null \n" +
            "and (c.computer_location like %:computerLocation% or c.computer_location is null) \n " +
            "and (t.type_name like %:type% or t.type_name is null) \n " +
            "and (s.status_name like %:status% or s.status_name is null) \n " +
            "and ((c.computer_start_used_date between :computerStartUsedFrom and :computerStartUsedTo)) \n ",nativeQuery= true)
    Page<Computer> searchComputer(@Param("computerId") String computerId,
                                  @Param("computerLocation") String computerLocation,
                                  @Param("computerStartUsedFrom") String computerStartUsedFrom,
                                  @Param("computerStartUsedTo") String computerStartUsedTo,
                                  @Param("type") String type,
                                  @Param("status") String status,
                                  Pageable pageable);





    @Query("select c from Computer c "+
    "join Type t on c.type.typeId = t.typeId " +
    "join Status s on c.status.statusId = s.statusId " +
    "where (c.computerId is null or c.computerId like %:computerId%) and "+
    "(c.computerLocation is null or c.computerLocation like %:computerLocation%) and "+
    "(c.computerStartUsedDate between :startUsedDateFromComputer and :startUsedDateToComputer) and "+
    "(c.type.typeName is null or c.type.typeName like %:type%) and "+
    "(c.status.statusName is null or c.status.statusName like %:status%)" +
    "order by c.computerId")
    Page<Computer> advancedSearchComputer(String computerId,String computerLocation, String startUsedDateFromComputer,
                                          String startUsedDateToComputer, String type,String status,Pageable pageable);

    Computer findByComputerIpLocal(String ipLocal);
}
