package com.webcrawling.entities.beneficiary;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="t_beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationality;
    private String contactPhoneType;
    private String contactPhoneNumber;
    private Boolean isActive = true;
}
