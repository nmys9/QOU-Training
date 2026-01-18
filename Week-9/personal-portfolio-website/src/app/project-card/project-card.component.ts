import {Component, input} from '@angular/core';
import {Project} from '../models/project.model';
import {of} from 'rxjs';

@Component({
  selector: 'app-project-card',
  imports: [],
  templateUrl: './project-card.component.html',
  styleUrl: './project-card.component.css',
})
export class ProjectCardComponent {
  project =input.required<Project>();
  protected readonly of = of;
}
