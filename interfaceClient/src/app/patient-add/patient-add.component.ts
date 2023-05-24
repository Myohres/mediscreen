import { Component, OnInit } from '@angular/core';
import {FormControl} from "@angular/forms";
import {Patient} from "../model/patient";
import {PatientService} from "../service/patient.service";

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {

  given: FormControl = new FormControl();
  family: FormControl = new FormControl();
  dob: FormControl = new FormControl();
  sex: FormControl = new FormControl();
  address: FormControl = new FormControl();
  phone: FormControl = new FormControl();

  message: string = '';

  errorInput: boolean = false;
  errorMessageFamily: string = "";
  errorMessageGiven: string = "";
  errorMessageDob: string = "";
  errorMessageSex: string= "";

  patientToAdd: Patient = {
    id: "",
    family: "",
    given: "",
    dob: "",
    sex: "",
    address: "",
    phone: "",
  }

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {}

  addPatient() {
    this.patientToAdd.family = this.family.value;
    this.patientToAdd.given = this.given.value;
    this.patientToAdd.dob = this.dob.value;
    this.patientToAdd.sex = this.sex.value;
    this.patientToAdd.address = this.address.value;
    this.patientToAdd.phone = this.phone.value;

    this.verificationInput()
    if (!this.errorInput) {
      this.patientService.addPatient(this.patientToAdd).subscribe({
        next: value => {},
        error: error => {console.log("error")},
        complete: () =>
        {
          console.log("Patient added successfully")
          this.message = "patient added successfully";
        }
      })
    }
  }

  verificationInput() {
    if ((this.family.value == null) || (this.family.value == "")) {
      this.errorMessageFamily = "Family is mandatory";
      this.errorInput = true;
    } else {
      this.errorMessageFamily = "";
      this.errorInput = false;
    }
    if (this.given.value == null || (this.given.value == "")) {
      this.errorMessageGiven = "Given is mandatory";
      this.errorInput = true;
    } else {
      this.errorMessageGiven = "";
      this.errorInput = false;
    }
    if (this.dob.value == null || (this.dob.value == "")) {
      this.errorMessageDob = "Birthdate is mandatory";
      this.errorInput = true;
    }
    else {
      this.errorMessageDob = "";
      this.errorInput = false;
    }
    if (this.sex.value == null || (this.sex.value == "")) {
      this.errorMessageSex = "Gender is mandatory";
      this.errorInput = true;
    } else {
      this.errorMessageSex = "";
      this.errorInput = false
    }
  }


}
