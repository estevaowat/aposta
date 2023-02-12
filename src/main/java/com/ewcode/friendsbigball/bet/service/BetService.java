package com.ewcode.friendsbigball.bet.service;

import com.ewcode.friendsbigball.bet.dto.BookBetDto;
import com.ewcode.friendsbigball.bet.repository.BetRepository;
import com.ewcode.friendsbigball.common.entity.Bet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    private final BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public List<Bet> findBetsToBook() {
        return betRepository.findBetsToBook();
    }

    public void book(BookBetDto dto) {

    }
}
