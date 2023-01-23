package com.ewcode.friendsbigball.costumer.resource;

import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.costumer.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/costumer")
public class CostumerResource {
  private final CostumerService costumerService;

  @Autowired
  public CostumerResource(CostumerService costumerService) {
    this.costumerService = costumerService;
  }

  @PostMapping
  public ResponseEntity<String> create(CreateCostumerDto costumer) {
    costumerService.create(costumer);
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
