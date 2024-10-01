package model;

import model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.*;


public class Project {
	private int id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ProjectStatus statut;
    private List<Task> taches;

    public Project(String nom, String description, LocalDate dateDebut, LocalDate dateFin, ProjectStatus statut, List<Task> taches) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.taches = taches;
    }

    public Project(int id, String nom, String description, LocalDate dateDebut, LocalDate dateFin, ProjectStatus statut, List<Task> taches) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.taches = taches;
    }


    
}
