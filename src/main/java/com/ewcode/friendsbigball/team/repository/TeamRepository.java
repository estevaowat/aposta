package com.ewcode.friendsbigball.team.repository;

import com.ewcode.friendsbigball.common.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

}
