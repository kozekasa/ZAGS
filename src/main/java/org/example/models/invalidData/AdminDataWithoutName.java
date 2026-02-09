package org.example.models.invalidData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminDataWithoutName {
    String personalLastName;
    String personalMiddleName;
    String personalPhoneNumber;
    String personalNumberOfPassport;
    String dateofbirth;
}
