package com.example.patient.repository;

import com.example.patient.document.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Patient findPatientById(String id);

    List<Patient> findPatientByFirstNameAndLastNameAndAddressAndBirthDate(
            String firstName, String lastName, String address, String birthDate);

    Optional<Patient> findPatientByPhoneNumber(String phoneNumber);
}
