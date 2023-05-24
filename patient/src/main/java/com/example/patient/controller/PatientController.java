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
@CrossOrigin("http://localhost:4200")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientService patientService;

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getPatient(
           final String family,
           final String given,
           final String dob,
           final String sex,
           final String address,
           final String phone) {
        logger.info("GET/family/" + family + "/given/" + given + "/birthDate/" + dob
                + "/sex/" + sex + "/address/" + address + "/phone/" + phone);
        try {
            return ResponseEntity.ok(
                    patientService.getPatientByAllInformation(family, given, dob, sex, address, phone));
        } catch (NoSuchElementException e) {
            logger.error("getPatient error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(
            final Patient patient) {
        logger.info("POST/" + patient.getFamily() + "/given/" + patient.getGiven() + "/birthDate/" + patient.getDob()
                + "/sex/" + patient.getSex() + "/address/" + patient.getAddress() + "/phone/" + patient.getPhone());
        try {
            patientService.validationPatient(patient);
            return ResponseEntity.ok(patientService.savePatient(patient));
        } catch (NoSuchElementException e) {
            logger.error("savePatient error : " + e);
            return ResponseEntity.notFound().build();
        } catch (NullPointerException e) {
            logger.error("savePatient error : " +e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable String id, Patient patient) {
      logger.info("PUT/patient/update/id/" + patient.getFamily() + "/given/" + patient.getGiven() + "/birthDate/" + patient.getDob()
                + "/sex/" + patient.getSex() + "/address/" + patient.getAddress() + "/phone/" + patient.getPhone());
        try {
            patientService.validationPatient(patient);
            return ResponseEntity.ok(patientService.updatePatient(id, patient));
        } catch (NoSuchElementException e) {
            logger.error("updatePatient error : " + e);
            return ResponseEntity.notFound().build();
        } catch (NullPointerException e) {
            logger.error("savePatient error : " +e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
