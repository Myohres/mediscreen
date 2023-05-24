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

    /** Id patient. */
    @Id
    private String id;
    /** FamilyName patient. */
    @NonNull
    private String family = "";
    /** GivenName patient. */
    @NonNull
    private String given = "";
    /** BirthDate patient. */
    @NonNull
    private String dob = "";
    /** Gender patient. */
    @NonNull
    private String sex = "";
    /** Address patient. */
    private String address = "";
    /** PhoneNumber patient. */
    private String phone = "";
}
