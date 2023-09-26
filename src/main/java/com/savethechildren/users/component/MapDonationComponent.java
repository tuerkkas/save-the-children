package com.savethechildren.users.component;

import com.savethechildren.users.dto.DonationDto;
import com.savethechildren.users.model.DonationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class MapDonationComponent {

    private static ModelMapper modelMapper;

    public MapDonationComponent(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Method userd to Map UserEntity to Dto
     *
     * @param donationEntity
     * @return
     */
    public static DonationDto toDto(DonationEntity donationEntity) {
        DonationDto donationDto = null;

        if (donationEntity != null)
            donationDto = modelMapper.map(donationEntity, DonationDto.class);

        return donationDto;
    }


    /**
     * Method to Map UserDTo to UserEntity
     *
     * @param donationDto
     * @return
     */
    public static DonationEntity toEntity(DonationDto donationDto) {
        DonationEntity donationEntity = null;

        if (donationDto != null) {
            donationEntity = modelMapper.map(donationDto, DonationEntity.class);
        }
        return donationEntity;
    }


    /**
     * Method used for update existing Entity with Dto values
     *
     * @param donationEntity
     * @param donationDto
     * @return
     */
    public DonationEntity toUpdateEntity(DonationEntity donationEntity, DonationDto donationDto) {
        donationEntity = toEntity(donationDto);
        return donationEntity;
    }

}
