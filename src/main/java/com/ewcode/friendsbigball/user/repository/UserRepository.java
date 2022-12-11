package com.ewcode.friendsbigball.user.repository;

import com.ewcode.friendsbigball.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
