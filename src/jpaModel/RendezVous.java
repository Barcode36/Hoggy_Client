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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rendez_vous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r")
    , @NamedQuery(name = "RendezVous.findById", query = "SELECT r FROM RendezVous r WHERE r.id = :id")
    , @NamedQuery(name = "RendezVous.findByMatricule", query = "SELECT r FROM RendezVous r WHERE r.matricule = :matricule")
    , @NamedQuery(name = "RendezVous.findByDate", query = "SELECT r FROM RendezVous r WHERE r.date = :date")})
public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "matricule")
    private String matricule;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;
    @JoinColumn(name = "iddocteur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin iddocteur;

    public RendezVous() {
    }

    public RendezVous(Integer id) {
        this.id = id;
    }

    public RendezVous(Integer id, String matricule, Date date) {
        this.id = id;
        this.matricule = matricule;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Patient idpatient) {
        this.idpatient = idpatient;
    }

    public Medecin getIddocteur() {
        return iddocteur;
    }

    public void setIddocteur(Medecin iddocteur) {
        this.iddocteur = iddocteur;
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
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.RendezVous[ id=" + id + " ]";
    }
    
}
