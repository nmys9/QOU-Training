import { Injectable } from '@angular/core';
import {Project} from '../../models/project.model';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private project: Project[] = [
    {
      id: 1,
      title: "Employee Directory API",
      description: "A robust backend system for managing employee records, implementing CRUD operations and seamless database integration using Spring Boot.",
      technologies: ["Spring Boot", "Oracle"],
      githubLink: "https://github.com/nmys9/Employee-Directory-API.git"
    },
    {
      id: 2,
      title: "LocateQOU",
      description: "A mobile application designed for WiFi scanning and location tracking, focusing on network analysis and user connectivity within the university environment.",
      technologies: ["Flutter", "Firebase Firestore","WiFi RSSI"],
      githubLink: "https://github.com/nmys9/LocateQOU-WiFi-Scan.git"
    },
    {
      id: 3,
      title: "Electronics-Store",
      description: "A web project for selling tech gadgets (headphones, keyboards, etc.),It allows users to browse products, adjust quantities, and make purchases. Runs locally on XAMPP as a simple and efficient online store model.",
      technologies: ["PHP", "MYSQL", "HTML","CSS"],
      githubLink: "https://github.com/nmys9/Electronics-Store.git"
    },
    {
      id: 4,
      title: "Computer Vision",
      description: "Image Selection and Stitching, Edge Detection Implementation techniques such as Canny Edge Detection and Difference of Gaussians (DoG) with adjustable morphological operations ,and AI-Based Human Figure Detection using Model SSD MobileNet V3.",
      technologies: ["Python", "OpenCV", "HTML","CSS"],
      githubLink: "https://github.com/nmys9/Computer-Vision.git"
    },
    {
      id: 5,
      title: " Personal Portfolio Website",
      description: "My professional portfolio built with Angular to showcase my skills and projects, featuring a responsive design and modern UI/UX components.",
      technologies: ["Angular", "TypeScript", "CSS Flexbox"],
      githubLink: "https://github.com/nmys9/-Personal-Portfolio-Website.git"
    }
  ];

  getProjects(): Project[]{
    return this.project;
  }
}

