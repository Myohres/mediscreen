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
  validationVerification: boolean = false;

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

  ngOnInit(): void {
  }

  addPatient() {
    this.patientToAdd.given = this.given.value;
    this.patientToAdd.family = this.family.value;
    this.patientToAdd.dob = this.dob.value;
    this.patientToAdd.sex = this.sex.value;
    this.patientToAdd.address = this.address.value;
    this.patientToAdd.phone = this.phone.value;

    this.verificationInput()
    if (this.validationVerification) {
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
    if (this.given.value != "" && this.family.value != "" && this.dob.value != "" && this.sex.value != "") {
      this.validationVerification = true;
    } else {
      this.message = "Lastname, firstname, birthdate and gender are mandatory"
    }

  }


}
