package com.savethechildren.users.repository;

import com.savethechildren.users.model.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, UUID> {


    public Optional<UserEntity> findByIdEquals(UUID id);

    public Boolean existsUserEntitiesByEmailEquals(String email);

}
