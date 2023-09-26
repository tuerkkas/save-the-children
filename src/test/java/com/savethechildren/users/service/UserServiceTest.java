package com.savethechildren.users.service;

import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.model.DonationEntity;
import com.savethechildren.users.model.UserEntity;
import com.savethechildren.users.repository.DonationRepository;
import com.savethechildren.users.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DonationRepository donationRepository;

    @SneakyThrows
    @Test
    void should_save_UserEntity() {
        //Creation user

        UserDto userDto = UserDto.builder().id(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3")).firstName("jorge").lastName("diaz").email("jorge@gmail.com").build();
        UserEntity actualUser = new UserEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "jorge", "diaz", "jorge@gmail.com");

        //Mock repository behaviour
        when(userRepository.save(any(UserEntity.class))).thenReturn(actualUser);

        UserDto createdUser = userService.save(userDto);

        assertNotNull(createdUser);
        assertEquals("jorge", createdUser.getFirstName());
        assertEquals("diaz", createdUser.getLastName());
        assertEquals("jorge@gmail.com", createdUser.getEmail());
    }

    @Test
    void should_update_userEntity() {
        //Creation user

        UserDto userDto = UserDto.builder().id(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3")).firstName("Antonio").lastName("Garcia").email("jorge@savethechildren.com").build();
        UserEntity actualUser = new UserEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "jorge", "diaz", "jorge@gmail.com");
        UserEntity updatedUser = new UserEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "Antonio", "Garcia", "jorge@savethechildren.com");

        //Mock repository behaviour
        when(userRepository.findByIdEquals(actualUser.getId())).thenReturn(Optional.of(actualUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);

        Optional<UserDto> updatedUserDto = userService.update(userDto);

        assertNotNull(updatedUser);
        assertEquals("Antonio", updatedUserDto.isPresent() ? updatedUserDto.get().getFirstName() : null);
        assertEquals("Garcia", updatedUserDto.isPresent() ? updatedUserDto.get().getLastName() : null);
        assertEquals("jorge@savethechildren.com", updatedUserDto.isPresent() ? updatedUserDto.get().getEmail() : null);
    }

    /**
     * Used to find an user by ID. Should return an user when it is found
     */
    public void shouldReturn_StoredUser_findByID() {
        UserEntity user = new UserEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "jorge", "diaz", "jorge@savethechildren.com");
        DonationEntity donationEntity1 = new DonationEntity();
        donationEntity1.setId(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f1"));

        DonationEntity donationEntity2 = new DonationEntity();
        donationEntity2.setId(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f0"));

        //Mock repository behaviour
        //when(donationRepository.findAll()).thenReturn(Arrays.asList(donationEntity1, donationEntity2));
        when(userRepository.findByIdEquals(any(UUID.class))).thenReturn(Optional.of(user));

        Optional<UserDto> userFound = userService.findById(user.getId());

        assertNotNull(userFound);
        assertNotNull("jorge", userFound.isPresent() ? userFound.get().getFirstName() : null);
        assertNotNull("diaz", userFound.isPresent() ? userFound.get().getLastName() : null);
        assertNotNull("jorge@savethechildren.com", userFound.isPresent() ? userFound.get().getEmail() : null);
    }

    /**
     * Return true if user exist on database
     */
    @Test
    public void shouldReturn_true_existsByEmailEquals() {
        UserEntity user = Mockito.mock(UserEntity.class);

        //Mock repository behaviour
        when(userRepository.existsUserEntitiesByEmailEqualsIgnoreCase(user.getEmail())).thenReturn(true);

        Boolean isUserFound = userService.existsUserEntitiesByEmailEqualsIgnoreCase(user.getEmail());

        assertTrue(isUserFound);

    }

    /**
     * Return false if user do not exist on database
     */
    @Test
    public void shouldReturn_false_If_not_existsByEmailEquals() {
        UserEntity user = Mockito.mock(UserEntity.class);

        //Mock repository behaviour
        when(userRepository.existsUserEntitiesByEmailEqualsIgnoreCase(user.getEmail())).thenReturn(false);

        Boolean isUserFound = userService.existsUserEntitiesByEmailEqualsIgnoreCase(user.getEmail());

        assertFalse(isUserFound);

    }

    @Test
    public void should_delete_user_if_exist() {
        MockitoAnnotations.initMocks(this);
        // Arrange
        UUID userId = UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"));
        // Mock the behavior of the userRepository
        doNothing().when(userRepository).deleteById(userEntity.getId());

        // Act
        userService.deleteById(userId);
    }

}