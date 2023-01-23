package com.ewcode.friendsbigball.costumer.service;

import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.costumer.repository.CostumerRepository;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

  private final CostumerRepository costumerRepository;

  private final ModelMapper modelMapper;

  public CostumerService(CostumerRepository costumerRepository, ModelMapper modelMapper) {
    this.costumerRepository = costumerRepository;
    this.modelMapper = modelMapper;
  }

  public Costumer create(CreateCostumerDto costumer) {
    Costumer entity = modelMapper.map(costumer, Costumer.class);
    return costumerRepository.save(entity);
  }

  public Optional<Costumer> findById(int id) {
    return costumerRepository.findById(id);
  }

  public List<Costumer> findAll() {
    return costumerRepository.findAll();
  }

  public Page<Costumer> findByFilterPaginated(String filter, Pageable pageable) {
    return costumerRepository.findByFilter(filter, pageable);
  }
}
