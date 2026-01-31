package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CitizenData {
    String surname;
    String name;
    String patronymic;
    String dateOfBirth;
    String passportNumber;
    String sex;
    String registrationAddress;
}
