package com.example.patient.service;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatientByAllInformation(
            String firstName, String lastName, String dob, String sex,String address, String phone) {
        logger.debug("getPatientByAllInformation");
        if (firstName.equals("") && lastName.equals("") && dob.equals("") && sex.equals("") && address.equals("") && phone.equals("")) {
            return new ArrayList<>();
        }
        List<Patient> patientList = patientRepository.findAll();
        if (!Objects.equals(firstName, "")) {
           patientList = patientList.stream().filter(patient -> patient.getFirstName().equals(firstName)).toList();
        }
        if (!Objects.equals(lastName, "")) {
            patientList = patientList.stream().filter(patient -> patient.getLastName().equals(lastName)).toList();
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
}
