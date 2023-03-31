package com.example.patient.controller;

import com.example.patient.service.PatientService;
import com.example.patient.document.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:4200")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientService patientService;

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getPatientByAllInformation(
           final String firstName,
           final String lastName,
           final String dob,
           final String sex,
           final String address,
           final String phone) {
        logger.info("GET/firstName/" + firstName + "/lastName/" + lastName + "/birthDate/" + dob
                + "/sex/" + sex + "/address/" + address + "/phone/" + phone);
        try {
            return ResponseEntity.ok(
                    patientService.getPatientByAllInformation(firstName, lastName, dob, sex, address, phone));
        } catch (NoSuchElementException e) {
            logger.error("getPatientByPhoneNumber error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(
            final Patient patient) {
        logger.info("POST/" + patient.getFirstName());
        try {
            return ResponseEntity.ok(patientService.savePatient(patient));
        } catch (NoSuchElementException e) {
            logger.error("savePatient error : " + e);
            return ResponseEntity.notFound().build();
        }
    }
}
