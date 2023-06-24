package twitter.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twitter.domain.User;
import twitter.repository.UserRepository;
import twitter.repository.impl.UserRepositoryImpl;
import twitter.service.UserService;

import javax.persistence.Persistence;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    UserService userService;
    UserRepository userRepository;
    UserService realUserService;
    UserRepository realUserRepository;


    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

        realUserRepository=
                new UserRepositoryImpl(Persistence.createEntityManagerFactory("twitter").createEntityManager());
        realUserService=new UserServiceImpl(userRepository);
    }

    @Test
    void itShouldReturnValidateCredentialByValidUsername() {
        User newUser = new User();
        newUser.setUsername("aminty");
        newUser.setPassword("1234");

        given(userRepository.getUserByUsername(anyString())).willReturn(Optional.of(newUser));

        boolean result = userService.isValidCredential("aminty", "1234");

        verify(userRepository).getUserByUsername("aminty");

        assertTrue(result);
    }

    @Test
    void itShouldReturnValidateCredentialByValidEmail() {
        User newUser = new User();
        newUser.setEmail("amin@gmail.com");
        newUser.setPassword("1234");

        when(userRepository.getUserByEmail(anyString())).thenReturn(Optional.of(newUser));

        boolean result = userService.isValidCredential("amin@gmail.com","1234");

        verify(userRepository).getUserByEmail("amin@gmail.com");

        assertTrue(result);

    }

    @Test
    void itShouldReturnValidateCredentialByInvalidEmail() {
        User newUser = new User();
        newUser.setEmail("amin@gmail.com0");

        when(userRepository.getUserByEmail(anyString())).thenReturn(Optional.empty());

        boolean result = userService.isValidCredential("amin@gmail.com0","1234");

        verify(userRepository).getUserByEmail("amin@gmail.com0");

        assertFalse(result);

    }

    @Test
    void itShouldReturnValidateCredentialByInvalidUsername() {
        User newUser = new User();
        newUser.setEmail("aminty0");

        when(userRepository.getUserByUsername(anyString())).thenReturn(Optional.empty());

        boolean result = userService.isValidCredential("aminty0","1234");

        verify(userRepository).getUserByUsername("aminty0");

        assertFalse(result);

    }

    @Test
    void itShouldReturnValidateCredentialByValidUsernameReal() {


        String username="aminty";

        boolean result = realUserService.isValidCredential("aminty", "1234");

        assertTrue(result);

    }
}