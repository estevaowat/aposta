package com.ewcode.friendsbigball.bet.repository;

import com.ewcode.friendsbigball.common.entity.Bet;
import com.ewcode.friendsbigball.common.entity.BetPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, BetPrimaryKey> {
  @Query(nativeQuery = true,
          value = """
  select bet.*
  from bet
  inner join game on game.start = bet.game_id
  where bet.status = 'PENDING'
  and game.status = 'FINISHED';
  """)
  List<Bet> findBetsToBook();
}
