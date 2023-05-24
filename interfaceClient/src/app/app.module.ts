import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-rooting-module";
import {HttpClientModule} from "@angular/common/http";
import { PatientComponent } from './patient/patient.component';
import { PatientUpdateComponent } from './patient-update/patient-update.component';
import { PatientAddComponent } from './patient-add/patient-add.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    PatientUpdateComponent,
    PatientAddComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
