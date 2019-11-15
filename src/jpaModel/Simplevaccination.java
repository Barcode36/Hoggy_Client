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
@Table(name = "simplevaccination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simplevaccination.findAll", query = "SELECT s FROM Simplevaccination s")
    , @NamedQuery(name = "Simplevaccination.findById", query = "SELECT s FROM Simplevaccination s WHERE s.id = :id")
    , @NamedQuery(name = "Simplevaccination.findByMatricule", query = "SELECT s FROM Simplevaccination s WHERE s.matricule = :matricule")
    , @NamedQuery(name = "Simplevaccination.findByDate", query = "SELECT s FROM Simplevaccination s WHERE s.date = :date")
    , @NamedQuery(name = "Simplevaccination.findByType", query = "SELECT s FROM Simplevaccination s WHERE s.type = :type")
    , @NamedQuery(name = "Simplevaccination.findByConclusion", query = "SELECT s FROM Simplevaccination s WHERE s.conclusion = :conclusion")})
public class Simplevaccination implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "conclusion")
    private String conclusion;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;
    @JoinColumn(name = "iddocteur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin iddocteur;

    public Simplevaccination() {
    }

    public Simplevaccination(Integer id) {
        this.id = id;
    }

    public Simplevaccination(Integer id, String matricule, Date date, String type, String conclusion) {
        this.id = id;
        this.matricule = matricule;
        this.date = date;
        this.type = type;
        this.conclusion = conclusion;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
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
        if (!(object instanceof Simplevaccination)) {
            return false;
        }
        Simplevaccination other = (Simplevaccination) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Simplevaccination[ id=" + id + " ]";
    }
    
}
