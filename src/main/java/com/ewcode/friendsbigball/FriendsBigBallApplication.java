package com.ewcode.friendsbigball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsBigBallApplication {

    private static final Logger logger = LogManager.getLogger(FriendsBigBallApplication.class.getName());

    public static void main(String[] args) {
        
        SpringApplication.run(FriendsBigBallApplication.class, args);
    }
}
