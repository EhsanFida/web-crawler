package com.webcrawling.repositories.beneficiary;

import com.webcrawling.entities.beneficiary.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
}
