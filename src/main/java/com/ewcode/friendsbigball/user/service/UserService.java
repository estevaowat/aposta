package com.ewcode.friendsbigball.user.service;

import com.ewcode.friendsbigball.common.entities.User;
import com.ewcode.friendsbigball.user.repository.UserRepository;
import com.ewcode.friendsbigball.user.resource.dto.CreateUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User create(CreateUserDto user) {
        User entity = modelMapper.map(user, User.class);
        return userRepository.save(entity);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
