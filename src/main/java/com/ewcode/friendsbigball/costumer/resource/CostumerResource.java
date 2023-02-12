package com.ewcode.friendsbigball.costumer.resource;

import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.config.log.LogInfo;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.costumer.service.CostumerService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("costumer")

public class CostumerResource {
    final Logger logger = LogManager.getLogger(CostumerResource.class);
    private final CostumerService costumerService;
    private final LogInfo logInfo;

    @Autowired
    public CostumerResource(CostumerService costumerService, LogInfo logInfo) {
        this.costumerService = costumerService;
        this.logInfo = logInfo;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateCostumerDto costumer) {
        logger.info(logInfo.getCorrelationLog() + "Creating costumer");

        costumerService.create(costumer);
        logger.info(logInfo.getCorrelationLog() + "Costumer created");
        return ResponseEntity.ok("costumer created");
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Costumer>> create(@PathVariable int id) {
        Optional<Costumer> costumer = costumerService.findById(id);
        return ResponseEntity.ok(costumer);
    }

    @GetMapping("filter")
    public ResponseEntity<Page<Costumer>> findByFilter(
            @RequestParam String filter, Pageable pageable) {
        Page<Costumer> costumers = costumerService.findByFilterPaginated(filter, pageable);
        return ResponseEntity.ok(costumers);
    }
}
