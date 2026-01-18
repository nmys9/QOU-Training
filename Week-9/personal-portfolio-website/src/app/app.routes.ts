import { Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {ProjectsComponent} from './projects/projects.component';
import {ContactComponent} from './contact/contact.component';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch:'full'},
  {path: 'home' , component:HomeComponent},
  {path: 'about' , component:AboutComponent},
  {path: 'project', component: ProjectsComponent},
  {path: 'contact', component: ContactComponent}
];
