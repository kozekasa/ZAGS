package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserData {
    String surname;
    String name;
    String patronymic;
    String telephoneNumber;
    String passportNumber;
    String registrationAddress;
}
