package com.webcrawling.service;

import com.webcrawling.entities.beneficiary.Beneficiary;
import com.webcrawling.repositories.beneficiary.BeneficiaryRepository;
import com.webcrawling.service.beneficiary.BeneficiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class BeneficiaryServiceTest {
    @Autowired
    BeneficiaryService beneficiaryService;

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @BeforeEach
    public void setup() {
        beneficiaryRepository.deleteAll();

        Beneficiary bene = new Beneficiary();
        bene.setFirstName("Ehsan");
        bene.setMiddleName("");
        bene.setLastName("Fida");
        bene.setContactPhoneType("mobile");
        bene.setContactPhoneNumber("03455165351");
        bene.setIsActive(true);
        beneficiaryService.createBeneficiary(bene);
    }

    @Test
    public void testGetAllBeneficiaries() {
        assertEquals(1, beneficiaryService.getBeneficiaries().size());
    }

    @Test
    public void testGetBeneficiaryById() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setFirstName("Babar");
        beneficiary.setLastName("Taimour");
        beneficiary.setIsActive(true);
        Beneficiary beneficiary1 = beneficiaryService.createBeneficiary(beneficiary);

        assertNotNull(beneficiaryService.getBeneficiaryById(beneficiary1.getId()));
    }
}
