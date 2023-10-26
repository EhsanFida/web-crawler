package com.webcrawling;

import com.webcrawling.entities.beneficiary.Beneficiary;
import com.webcrawling.service.beneficiary.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCrawlingApplication implements CommandLineRunner {

	@Autowired
	private BeneficiaryService beneficiaryService;

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Beneficiary bene1 = new Beneficiary();
		bene1.setFirstName("Ehsan");
		bene1.setMiddleName("Fida");
		bene1.setLastName("Tanoli");
		bene1.setContactPhoneType("Mobile");
		bene1.setContactPhoneNumber("03455d16d5351");
		bene1.setIsActive(true);
		beneficiaryService.createBeneficiary(bene1);

		Beneficiary bene2 = new Beneficiary();
		bene2.setFirstName("Waleed");
		bene2.setMiddleName("Awan");
		bene2.setLastName("Mithay");
		bene2.setContactPhoneType("Mobile");
		bene2.setContactPhoneNumber("03456987456");
		bene2.setIsActive(true);
		beneficiaryService.createBeneficiary(bene2);

		Beneficiary bene3 = new Beneficiary();
		bene3.setFirstName("Taimour");
		bene3.setMiddleName("Babar");
		bene3.setLastName("babri");
		bene3.setContactPhoneType("Mobile");
		bene3.setContactPhoneNumber("03348979074");
		bene3.setIsActive(true);
		beneficiaryService.createBeneficiary(bene3);
	}
}
