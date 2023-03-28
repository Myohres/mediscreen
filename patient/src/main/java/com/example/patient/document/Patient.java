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
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String birthDate;
    @NonNull
    private String genre;
    @NonNull
    private String address;
    @NonNull
    private String phoneNumber;
}
