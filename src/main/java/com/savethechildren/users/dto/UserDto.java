package com.savethechildren.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    @NotBlank(message = " is required")
    @Size(min = 1, max = 100, message = " lenght is not valid >=1 and <= 100")
    private String firstName;

    @NotBlank(message = " is required")
    @Size(min = 1, max = 100, message = " lenght is not valid >=1 and <= 100")
    private String lastName;

    @NotBlank(message = " is required")
    @Email(message = " has not a valid format")
    @Size(min = 1, max = 255, message = " length is not valid >=1 and <=255")
    private String email;

    List<DonationDto> donationDtoList;

}
