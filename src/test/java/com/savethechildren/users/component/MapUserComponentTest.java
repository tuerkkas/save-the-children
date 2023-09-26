package com.savethechildren.users.component;

import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MapUserComponentTest {

    //@Autowired
   //private MapUserComponent mapUserComponent;
    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"));
        userEntity.setFirstName("Jorge");
        userEntity.setLastName("Diaz");
        userEntity.setEmail("jorge@savethechildren.com");

        UserDto userDto = MapUserComponent.toDto(userEntity);

        assertEquals(userEntity.getId(),userDto.getId());
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
    }

    @Test
    void whenConvertUserDtoToUserEntity_thenCorrect() {

        UserDto userDto = UserDto.builder().id(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3")).firstName("jorge").lastName("diaz").email("jorge@savethechildren.com").build();

        UserEntity userEntity = MapUserComponent.toEntity(userDto);

        assertEquals(userDto.getId(),userEntity.getId());
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
        assertEquals(userDto.getEmail(), userEntity.getEmail());

    }
}