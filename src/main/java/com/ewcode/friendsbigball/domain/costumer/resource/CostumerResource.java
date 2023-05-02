package com.ewcode.friendsbigball.domain.costumer.resource;

import com.ewcode.friendsbigball.app.log.LogBuilder;
import com.ewcode.friendsbigball.app.log.Logger;
import com.ewcode.friendsbigball.domain.common.entity.Costumer;
import com.ewcode.friendsbigball.domain.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.domain.costumer.service.CostumerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("costumer")
public class CostumerResource {
    private final CostumerService costumerService;
    private final Logger logger;
    LogBuilder logBuilder = new LogBuilder().className(CostumerService.class.getName());

    @Autowired
    public CostumerResource(CostumerService costumerService, Logger logger) {
        this.costumerService = costumerService;
        this.logger = logger;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateCostumerDto costumer) {
        logger.info(logBuilder.message("Creating costumer"));
        costumerService.create(costumer);
        logger.info(logBuilder.message("Costumer created"));

        return ResponseEntity.ok("costumer created");
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Costumer>> create(@PathVariable int id) {
        Optional<Costumer> costumer = costumerService.findById(id);
        return ResponseEntity.ok(costumer);
    }

    @GetMapping("filter")
    public ResponseEntity<Page<Costumer>> findByFilter(@RequestParam String filter, Pageable pageable) {
        Page<Costumer> costumers = costumerService.findByFilterPaginated(filter, pageable);
        return ResponseEntity.ok(costumers);
    }
}
