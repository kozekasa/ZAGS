package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MarriageRegistrationServiceData {
    String dateOfRegistration;
    String newSurname;
    String spouseSurname;
    String spouseName;
    String spousePatronymic;
    String spouseDateOfBirth;
    String spousePassportNumber;
}
