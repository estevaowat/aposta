package com.ewcode.friendsbigball.game.repository;

import com.ewcode.friendsbigball.common.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface GameRepository extends JpaRepository<Game, LocalDateTime> {


}
