package com.ewcode.friendsbigball.bet;

import com.ewcode.friendsbigball.common.entities.Game;
import com.ewcode.friendsbigball.common.entities.User;
import com.ewcode.friendsbigball.config.GsonConfig;
import com.ewcode.friendsbigball.game.service.GameService;
import com.ewcode.friendsbigball.user.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BetProducerTest {

    private BetProducer betProducer;

    @Mock
    private UserService userService;

    @Mock
    private GameService gameService;

    private Gson gson;

    @BeforeEach
    void setUp() {
        GsonConfig gsonConfig = new GsonConfig();
        gson = gsonConfig.gsonBean();
        betProducer = new BetProducer(userService, gameService, gson);

    }

    @Test
    void shouldProduceMessages() throws Exception {
        User user = new User();
        Game game = new Game();
        game.setStart(LocalDateTime.now());

        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
        Mockito.when(gameService.findById(Mockito.anyInt())).thenReturn(Optional.of(game));
        betProducer.produce();
    }
}