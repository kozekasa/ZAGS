package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BirthRegistrationServiceData {
    String placeOfBirth;
    String mother;
    String father;
    String grandma;
    String grandpa;
}
