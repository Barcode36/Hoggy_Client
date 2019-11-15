/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Date;

/**
 *
 * @author Fallou
 */
public class Classe {
    private String matricule;
     private String adresse;
      private String telephone;
       private String naissance;
        private String nom;
         private String prenom;
          private String dateconcultation;
      private String docteur;

    public Classe() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateconcultation() {
        return dateconcultation;
    }

    public void setDateconcultation(String dateconcultation) {
        this.dateconcultation = dateconcultation;
    }

    public String getDocteur() {
        return docteur;
    }

    public void setDocteur(String docteur) {
        this.docteur = docteur;
    }

    public Classe(String matricule, String adresse, String telephone, String naissance, String nom, String prenom, String dateconcultation, String docteur) {
        this.matricule = matricule;
        this.adresse = adresse;
        this.telephone = telephone;
        this.naissance = naissance;
        this.nom = nom;
        this.prenom = prenom;
        this.dateconcultation = dateconcultation;
        this.docteur = docteur;
    }
 
          
}
