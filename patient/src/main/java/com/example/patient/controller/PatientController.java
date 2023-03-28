package com.example.patient.controller;

import com.example.patient.service.PatientService;
import com.example.patient.document.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<Patient>> getPatients() {
        logger.info("GET/");
        try {
            return ResponseEntity.ok(patientService.getPatients());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<Patient> getPatientByPhoneNumber(
            @PathVariable String phoneNumber) {
        logger.info("GET/phoneNumber/" + phoneNumber);
        try {
            return ResponseEntity.ok(patientService.getPatientByPhoneNumber(phoneNumber));
        } catch (NoSuchElementException e) {
            logger.error("getPatientByPhoneNumber error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getPatientByNameAddressBirthDatePhone(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam String birthDate) {
        logger.info("GET/firstName/" + firstName + "/lastName/" + lastName + "/address/" + address + "/birthDate/" + birthDate);
        try {
            return ResponseEntity.ok(
                    patientService.getPatientByNameAddressBirthDate(firstName, lastName, address, birthDate));
        } catch (NoSuchElementException e) {
            logger.error("getPatientByPhoneNumber error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(
            @RequestBody final Patient patient) {
        logger.info("POST/" + patient.getFirstName());
        try {
            return ResponseEntity.ok(patientService.savePatient(patient));
        } catch (NoSuchElementException e) {
            logger.error("savePatient error : " + e);
            return ResponseEntity.notFound().build();
        }
    }
}
