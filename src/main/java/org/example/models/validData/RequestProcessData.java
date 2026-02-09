package org.example.models.validData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestProcessData {
    private int applId;
    private int staffid;
    private String action; // Например, "approve" или "reject"
}