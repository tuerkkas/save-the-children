package com.savethechildren.users.model;

import com.savethechildren.users.encrypt.AesEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "donations", schema = "public", catalog = "save-the-children-users")
public class DonationEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @Column(name = "amount", length = 1024 , nullable=false)
    @Convert(converter = AesEncryptor.class)
    private BigDecimal amount;

    @Column(name = "donation_date" , nullable=false)
    @Convert(converter = AesEncryptor.class)
    private Timestamp donationDate;

    @Column(name = "currency_code" , nullable=false)
  //  @Enumerated(EnumType.STRING)
    @Convert(converter = AesEncryptor.class)
    private String currencyCode;

}
