package model;

import model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.*;


public class Project {
	private long id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ProjectStatus statut;
    private List<Task> taches;

    public  Project(){

    }

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

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ProjectStatus getStatut() {
        return statut;
    }

    public void setStatut(ProjectStatus statut) {
        this.statut = statut;
    }

    public List<Task> getTaches() {
        return taches;
    }

    public void setTaches(List<Task> taches) {
        this.taches = taches;
    }
}
