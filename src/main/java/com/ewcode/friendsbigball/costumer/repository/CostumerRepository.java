package com.ewcode.friendsbigball.costumer.repository;

import com.ewcode.friendsbigball.common.entity.Costumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
    @Query(value = """
    select  * from costumer
    where ((:filter is null) or ((lower(costumer.email) like concat('%',lower(:filter),'%') or (lower(costumer.name) like concat('%',lower(:filter),'%') ))))
    """,
            nativeQuery = true)
    Page<Costumer> findByFilter(@Param("filter") String filter, Pageable pageable);
}