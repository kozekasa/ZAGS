package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserData {
    String surname;
    String name;
    String patronymic;
    String telephoneNumber;
    String passportNumber;
    String registrationAddress;
}
