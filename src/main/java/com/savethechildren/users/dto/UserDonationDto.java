package com.savethechildren.users.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class UserDonationDto {

    UUID userId;

    @NotNull(message = "is required")
    @Positive(message = " must be a positive quantity")
    BigDecimal donation;
    @NotBlank(message = "is required")
    @Size(min = 3, max = 3, message = " 3 digits required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = " format not valid")
    String currency;
}
