package org.example.models.invalidData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestProcessDataWithoutApplid {
    private int staffid;
    private String action;
}