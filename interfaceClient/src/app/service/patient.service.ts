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
    firstName: "",
    lastName: "",
    dob: "",
    sex: "",
    address: "",
    phone: "",
  }

  constructor(private http: HttpClient){
  }

  public getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.rootURL)
  }

  public getPatientByPhone(phone: string): Observable<Patient[]>{
    return this.http.get<Patient[]>(`${this.rootURL}/phone/${phone}`)
  }

  public getPatientAllInformation(firstName: string, lastName: string, dob: string, sex: string, address: string, phone :string): Observable<Patient[]>{
    return this.http.get<Patient[]>(`${this.rootURL}/`, {
      params: {
        firstName: firstName,
        lastName: lastName,
        dob: dob,
        sex: sex,
        address: address,
        phone: phone
      },
      })
  }

  get patient(): Patient {
    return this._patient;
  }

  set patient(value: Patient) {
    this._patient = value;
  }

  get patientId(): string {
    return this._patient.id;
  }

  get patientFirstName(): string {
    return this._patient.firstName;
  }

  get patientLastName(): string {
    return this._patient.lastName;
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
    this._patient.firstName = value;
  }

  set patientLastName(value: string) {
    this._patient.lastName = value;
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
