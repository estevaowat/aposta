package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.common.entities.Bet;
import com.ewcode.friendsbigball.common.entities.Game;
import com.ewcode.friendsbigball.common.entities.User;
import com.ewcode.friendsbigball.common.entities.enums.BetStatus;
import com.ewcode.friendsbigball.game.service.GameService;
import com.ewcode.friendsbigball.user.service.UserService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class BetProducer {

    Logger logger = LogManager.getLogger(BetProducer.class);
    private final UserService userService;
    private final GameService gameService;
    private final Gson gson;

    public BetProducer(UserService userService, GameService gameService, Gson gson) {
        this.userService = userService;
        this.gameService = gameService;
        this.gson = gson;
    }

    public void produce() throws Exception {
        logger.info("Producing bet messages");

        final int numberBets = 10;
        List<Bet> bets = getBets(numberBets);

        for(Bet bet : bets) {
            String message = gson.toJson(bet);
            send(message);
        }


    }

    public void send(String message) {
        logger.info(message);
        logger.info("Finished bet messages");

    }

    private List<Bet> getBets(int numberBets) throws Exception {
        List<Bet> bets = new ArrayList<>();

        for(int i = 0; i < numberBets; i++) {
            Bet bet = createBet();
            bets.add(bet);
        }

        return bets;
    }

    private Bet createBet() throws Exception {
        final int userId = 1;
        Optional<User> userOpt = userService.findById(userId);

        if(userOpt.isEmpty()) {
            throw new Exception("User not found!");
        }

        final int gameId = 1;
        Optional<Game> gameOpt = gameService.findById(gameId);

        if(gameOpt.isEmpty()) {
            throw new Exception("Game not found!");
        }

        String randomResult = createRandomResult();

        Bet bet = new Bet();
        bet.setUser(userOpt.get());
        bet.setGame(gameOpt.get());
        bet.setResult(randomResult);
        bet.setPoints(null);
        bet.setStatus(BetStatus.PENDING);

        return bet;

    }

    private String createRandomResult() {
        Random rand = new Random();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(rand.nextInt(5));
        strBuilder.append("x");
        strBuilder.append(rand.nextInt(5));

        return strBuilder.toString();
    }

}
