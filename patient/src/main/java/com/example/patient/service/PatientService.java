package com.example.patient.service;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatients() {
        logger.debug("getPatients");
        return patientRepository.findAll();
    }

    public Patient getPatientByPhoneNumber(String phoneNumber)  {
        logger.info("getPatientByPhoneNumber");
        Optional<Patient> patient = patientRepository.findPatientByPhoneNumber(phoneNumber);
        if (patient.isEmpty()) {
            throw new NoSuchElementException("No such patient with " +phoneNumber);
        }
        else {
            return patient.get();
        }
    }

    public List<Patient> getPatientByNameAddressBirthDate(String firstName, String lastName, String address, String birthDate)  {
        logger.debug("getPatientByNameAddressBirthDatePhone");
        List<Patient> patient = patientRepository
                .findPatientByFirstNameAndLastNameAndAddressAndBirthDate(firstName, lastName, address, birthDate);
        if (patient.isEmpty()) {
            throw new NoSuchElementException("No such patient");
        }
        else {
            return patient;
        }
    }

    public Patient savePatient(Patient patient) {
        logger.debug("savePatient");
        return patientRepository.save(patient);
    }


}
