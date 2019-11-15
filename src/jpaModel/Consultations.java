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
import javax.persistence.Lob;
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
@Table(name = "consultations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultations.findAll", query = "SELECT c FROM Consultations c")
    , @NamedQuery(name = "Consultations.findById", query = "SELECT c FROM Consultations c WHERE c.id = :id")
    , @NamedQuery(name = "Consultations.findByMatricule", query = "SELECT c FROM Consultations c WHERE c.matricule = :matricule")
    , @NamedQuery(name = "Consultations.findByTaille", query = "SELECT c FROM Consultations c WHERE c.taille = :taille")
    , @NamedQuery(name = "Consultations.findByPoids", query = "SELECT c FROM Consultations c WHERE c.poids = :poids")
    , @NamedQuery(name = "Consultations.findByPression", query = "SELECT c FROM Consultations c WHERE c.pression = :pression")
    , @NamedQuery(name = "Consultations.findByTemperature", query = "SELECT c FROM Consultations c WHERE c.temperature = :temperature")
    , @NamedQuery(name = "Consultations.findByConclusion", query = "SELECT c FROM Consultations c WHERE c.conclusion = :conclusion")
    , @NamedQuery(name = "Consultations.findByDate", query = "SELECT c FROM Consultations c WHERE c.date = :date")})
public class Consultations implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "documentA")
    private byte[] documentA;
    @Basic(optional = false)
    @Lob
    @Column(name = "documentB")
    private byte[] documentB;
    @Basic(optional = false)
    @Lob
    @Column(name = "documentC")
    private byte[] documentC;

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
    @Column(name = "taille")
    private String taille;
    @Basic(optional = false)
    @Column(name = "poids")
    private String poids;
    @Basic(optional = false)
    @Column(name = "pression")
    private String pression;
    @Basic(optional = false)
    @Column(name = "temperature")
    private String temperature;
    @Basic(optional = false)
    @Column(name = "conclusion")
    private String conclusion;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "iddocteur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin iddocteur;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;

    public Consultations() {
    }

    public Consultations(Integer id) {
        this.id = id;
    }

    public Consultations(Integer id, String matricule, String taille, String poids, String pression, String temperature, String conclusion, Date date, byte[] documentA, byte[] documentB, byte[] documentC) {
        this.id = id;
        this.matricule = matricule;
        this.taille = taille;
        this.poids = poids;
        this.pression = pression;
        this.temperature = temperature;
        this.conclusion = conclusion;
        this.date = date;
        this.documentA = documentA;
        this.documentB = documentB;
        this.documentC = documentC;
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

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getPression() {
        return pression;
    }

    public void setPression(String pression) {
        this.pression = pression;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Medecin getIddocteur() {
        return iddocteur;
    }

    public void setIddocteur(Medecin iddocteur) {
        this.iddocteur = iddocteur;
    }

    public Patient getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Patient idpatient) {
        this.idpatient = idpatient;
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
        if (!(object instanceof Consultations)) {
            return false;
        }
        Consultations other = (Consultations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Consultations[ id=" + id + " ]";
    }

    public byte[] getDocumentA() {
        return documentA;
    }

    public void setDocumentA(byte[] documentA) {
        this.documentA = documentA;
    }

    public byte[] getDocumentB() {
        return documentB;
    }

    public void setDocumentB(byte[] documentB) {
        this.documentB = documentB;
    }

    public byte[] getDocumentC() {
        return documentC;
    }

    public void setDocumentC(byte[] documentC) {
        this.documentC = documentC;
    }
    
}
