import { Component, OnInit } from '@angular/core';
import {Patient} from "../model/patient";
import {FormControl} from "@angular/forms";
import {PatientService} from "../service/patient.service";

@Component({
  selector: 'app-patient-update',
  templateUrl: './patient-update.component.html',
  styleUrls: ['./patient-update.component.css']
})
export class PatientUpdateComponent implements OnInit {

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

  patientToUpdate: Patient = {
    id: "",
    family: "",
    given: "",
    dob: "",
    sex: "",
    address: "",
    phone: "",
  }

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.patientToUpdate = this.patientService.get_patient();

    this.given.setValue(this.patientToUpdate.given);
    this.family.setValue(this.patientToUpdate.family);
    this.dob.setValue(this.patientToUpdate.dob);
    this.sex.setValue(this.patientToUpdate.sex);
    this.address.setValue(this.patientToUpdate.address);
    this.phone.setValue(this.patientToUpdate.phone);
  }

  updatePatient() {
    this.patientToUpdate.given = this.given.value;
    this.patientToUpdate.family = this.family.value;
    this.patientToUpdate.dob = this.dob.value;
    this.patientToUpdate.sex = this.sex.value;
    this.patientToUpdate.address = this.address.value;
    this.patientToUpdate.phone = this.phone.value;

    this.verificationInput()
    if (!this.errorInput) {
      this.patientService.updatePatient(this.patientToUpdate.id,
        this.patientToUpdate).subscribe({
        next: value => {},
        error: error => {console.log("error")},
        complete: () =>
        {
          console.log("Patient updated successfully")
          this.message = "patient updated successfully";
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
