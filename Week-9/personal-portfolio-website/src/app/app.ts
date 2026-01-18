import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ProjectsComponent} from './projects/projects.component';
import {SiteHeaderComponent} from './site-header/site-header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProjectsComponent, SiteHeaderComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('personal-portfolio-website');
}
