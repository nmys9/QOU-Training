import {Component, OnInit, signal} from '@angular/core';
import {Project} from '../models/project.model';
import {ProjectService} from '../service/project/project.service';
import {of} from 'rxjs';
import {ProjectCardComponent} from '../project-card/project-card.component';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [
    ProjectCardComponent
  ],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.css',
})
export class ProjectsComponent implements OnInit{
  myProjects: Project[] = [];

  constructor(private projectService:ProjectService) {
  }

  ngOnInit(){
    this.myProjects=this.projectService.getProjects();
  }

  protected readonly of = of;
}
