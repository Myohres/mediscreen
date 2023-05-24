package com.example.patient.service;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(String id) {
        logger.debug("getPatientById");
        return patientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such patient with id " + id));
    }

    public List<Patient> getPatientByAllInformation(
            String family, String given, String dob, String sex,String address, String phone) {
        logger.debug("getPatientByAllInformation");
        if (family.equals("") && given.equals("") && dob.equals("") && sex.equals("") && address.equals("") && phone.equals("")) {
            return new ArrayList<>();
        }
        List<Patient> patientList = patientRepository.findAll();
        if (!Objects.equals(family, "")) {
            patientList = patientList.stream().filter(patient -> patient.getFamily().equals(family)).toList();
        }
        if (!Objects.equals(given, "")) {
            patientList = patientList.stream().filter(patient -> patient.getGiven().equals(given)).toList();
        }
        if (!Objects.equals(dob, "")) {
            patientList = patientList.stream().filter(patient -> patient.getDob().equals(dob)).toList();
        }
        if (!Objects.equals(sex, "")) {
            patientList = patientList.stream().filter(patient -> patient.getSex().equals(sex)).toList();
        }
        if (!Objects.equals(address, "")) {
            patientList = patientList.stream().filter(patient -> patient.getAddress().equals(address)).toList();
        }
        if (!Objects.equals(phone, "")) {
            patientList = patientList.stream().filter(patient -> patient.getPhone().equals(phone)).toList();
        }
        if (patientList.isEmpty()) {
            throw new NoSuchElementException("No patient found");
        }
        return patientList;
    }

    public Patient savePatient(Patient patient) {
        logger.debug("savePatient");
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String id,Patient patient) {
        logger.debug("updatePatient");
        Patient patientToUpdate = getPatientById(id);
        patientToUpdate.setFamily(patient.getFamily());
        patientToUpdate.setGiven(patient.getGiven());
        patientToUpdate.setSex(patient.getSex());
        patientToUpdate.setDob(patient.getDob());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());
        return patientRepository.save(patientToUpdate);
    }

    public void validationPatient(Patient patient) throws NullPointerException {
        String errorMessage1 = "";
        String errorMessage2 = "";
        String errorMessage3 = "";
        String errorMessage4 = "";
        boolean error = false;
        if (patient.getFamily() == null || patient.getFamily().isEmpty() || patient.getFamily().isBlank()) {
            errorMessage1 = "family";
            error = true;
        }
        if (patient.getGiven() == null || patient.getGiven().isEmpty() || patient.getGiven().isBlank()) {
            errorMessage2 = "given";
            error = true;
        }
        if (patient.getDob() == null || patient.getDob().isEmpty() || patient.getDob().isBlank()) {
            errorMessage3 = "Birthdate";
            error = true;
        }
        if (patient.getSex() == null || patient.getSex().isEmpty() || patient.getSex().isBlank()) {
            errorMessage4 = "sex";
            error = true;
        }
        if (error) {
            throw new NullPointerException(errorMessage1 + " " + errorMessage2 + " " + errorMessage3 + " " + errorMessage4 + " mandatory");
        }


    }
}
