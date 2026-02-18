package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationData {
    private int applicationid;
    private String statusofapplication;
    private int staffid;
    private int total;
    private int size;
    public int citizenId;
    public int applicantId;
    public String kind;
}