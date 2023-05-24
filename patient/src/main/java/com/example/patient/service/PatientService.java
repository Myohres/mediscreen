package com.example.patient.service;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class PatientService {

    /** Logger control. */
    private final Logger logger = LoggerFactory.getLogger(PatientService.class);

    /** Repository patient layer. */
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Find patient by his id.
     * @param id patient id
     * @return Patient
     */
    public Patient getPatientById(final String id) {
        logger.debug("getPatientById");
        return patientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No such patient with id " + id));
    }

    /**
     * Find a patient by all his information service.
     * @param family familyName of the patient
     * @param given GivenName of the patient
     * @param dob BirthDate of the patient
     * @param sex Gender of the patient
     * @param address address of the patient
     * @param phone phone number of the patient
     * @return Patient
     */
    public List<Patient> getPatientByAllInputs(
            final String family,
            final String given,
            final String dob,
            final String sex,
            final String address,
            final String phone) {
        logger.debug("getPatientByAllInputs");
        if (family.equals("") && given.equals("") && dob.equals("")
                && sex.equals("") && address.equals("") && phone.equals("")) {
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

    /**
     * Save new patient.
     * @param patient patient to add
     * @return patient added
     */
    public Patient savePatient(final Patient patient) {
        logger.debug("savePatient");
        return patientRepository.save(patient);
    }

    /**
     * Update patient.
     * @param id patient id to update
     * @param patient patient model with new information
     * @return patient updated
     */
    public Patient updatePatient(final String id, final Patient patient) {
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

    /**
     * Validating input patients.
     * Family, given, birthdate and sex are mandatory.
     * @param patient patient to validate
     * @throws NullPointerException input null detected error
     */
    public void validationPatient(final Patient patient) throws NullPointerException {
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
            throw new NullPointerException(
                    errorMessage1 + " " + errorMessage2 + " "
                            + errorMessage3 + " " + errorMessage4 + " mandatory");
        }
    }
}
