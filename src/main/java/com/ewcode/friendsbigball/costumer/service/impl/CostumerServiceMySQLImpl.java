package com.ewcode.friendsbigball.costumer.service.impl;

import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.config.log.LogInfo;
import com.ewcode.friendsbigball.costumer.repository.CostumerRepository;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.costumer.service.CostumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerServiceMySQLImpl implements CostumerService {
  private final CostumerRepository costumerRepository;
  private final ModelMapper modelMapper;
  private final LogInfo logInfo;
  Logger logger = LogManager.getLogger(CostumerServiceMySQLImpl.class);

  public CostumerServiceMySQLImpl(
          CostumerRepository costumerRepository, ModelMapper modelMapper, LogInfo logInfo) {
    this.costumerRepository = costumerRepository;
    this.modelMapper = modelMapper;
    this.logInfo = logInfo;
  }

  @Override
  public Costumer create(CreateCostumerDto costumer) {
    logger.info(logInfo.getCorrelationLog() + "Entering in service");
    logger.info(logInfo.getCorrelationLog() + "Mapping entity");
    Costumer entity = modelMapper.map(costumer, Costumer.class);
    logger.info(logInfo.getCorrelationLog() + "Entity mapped");
    Costumer costumerSaved = costumerRepository.save(entity);
    logger.info(logInfo.getCorrelationLog() + "Costumer saved");
    return costumerSaved;
  }

  @Override
  public Optional<Costumer> findById(int id) {
    return costumerRepository.findById(id);
  }

  @Override
  public List<Costumer> findAll() {
    return costumerRepository.findAll();
  }

  @Override
  public Page<Costumer> findByFilterPaginated(String filter, Pageable pageable) {
    return costumerRepository.findByFilter(filter, pageable);
  }
}
