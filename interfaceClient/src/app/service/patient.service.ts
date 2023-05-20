import {Patient} from "../model/patient";
import * as Http from "http";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn:"root"
})
export class PatientService {

  private rootURL: string = 'http://localhost:8081/patient';

  private _patient: Patient = {
    id: "",
    family: "",
    given: "",
    dob: "",
    sex: "",
    address: "",
    phone: "",
  }

  constructor(private http: HttpClient) {
  }

  public getPatientAllInformation(family: string, given: string, dob: string, sex: string, address: string, phone :string): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.rootURL}/`, {
      params: {
        family: family,
        given: given,
        dob: dob,
        sex: sex,
        address: address,
        phone: phone
      },
      })
  }

  public updatePatient(id: string, patient: Patient): Observable<Patient> {
    return this.http.put<Patient>(`${this.rootURL}/update/id/${id}`,patient, {
      params: {
        family: patient.family,
        given: patient.given,
        dob: patient.dob,
        sex: patient.sex,
        address: patient.address,
        phone: patient.phone
      },
    })
  }

  public savePatient( patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(`${this.rootURL}/add`, patient)
  }

  get_patient(): Patient {
    return this._patient;
  }

  set_patient(value: Patient) {
    this._patient = value;
  }

  get patientId(): string {
    return this._patient.id;
  }

  get patientFirstName(): string {
    return this._patient.family;
  }

  get patientLastName(): string {
    return this._patient.given;
  }

  get patientBirthDate(): string {
    return this._patient.dob;
  }

  get patientGenre(): string {
    return this._patient.sex;
  }

  get patientAddress(): string {
    return this._patient.address;
  }

  get patientPhoneNumber(): string {
    return this._patient.phone;
  }

  set patientId(value: string) {
    this._patient.id = value;
  }

  set patientFirstName(value: string) {
    this._patient.family = value;
  }

  set patientLastName(value: string) {
    this._patient.given = value;
  }

  set patientBirthDate(value: string) {
    this._patient.dob = value;
  }

  set patientGenre(value: string) {
    this._patient.sex = value;
  }

  set patientAddress(value: string) {
    this._patient.address = value;
  }

  set patientPhoneNumber(value: string) {
    this._patient.phone = value;
  }

}
