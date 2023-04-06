import { Component, OnInit } from '@angular/core';
import { Patient } from '../model/patient';
import { Observable } from 'rxjs';
import {PatientService} from "../service/patient.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  public patientData: Patient[] = [];
  public given = new FormControl();
  public family = new FormControl();
  public dob = new FormControl();
  public sex = new FormControl();
  public address = new FormControl();
  public phoneNumber = new FormControl();

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.patientData = []
    this.given.setValue("")
    this.family.setValue("")
    this.dob.setValue("")
    this.sex.setValue("")
    this.address.setValue("")
    this.phoneNumber.setValue("")

  }

  getPatient(){

    this.patientService.getPatientAllInformation(this.family.value, this.given.value, this.dob.value, this.sex.value, this.address.value, this.phoneNumber.value)
      .subscribe({
        next: data => this.patientData = data,
        error: err => console.error(+err),
        complete: () => console.log("get patient complete")
      })

    this.given.reset()
    this.family.reset()
    this.dob.reset()
    this.sex.reset()
    this.address.reset()
    this.phoneNumber.reset()
    this.given.setValue("")
    this.family.setValue("")
    this.dob.setValue("")
    this.sex.setValue("")
    this.address.setValue("")
    this.phoneNumber.setValue("")
  }
}
