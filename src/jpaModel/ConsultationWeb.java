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
@Table(name = "consultation_web")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultationWeb.findAll", query = "SELECT c FROM ConsultationWeb c")
    , @NamedQuery(name = "ConsultationWeb.findById", query = "SELECT c FROM ConsultationWeb c WHERE c.id = :id")
    , @NamedQuery(name = "ConsultationWeb.findByConsultation", query = "SELECT c FROM ConsultationWeb c WHERE c.consultation = :consultation")
    , @NamedQuery(name = "ConsultationWeb.findByDaterv", query = "SELECT c FROM ConsultationWeb c WHERE c.daterv = :daterv")
    , @NamedQuery(name = "ConsultationWeb.findByDocteur", query = "SELECT c FROM ConsultationWeb c WHERE c.docteur = :docteur")
    , @NamedQuery(name = "ConsultationWeb.findByMatricule", query = "SELECT c FROM ConsultationWeb c WHERE c.matricule = :matricule")
    , @NamedQuery(name = "ConsultationWeb.findByNom", query = "SELECT c FROM ConsultationWeb c WHERE c.nom = :nom")
    , @NamedQuery(name = "ConsultationWeb.findByPrenom", query = "SELECT c FROM ConsultationWeb c WHERE c.prenom = :prenom")})
public class ConsultationWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "consultation")
    @Temporal(TemporalType.DATE)
    private Date consultation;
    @Column(name = "daterv")
    @Temporal(TemporalType.DATE)
    private Date daterv;
    @Column(name = "docteur")
    private String docteur;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;

    public ConsultationWeb() {
    }

    public ConsultationWeb(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getConsultation() {
        return consultation;
    }

    public void setConsultation(Date consultation) {
        this.consultation = consultation;
    }

    public Date getDaterv() {
        return daterv;
    }

    public void setDaterv(Date daterv) {
        this.daterv = daterv;
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
        if (!(object instanceof ConsultationWeb)) {
            return false;
        }
        ConsultationWeb other = (ConsultationWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.ConsultationWeb[ id=" + id + " ]";
    }
    
}
