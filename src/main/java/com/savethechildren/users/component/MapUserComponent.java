package com.savethechildren.users.component;

import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class MapUserComponent {

    private static ModelMapper modelMapper;

    public MapUserComponent(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Method userd to Map UserEntity to Dto
     *
     * @param userEntity
     * @return
     */
    public static UserDto toDto(UserEntity userEntity) {
        UserDto userDto = null;

        if (userEntity != null)
            userDto = modelMapper.map(userEntity, UserDto.class);

        return userDto;
    }


    /**
     * Method to Map UserDTo to UserEntity
     *
     * @param userDto
     * @return
     */
    public static UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = null;

        if (userDto != null) {
            userEntity = modelMapper.map(userDto, UserEntity.class);
        }
        return userEntity;
    }


    /**
     * Method used for update existing Entity with Dto values
     *
     * @param userEntity
     * @param userDto
     * @return
     */
    public static UserEntity toUpdateEntity(UserEntity userEntity, UserDto userDto) {
        userEntity = toEntity(userDto);
        return userEntity;
    }

}
