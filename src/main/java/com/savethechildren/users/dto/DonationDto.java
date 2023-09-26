package com.savethechildren.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savethechildren.users.encrypt.AesEncryptor;
import com.savethechildren.users.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationDto {

    private UUID id;

    @JsonIgnore
    private UserDto user;

    private BigDecimal amount;

    private Timestamp donationDate;

    private String currencyCode;

}
