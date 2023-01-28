package com.ewcode.friendsbigball.bet.resource;

import com.ewcode.friendsbigball.bet.BetProducer;
import com.ewcode.friendsbigball.bet.dto.CreateBetDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bet")
public class BetResource {

  private final BetProducer betProducer;

  public BetResource(BetProducer betProducer) {
    this.betProducer = betProducer;
  }

  @PostMapping
  public ResponseEntity<Void> sendBetsToBook() throws JsonProcessingException {
    betProducer.produce();
    return ResponseEntity.ok().build();
  }

  @PostMapping("create")
  public ResponseEntity<Void> createBet(CreateBetDTO createBetDTO) {
    return ResponseEntity.ok().build();
  }
}
