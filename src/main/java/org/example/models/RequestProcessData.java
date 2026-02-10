package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestProcessData {
    private int applId;
    private int staffid;
    private String action;
}