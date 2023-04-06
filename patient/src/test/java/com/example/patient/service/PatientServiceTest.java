package com.example.patient.service;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    Patient patient1;
    Patient patient2;
    Patient patient3;
    Patient patient4;

    List<Patient> patientList;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        patientList = new ArrayList<Patient>();

        patient1 = new Patient();
        patient1.setId("1");
        patient1.setFamily("John");
        patient1.setGiven("Connor");
        patient1.setDob("18-04-1989");
        patient1.setAddress("1 rue ville");
        patient1.setSex("H");
        patient1.setPhone("1234");
        patientList.add(patient1);

        patient2 = new Patient();
        patient2.setId("2");
        patient2.setFamily("Jane");
        patient2.setGiven("Seymour");
        patient2.setDob("20-02-1970");
        patient2.setAddress("3 av ville");
        patient2.setSex("F");
        patient2.setPhone("5678");
        patientList.add(patient2);

        patient3 = new Patient();
        patient3.setId("3");
        patient3.setFamily("Will");
        patient3.setGiven("Turner");
        patient3.setDob("10-03-1985");
        patient3.setAddress("10 rue Fort");
        patient3.setSex("H");
        patient3.setPhone("0000");
        patientList.add(patient3);
    }

    @Test
    void getPatientByAllInformationWithNoInfo() {
       List<Patient> patientListTest = patientService.getPatientByAllInformation("","","","","","");
       assertEquals(0, patientListTest.size());
    }

    @Test
    void getPatientByAllInformationWithAllInfo() {
        when(patientRepository.findAll()).thenReturn(patientList);
        List<Patient> patientListTest = patientService.getPatientByAllInformation("John","Connor","18-04-1989","H","1 rue ville","1234");
        assertEquals(1, patientListTest.size());
    }

    @Test
    void getPatientByAllInformationWithSomeInformation() {
        when(patientRepository.findAll()).thenReturn(patientList);
        List<Patient> patientListTest = patientService.getPatientByAllInformation("","","","H","","");
        assertEquals(2, patientListTest.size());
    }

    @Test
    void getPatientByAllInformationWithSomeInformationAndNotFound() {
        when(patientRepository.findAll()).thenReturn(patientList);
       assertThrows(NoSuchElementException.class, () -> patientService.getPatientByAllInformation("","","","","","9999"));
    }

    @Test
    void savePatient() {
        when(patientRepository.save(any())).thenReturn(patient1);
        patientService.savePatient(patient1);
        verify(patientRepository,times(1)).save(patient1);
    }
}