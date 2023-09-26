package com.savethechildren.users.controller;

import com.savethechildren.users.dto.DonationDto;
import com.savethechildren.users.dto.UserDonationDto;
import com.savethechildren.users.exception.RecordNotFoundException;
import com.savethechildren.users.service.UserDonationService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/donations")
public class DonationController {

    private final UserDonationService userDonationService;

    public DonationController(UserDonationService userDonationService) {
        this.userDonationService = userDonationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DonationDto> createUserDonation(@Valid @RequestBody UserDonationDto userDonationDto) throws RecordNotFoundException {

        DonationDto donationDto = userDonationService.save(userDonationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(donationDto);
    }

}

