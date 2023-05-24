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
  validationVerification: boolean = false;

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
    if (this.validationVerification) {
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
    if (this.given.value != "" && this.family.value != "" && this.dob.value != "" && this.sex.value != "") {
      this.validationVerification = true;
    } else {
      this.message = "Lastname, firstname, birthdate and gender are mandatory"
    }

  }
}
