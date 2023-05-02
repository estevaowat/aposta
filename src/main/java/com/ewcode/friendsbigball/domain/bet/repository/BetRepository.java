package com.ewcode.friendsbigball.domain.bet.repository;

import com.ewcode.friendsbigball.domain.bet.Bet;
import com.ewcode.friendsbigball.domain.common.entity.BetPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, BetPrimaryKey> {
    @Query(nativeQuery = true,
            value = """
                    select bet.*
                    from bet
                    inner join match on match.start = bet.game_id
                    where bet.status = 'PENDING'
                    and match.status = 'FINISHED';
                    """)
    List<Bet> findBetsToBook();
}
