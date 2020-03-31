package com.makolyte.springsqsstarter.repository;

import com.makolyte.springsqsstarter.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    boolean existsByAwsMessageId(String awsMessageId);
}
