/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaModel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fallou
 */
@Entity
@Table(name = "allergie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Allergie.findAll", query = "SELECT a FROM Allergie a")
    , @NamedQuery(name = "Allergie.findById", query = "SELECT a FROM Allergie a WHERE a.id = :id")
    , @NamedQuery(name = "Allergie.findByMedicament", query = "SELECT a FROM Allergie a WHERE a.medicament = :medicament")
    , @NamedQuery(name = "Allergie.findByAliments", query = "SELECT a FROM Allergie a WHERE a.aliments = :aliments")
    , @NamedQuery(name = "Allergie.findByAutres", query = "SELECT a FROM Allergie a WHERE a.autres = :autres")
    , @NamedQuery(name = "Allergie.findByIdconsultation", query = "SELECT a FROM Allergie a WHERE a.idconsultation = :idconsultation")})
public class Allergie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "medicament")
    private int medicament;
    @Basic(optional = false)
    @Column(name = "aliments")
    private int aliments;
    @Basic(optional = false)
    @Column(name = "autres")
    private int autres;
    @Basic(optional = false)
    @Column(name = "idconsultation")
    private int idconsultation;

    public Allergie() {
    }

    public Allergie(Integer id) {
        this.id = id;
    }

    public Allergie(Integer id, int medicament, int aliments, int autres, int idconsultation) {
        this.id = id;
        this.medicament = medicament;
        this.aliments = aliments;
        this.autres = autres;
        this.idconsultation = idconsultation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMedicament() {
        return medicament;
    }

    public void setMedicament(int medicament) {
        this.medicament = medicament;
    }

    public int getAliments() {
        return aliments;
    }

    public void setAliments(int aliments) {
        this.aliments = aliments;
    }

    public int getAutres() {
        return autres;
    }

    public void setAutres(int autres) {
        this.autres = autres;
    }

    public int getIdconsultation() {
        return idconsultation;
    }

    public void setIdconsultation(int idconsultation) {
        this.idconsultation = idconsultation;
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
        if (!(object instanceof Allergie)) {
            return false;
        }
        Allergie other = (Allergie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Allergie[ id=" + id + " ]";
    }
    
}
