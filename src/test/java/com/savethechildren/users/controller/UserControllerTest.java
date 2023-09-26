package com.savethechildren.users.controller;

import com.savethechildren.users.repository.UserRepository;
import com.savethechildren.users.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserControllerTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

}