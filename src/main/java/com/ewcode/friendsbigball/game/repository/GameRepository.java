package com.ewcode.friendsbigball.game.repository;

import com.ewcode.friendsbigball.common.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {


}
