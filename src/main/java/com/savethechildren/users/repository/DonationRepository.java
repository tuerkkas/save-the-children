package com.savethechildren.users.repository;

import com.savethechildren.users.model.DonationEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DonationRepository extends ListCrudRepository<DonationEntity, UUID> {


}
