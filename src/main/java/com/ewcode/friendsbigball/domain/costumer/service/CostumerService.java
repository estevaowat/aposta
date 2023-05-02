package com.ewcode.friendsbigball.domain.costumer.service;

import com.ewcode.friendsbigball.domain.common.entity.Costumer;
import com.ewcode.friendsbigball.domain.costumer.resource.dto.CreateCostumerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CostumerService {
    Costumer create(CreateCostumerDto costumer);

    Optional<Costumer> findById(int id);

    List<Costumer> findAll();

    Page<Costumer> findByFilterPaginated(String filter, Pageable pageable);
}
