package com.webcrawling.service.beneficiary;

import com.webcrawling.entities.beneficiary.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    Beneficiary createBeneficiary(Beneficiary beneficiary);

    List<Beneficiary> getBeneficiaries();

    Beneficiary getBeneficiaryById(Integer id);

}
