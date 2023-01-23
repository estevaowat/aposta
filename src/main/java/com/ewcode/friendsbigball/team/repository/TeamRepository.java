package com.ewcode.friendsbigball.team.repository;

import com.ewcode.friendsbigball.common.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

}
