package com.savethechildren.users.service;

import com.savethechildren.users.dto.DonationDto;
import com.savethechildren.users.dto.UserDonationDto;
import com.savethechildren.users.exception.RecordNotFoundException;
import com.savethechildren.users.model.DonationEntity;
import com.savethechildren.users.model.UserEntity;
import com.savethechildren.users.repository.DonationRepository;
import com.savethechildren.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDonationServiceTest {
    @InjectMocks
    private UserDonationService userDonationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    DonationRepository donationRepository;


    @Test
    void should_save_UserEntityAndDonationEntity() throws RecordNotFoundException {
        //Creation user

        UserDonationDto userDonationDto = UserDonationDto.builder().userId(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3")).donation(new BigDecimal(100)).currency("EUR").build();


        UserEntity actualUser = new UserEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "jorge", "diaz", "jorge@gmail.com");
        DonationEntity donationActual = new DonationEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaabdc15f3"), actualUser, new BigDecimal(100), new Timestamp(new Date().getTime()), "EUR");
        //Mock repository behaviour

        when(userRepository.findByIdEquals(any(UUID.class))).thenReturn(Optional.of(actualUser));
        when(donationRepository.save(any(DonationEntity.class))).thenReturn(donationActual);

        DonationDto donationDtoCreated = userDonationService.save(userDonationDto);

        assertNotNull(donationDtoCreated);
        /*assertEquals("jorge", createdUser.getFirstName());
        assertEquals("diaz", createdUser.getLastName());
        assertEquals("jorge@gmail.com", createdUser.getEmail());*/
    }
}