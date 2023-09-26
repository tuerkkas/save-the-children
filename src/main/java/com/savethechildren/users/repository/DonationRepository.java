package com.savethechildren.users.repository;

import com.savethechildren.users.model.DonationEntity;
import com.savethechildren.users.model.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonationRepository extends ListCrudRepository<DonationEntity, UUID> {

   // public List<DonationEntity> findAllByUserOrderByDonationDateDonationDateDesc(UUID userId);


}
