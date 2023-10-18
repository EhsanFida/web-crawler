package com.webcrawling.service;

import com.webcrawling.entities.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    Beneficiary createBeneficiary(Beneficiary beneficiary);

    List<Beneficiary> getBeneficiaries();

    Beneficiary getBeneficiaryById(Integer id);

}
