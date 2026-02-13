package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminData {
    String personalLastName;
    String personalFirstName;
    String personalMiddleName;
    String personalPhoneNumber;
    String personalNumberOfPassport;
    String dateofbirth;
}
