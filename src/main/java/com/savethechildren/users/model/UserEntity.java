package com.savethechildren.users.model;

import com.savethechildren.users.encrypt.AesEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public", catalog = "save-the-children-users")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name", nullable = false,length = 100)
    @Convert(converter = AesEncryptor.class)
    private String firstName;

    @Column(name = "last_name", nullable = false,length = 100)
    @Convert(converter = AesEncryptor.class)
    private String lastName;

    @Column(name = "email", nullable = false, length = 255)
    @Convert(converter = AesEncryptor.class)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DonationEntity> donations;

    public UserEntity(UUID id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
