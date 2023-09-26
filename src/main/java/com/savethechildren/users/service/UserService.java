package com.savethechildren.users.service;


import com.savethechildren.users.component.MapDonationComponent;
import com.savethechildren.users.component.MapUserComponent;
import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.exception.RecordExistsException;
import com.savethechildren.users.model.UserEntity;
import com.savethechildren.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        // this.mapUserComponent = mapUserComponent;
    }

    /**
     * SAve a new user into the database. First we verify if the user exist on the database searching by email.
     *
     * @param userDto
     * @return Dto object created into the database
     * @throws RecordExistsException //will be thrown if email exist on the database
     */
    @Transactional
    public UserDto save(UserDto userDto) throws RecordExistsException {
        //Validate user doesnt exist
        if (userRepository.existsUserEntitiesByEmailEquals(userDto.getEmail())) {
            throw new RecordExistsException(new StringBuilder("User exist on database with email= ").append(userDto.getEmail()).toString());
        }
        return MapUserComponent.toDto(userRepository.save(MapUserComponent.toEntity(userDto)));
    }

    /**
     * Find into the database an user by UUID
     *
     * @param id
     * @return Dto of object stored on the database
     */
    @Transactional

    public Optional<UserDto> findById(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findByIdEquals(id);
        UserDto userDto = null;
        if (userEntity.isPresent()) {
            userDto = MapUserComponent.toDto(userEntity.get());
            userDto.setDonationDtoList(userEntity.get().getDonations().stream().map(donationEntity -> MapDonationComponent.toDto(donationEntity)).collect(Collectors.toList()));
        }
        return Optional.ofNullable(userDto);
    }

    /**
     * Method used to verify if the email exist on the user datatable
     *
     * @param email
     * @return true if Exist, false if not
     */
    @Transactional
    public Boolean existsUserEntitiesByEmailEquals(String email) {
        return userRepository.existsUserEntitiesByEmailEquals(email);
    }

    /**
     * Validate user exist on database and update.
     *
     * @param userDto
     * @return UserDto with information updated or null
     */
    @Transactional
    public Optional<UserDto> update(UserDto userDto) {
        //Validate record exist
        Optional<UserEntity> userEntityToUpdate = userRepository.findByIdEquals(userDto.getId());
        UserDto userDtoUpdated = null;
        if (userEntityToUpdate.isPresent()) { //Update model with new values and return object updated
            userDtoUpdated = MapUserComponent.toDto(userRepository.save(MapUserComponent.toUpdateEntity(userEntityToUpdate.get(), userDto)));
        }
        //return null or the updated value
        return Optional.ofNullable(userDtoUpdated);
    }


    /**
     * Search all users stored into the database.
     *
     * @return list of all users stored into the databse
     */
    @Transactional
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userEntity -> {
            UserDto userDto = MapUserComponent.toDto(userEntity);
            userDto.setDonationDtoList(userEntity.getDonations().stream().map(donationEntity -> MapDonationComponent.toDto(donationEntity)).collect(Collectors.toList()));
            return userDto;
        }).collect(Collectors.toList());
    }

    /**
     * Detele an users if exist on the database
     *
     * @param id UUID user to delete
     */
    @Transactional
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }
}
