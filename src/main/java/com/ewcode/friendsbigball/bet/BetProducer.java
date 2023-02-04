package com.ewcode.friendsbigball.bet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BetProducer {

  Logger logger = LogManager.getLogger(BetProducer.class);

  public int produce() {
    return 1;
  }
}
