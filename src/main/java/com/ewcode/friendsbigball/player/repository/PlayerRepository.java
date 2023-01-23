package com.ewcode.friendsbigball.player.repository;

import com.ewcode.friendsbigball.common.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
