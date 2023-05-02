package com.ewcode.friendsbigball.domain.costumer.service.impl;

import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.domain.common.entity.Costumer;
import com.ewcode.friendsbigball.domain.costumer.repository.CostumerRepository;
import com.ewcode.friendsbigball.domain.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.domain.costumer.service.CostumerService;
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
    private final Logger logger;
    private final LogBuilder logBuilder = new LogBuilder().className(CostumerServiceMySQLImpl.class.getName());

    public CostumerServiceMySQLImpl(CostumerRepository costumerRepository, ModelMapper modelMapper, Logger logger) {
        this.costumerRepository = costumerRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public Costumer create(CreateCostumerDto costumer) {
        logger.info(logBuilder.message("Entering in service"));
        logger.info(logBuilder.message("Mapping entity"));

        Costumer entity = modelMapper.map(costumer, Costumer.class);
        logger.info(logBuilder.message("Entity mapped"));

        Costumer costumerSaved = costumerRepository.save(entity);
        logger.info(logBuilder.message("Costumer saved"));

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
