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
@Table(name = "ordonnance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordonnance.findAll", query = "SELECT o FROM Ordonnance o")
    , @NamedQuery(name = "Ordonnance.findById", query = "SELECT o FROM Ordonnance o WHERE o.id = :id")
    , @NamedQuery(name = "Ordonnance.findByMatricule", query = "SELECT o FROM Ordonnance o WHERE o.matricule = :matricule")
    , @NamedQuery(name = "Ordonnance.findByDate", query = "SELECT o FROM Ordonnance o WHERE o.date = :date")
    , @NamedQuery(name = "Ordonnance.findByMedicament1", query = "SELECT o FROM Ordonnance o WHERE o.medicament1 = :medicament1")
    , @NamedQuery(name = "Ordonnance.findByMedicament2", query = "SELECT o FROM Ordonnance o WHERE o.medicament2 = :medicament2")
    , @NamedQuery(name = "Ordonnance.findByMedicament3", query = "SELECT o FROM Ordonnance o WHERE o.medicament3 = :medicament3")
    , @NamedQuery(name = "Ordonnance.findByMedicament4", query = "SELECT o FROM Ordonnance o WHERE o.medicament4 = :medicament4")
    , @NamedQuery(name = "Ordonnance.findByMedicamen5", query = "SELECT o FROM Ordonnance o WHERE o.medicamen5 = :medicamen5")
    , @NamedQuery(name = "Ordonnance.findByMedicament6", query = "SELECT o FROM Ordonnance o WHERE o.medicament6 = :medicament6")
    , @NamedQuery(name = "Ordonnance.findByDaterv", query = "SELECT o FROM Ordonnance o WHERE o.daterv = :daterv")})
public class Ordonnance implements Serializable {

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
    @Column(name = "medicament1")
    private String medicament1;
    @Basic(optional = false)
    @Column(name = "medicament2")
    private String medicament2;
    @Basic(optional = false)
    @Column(name = "medicament3")
    private String medicament3;
    @Basic(optional = false)
    @Column(name = "medicament4")
    private String medicament4;
    @Basic(optional = false)
    @Column(name = "medicamen5")
    private String medicamen5;
    @Basic(optional = false)
    @Column(name = "medicament6")
    private String medicament6;
    @Basic(optional = false)
    @Column(name = "daterv")
    @Temporal(TemporalType.DATE)
    private Date daterv;
    @JoinColumn(name = "iddocteur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin iddocteur;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;

    public Ordonnance() {
    }

    public Ordonnance(Integer id) {
        this.id = id;
    }

    public Ordonnance(Integer id, String matricule, Date date, String medicament1, String medicament2, String medicament3, String medicament4, String medicamen5, String medicament6, Date daterv) {
        this.id = id;
        this.matricule = matricule;
        this.date = date;
        this.medicament1 = medicament1;
        this.medicament2 = medicament2;
        this.medicament3 = medicament3;
        this.medicament4 = medicament4;
        this.medicamen5 = medicamen5;
        this.medicament6 = medicament6;
        this.daterv = daterv;
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

    public String getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(String medicament1) {
        this.medicament1 = medicament1;
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

    public String getMedicamen5() {
        return medicamen5;
    }

    public void setMedicamen5(String medicamen5) {
        this.medicamen5 = medicamen5;
    }

    public String getMedicament6() {
        return medicament6;
    }

    public void setMedicament6(String medicament6) {
        this.medicament6 = medicament6;
    }

    public Date getDaterv() {
        return daterv;
    }

    public void setDaterv(Date daterv) {
        this.daterv = daterv;
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
        if (!(object instanceof Ordonnance)) {
            return false;
        }
        Ordonnance other = (Ordonnance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Ordonnance[ id=" + id + " ]";
    }
    
}
