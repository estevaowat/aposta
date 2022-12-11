package com.ewcode.friendsbigball.user.service;

import com.ewcode.friendsbigball.common.entities.User;
import com.ewcode.friendsbigball.user.repository.UserRepository;
import com.ewcode.friendsbigball.user.resource.dto.CreateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    private UserService service;
    @Mock
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        service = new UserService(userRepository, modelMapper);
    }

    @Test
    void shouldCreateAnUser() {
         
        CreateUserDto user = createGenericUser();

        User userMocked = new User();
        userMocked.setName(user.getName());
        userMocked.setEmail(user.getEmail());

        Mockito.when(userRepository.save(Mockito.any()))
                .thenReturn(userMocked);

        User userCreated = service.create(user);
        Assertions.assertEquals(user.getName(), userCreated.getName());
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
    }

    private CreateUserDto createGenericUser() {
        CreateUserDto genericUser = new CreateUserDto();
        genericUser.setName("Canarinho Pistola");
        genericUser.setEmail("canarinho_pistola@cbf.com.br");

        return genericUser;
    }

}