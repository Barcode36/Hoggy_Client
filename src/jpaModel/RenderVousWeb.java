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
@Table(name = "render_vous_web")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenderVousWeb.findAll", query = "SELECT r FROM RenderVousWeb r")
    , @NamedQuery(name = "RenderVousWeb.findById", query = "SELECT r FROM RenderVousWeb r WHERE r.id = :id")
    , @NamedQuery(name = "RenderVousWeb.findByConsultation", query = "SELECT r FROM RenderVousWeb r WHERE r.consultation = :consultation")
    , @NamedQuery(name = "RenderVousWeb.findByDaterv", query = "SELECT r FROM RenderVousWeb r WHERE r.daterv = :daterv")
    , @NamedQuery(name = "RenderVousWeb.findByDocteur", query = "SELECT r FROM RenderVousWeb r WHERE r.docteur = :docteur")
    , @NamedQuery(name = "RenderVousWeb.findByMatricule", query = "SELECT r FROM RenderVousWeb r WHERE r.matricule = :matricule")
    , @NamedQuery(name = "RenderVousWeb.findByNom", query = "SELECT r FROM RenderVousWeb r WHERE r.nom = :nom")
    , @NamedQuery(name = "RenderVousWeb.findByPrenom", query = "SELECT r FROM RenderVousWeb r WHERE r.prenom = :prenom")})
public class RenderVousWeb implements Serializable {

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

    public RenderVousWeb() {
    }

    public RenderVousWeb(Long id) {
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
        if (!(object instanceof RenderVousWeb)) {
            return false;
        }
        RenderVousWeb other = (RenderVousWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.RenderVousWeb[ id=" + id + " ]";
    }
    
}
