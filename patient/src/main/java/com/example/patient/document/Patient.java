package com.example.patient.document;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient")
@Getter
@Setter
public class Patient {

    @Id
    private String id;
    @NonNull
    private String family = "";
    @NonNull
    private String given = "";
    @NonNull
    private String dob = "";
    @NonNull
    private String sex= "";
    private String address = "";
    private String phone= "";
}
