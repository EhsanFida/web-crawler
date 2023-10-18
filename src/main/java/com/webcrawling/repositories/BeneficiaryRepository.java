package com.webcrawling.repositories;

import com.webcrawling.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
}
