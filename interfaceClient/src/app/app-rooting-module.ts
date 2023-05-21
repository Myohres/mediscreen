import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PatientComponent} from "./patient/patient.component";
import {PatientUpdateComponent} from "./patient-update/patient-update.component";
import {PatientAddComponent} from "./patient-add/patient-add.component";


const routes: Routes = [
  { path: 'patient', component: PatientComponent },
  { path: 'patient/update', component: PatientUpdateComponent},
  { path: 'patient/add', component: PatientAddComponent},

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule{}
