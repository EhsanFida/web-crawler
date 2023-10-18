package com.webcrawling.service.impl;

import com.webcrawling.entities.Beneficiary;
import com.webcrawling.repositories.BeneficiaryRepository;
import com.webcrawling.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public List<Beneficiary> getBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    @Override
    public Beneficiary getBeneficiaryById(Integer id) {
        return beneficiaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Beneficiary not Exists!"));
    }
}
