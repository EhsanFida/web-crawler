package com.webcrawling.controller;

import com.webcrawling.entities.Beneficiary;
import com.webcrawling.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/beneficiary")
@Controller
public class BeneficiaryController {

    @Autowired
    BeneficiaryService beneficiaryService;

  //@PostMapping
    @MutationMapping("createBeneficiary")
    private Beneficiary createBeneficiary(@Argument (name = "beneficiary") Beneficiary beneficiary){
        return beneficiaryService.createBeneficiary(beneficiary);
    }

//    @GetMapping
    @QueryMapping("allBeneficiaries")
    private List<Beneficiary> getBeneficiaries(){
        return beneficiaryService.getBeneficiaries();
    }

//    @GetMapping("/{id}")
    @QueryMapping("getBeneficiary")
    private Beneficiary getBeneficiary(@Argument Integer id){//@PathVariable remove it because not id will be a argument
        return beneficiaryService.getBeneficiaryById(id);
    }
}
