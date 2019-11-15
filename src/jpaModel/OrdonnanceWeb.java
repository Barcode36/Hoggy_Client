/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fallou
 */
@Entity
@Table(name = "ordonnance_web")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdonnanceWeb.findAll", query = "SELECT o FROM OrdonnanceWeb o")
    , @NamedQuery(name = "OrdonnanceWeb.findById", query = "SELECT o FROM OrdonnanceWeb o WHERE o.id = :id")
    , @NamedQuery(name = "OrdonnanceWeb.findByDatepreinscription", query = "SELECT o FROM OrdonnanceWeb o WHERE o.datepreinscription = :datepreinscription")
    , @NamedQuery(name = "OrdonnanceWeb.findByDocteur", query = "SELECT o FROM OrdonnanceWeb o WHERE o.docteur = :docteur")
    , @NamedQuery(name = "OrdonnanceWeb.findByMatricule", query = "SELECT o FROM OrdonnanceWeb o WHERE o.matricule = :matricule")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament1", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament1 = :medicament1")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament10", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament10 = :medicament10")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament2", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament2 = :medicament2")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament3", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament3 = :medicament3")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament4", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament4 = :medicament4")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament5", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament5 = :medicament5")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament6", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament6 = :medicament6")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament7", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament7 = :medicament7")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament8", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament8 = :medicament8")
    , @NamedQuery(name = "OrdonnanceWeb.findByMedicament9", query = "SELECT o FROM OrdonnanceWeb o WHERE o.medicament9 = :medicament9")
    , @NamedQuery(name = "OrdonnanceWeb.findByNaissance", query = "SELECT o FROM OrdonnanceWeb o WHERE o.naissance = :naissance")
    , @NamedQuery(name = "OrdonnanceWeb.findByNom", query = "SELECT o FROM OrdonnanceWeb o WHERE o.nom = :nom")
    , @NamedQuery(name = "OrdonnanceWeb.findByPrenom", query = "SELECT o FROM OrdonnanceWeb o WHERE o.prenom = :prenom")})
public class OrdonnanceWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "datepreinscription")
    @Temporal(TemporalType.DATE)
    private Date datepreinscription;
    @Column(name = "docteur")
    private String docteur;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "medicament1")
    private String medicament1;
    @Column(name = "medicament10")
    private String medicament10;
    @Column(name = "medicament2")
    private String medicament2;
    @Column(name = "medicament3")
    private String medicament3;
    @Column(name = "medicament4")
    private String medicament4;
    @Column(name = "medicament5")
    private String medicament5;
    @Column(name = "medicament6")
    private String medicament6;
    @Column(name = "medicament7")
    private String medicament7;
    @Column(name = "medicament8")
    private String medicament8;
    @Column(name = "medicament9")
    private String medicament9;
    @Column(name = "naissance")
    @Temporal(TemporalType.DATE)
    private Date naissance;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    public OrdonnanceWeb() {
    }

    public OrdonnanceWeb(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatepreinscription() {
        return datepreinscription;
    }

    public void setDatepreinscription(Date datepreinscription) {
        this.datepreinscription = datepreinscription;
    }

    public String getDocteur() {
        return docteur;
    }

    public void setDocteur(String docteur) {
        this.docteur = docteur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(String medicament1) {
        this.medicament1 = medicament1;
    }

    public String getMedicament10() {
        return medicament10;
    }

    public void setMedicament10(String medicament10) {
        this.medicament10 = medicament10;
    }

    public String getMedicament2() {
        return medicament2;
    }

    public void setMedicament2(String medicament2) {
        this.medicament2 = medicament2;
    }

    public String getMedicament3() {
        return medicament3;
    }

    public void setMedicament3(String medicament3) {
        this.medicament3 = medicament3;
    }

    public String getMedicament4() {
        return medicament4;
    }

    public void setMedicament4(String medicament4) {
        this.medicament4 = medicament4;
    }

    public String getMedicament5() {
        return medicament5;
    }

    public void setMedicament5(String medicament5) {
        this.medicament5 = medicament5;
    }

    public String getMedicament6() {
        return medicament6;
    }

    public void setMedicament6(String medicament6) {
        this.medicament6 = medicament6;
    }

    public String getMedicament7() {
        return medicament7;
    }

    public void setMedicament7(String medicament7) {
        this.medicament7 = medicament7;
    }

    public String getMedicament8() {
        return medicament8;
    }

    public void setMedicament8(String medicament8) {
        this.medicament8 = medicament8;
    }

    public String getMedicament9() {
        return medicament9;
    }

    public void setMedicament9(String medicament9) {
        this.medicament9 = medicament9;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdonnanceWeb)) {
            return false;
        }
        OrdonnanceWeb other = (OrdonnanceWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.OrdonnanceWeb[ id=" + id + " ]";
    }
    
}
