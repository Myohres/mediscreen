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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:4200")
public class PatientController {
    /**
     * Logger control.
     */
    private final Logger log = LoggerFactory.getLogger(PatientController.class);
    /**
     * Patient service layer.
     */
    @Autowired
    private PatientService patientService;

    /**
     * Find Patient by all his inputs parameters.
     * @param family familyName patient
     * @param given GivenName patient
     * @param dob BirthDate patient
     * @param sex Gender patient
     * @param address address patient
     * @param phone phoneNumber patient
     * @return Patient
     */
    @GetMapping("/")
    public ResponseEntity<List<Patient>> getPatient(
            final String family,
            final String given,
            final String dob,
            final String sex,
            final String address,
            final String phone) {
        log.info("GET/family/" + family + "/given/" + given + "/birthDate/" + dob
                + "/sex/" + sex + "/address/" + address + "/phone/" + phone);
        try {
            return ResponseEntity.ok(
                    patientService.getPatientByAllInputs(family, given, dob, sex, address, phone));
        } catch (NoSuchElementException e) {
            log.error("getPatient error : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add a new Patient.
     * @param patient Patient to add
     * @return new Patient added
     */
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(
           final Patient patient) {
        log.info("POST/" + patient.getFamily() + "/given/" + patient.getGiven()
                + "/birthDate/" + patient.getDob() + "/sex/" + patient.getSex()
                + "/address/" + patient.getAddress() + "/phone/" + patient.getPhone());
        try {
            patientService.validationPatient(patient);
            return ResponseEntity.ok(patientService.savePatient(patient));
        } catch (NoSuchElementException e) {
            log.error("savePatient error : " + e);
            return ResponseEntity.notFound().build();
        } catch (NullPointerException e) {
            log.error("savePatient error : " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update information patient.
     * @param id patient to update id
     * @param patient patient model with new information
     * @return patient updated
     */
    @PutMapping("/update/id/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable final String id, final Patient patient) {
        log.info("PUT/patient/update/id/" + patient.getFamily() + "/given/" + patient.getGiven()
                + "/birthDate/" + patient.getDob() + "/sex/" + patient.getSex()
                + "/address/" + patient.getAddress() + "/phone/" + patient.getPhone());
        try {
            patientService.validationPatient(patient);
            return ResponseEntity.ok(patientService.updatePatient(id, patient));
        } catch (NoSuchElementException e) {
            log.error("updatePatient error : " + e);
            return ResponseEntity.notFound().build();
        } catch (NullPointerException e) {
            log.error("UpdatePatient error : " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
