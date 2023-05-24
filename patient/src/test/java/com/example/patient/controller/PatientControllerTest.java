package com.example.patient.controller;

import com.example.patient.document.Patient;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    PatientService patientService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPatientByAllInformationFound() throws Exception {
        when(patientService.getPatientByAllInformation("","","","","","")).thenReturn(new ArrayList<>());
        mvc.perform(get("/patient/")).andExpect(status().isOk());
    }

    @Test
    void getPatientByAllInformationNotFound() throws Exception {
        when(patientService.getPatientByAllInformation(any(),any(),any(),any(),any(),any())).thenThrow(new NoSuchElementException());
        mvc.perform(get("/patient/")).andExpect(status().isNotFound());
    }

    @Test
    void addPatient() throws Exception {
        when(patientService.savePatient(new Patient())).thenReturn(new Patient());
        mvc.perform(post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void addPatientNotFound() throws Exception {
        when(patientService.savePatient(any())).thenThrow(new NoSuchElementException());
        mvc.perform(post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addPatientInvalidInput() throws Exception {
       doThrow(new NullPointerException()).when(patientService).validationPatient(any());
        mvc.perform(post("/patient/add"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePatient() throws Exception {
        when(patientService.updatePatient("1",new Patient())).thenReturn(new Patient());
        mvc.perform(put("/patient/update/id/1")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void updatePatientNotFound() throws Exception {
        when(patientService.updatePatient(any(),any())).thenThrow(new NoSuchElementException());
        mvc.perform(put("/patient/update/id/1")
                        .contentType(MediaType.APPLICATION_JSON).content("{}"))
                        .andExpect(status().isNotFound());
    }

    @Test
    void updatePatientInvalidInput() throws Exception {
        doThrow(new NullPointerException()).when(patientService).validationPatient(any());
        mvc.perform(put("/patient/update/id/1"))
                .andExpect(status().isBadRequest());
    }


}