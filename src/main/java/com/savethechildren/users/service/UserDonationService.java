package com.savethechildren.users.service;

import com.savethechildren.users.component.MapDonationComponent;
import com.savethechildren.users.component.MapUserComponent;
import com.savethechildren.users.dto.DonationDto;
import com.savethechildren.users.dto.UserDonationDto;
import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.exception.RecordNotFoundException;
import com.savethechildren.users.model.DonationEntity;
import com.savethechildren.users.model.UserEntity;
import com.savethechildren.users.repository.DonationRepository;
import com.savethechildren.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserDonationService {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    public UserDonationService(UserRepository userRepository, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
    }

    public DonationDto save(UserDonationDto userDonationDto) throws RecordNotFoundException {
        DonationDto donationDto = null;
        UserDto userDto = null;
        DonationEntity donationEntityCreated = null;

        UserEntity userEntity = userRepository.findByIdEquals(userDonationDto.getUserId()).orElseThrow(() -> new RecordNotFoundException("User doesn't exist on the database"));

        donationEntityCreated = createDonation(userDonationDto, userEntity);
        donationDto = MapDonationComponent.toDto(donationEntityCreated);
        donationDto.setUser(MapUserComponent.toDto(donationEntityCreated.getUser()));

        return donationDto;
    }

    private DonationEntity createDonation(UserDonationDto userDonationDto, UserEntity userEntity) {
        DonationEntity donationEntity = new DonationEntity();
        donationEntity.setUser(userEntity);
        donationEntity.setDonationDate(new Timestamp(new Date().getTime()));
        donationEntity.setAmount(userDonationDto.getDonation());
        donationEntity.setCurrencyCode(userDonationDto.getCurrency());
        return donationRepository.save(donationEntity);
    }
}
