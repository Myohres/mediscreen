import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PatientComponent} from "./patient/patient.component";


const routes: Routes = [
  { path: 'patient', component: PatientComponent },

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
