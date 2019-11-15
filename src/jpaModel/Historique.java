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
@Table(name = "historique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historique.findAll", query = "SELECT h FROM Historique h")
    , @NamedQuery(name = "Historique.findById", query = "SELECT h FROM Historique h WHERE h.id = :id")
    , @NamedQuery(name = "Historique.findByDiabete", query = "SELECT h FROM Historique h WHERE h.diabete = :diabete")
    , @NamedQuery(name = "Historique.findByTuberculose", query = "SELECT h FROM Historique h WHERE h.tuberculose = :tuberculose")
    , @NamedQuery(name = "Historique.findByOrthepedique", query = "SELECT h FROM Historique h WHERE h.orthepedique = :orthepedique")
    , @NamedQuery(name = "Historique.findByHypertension", query = "SELECT h FROM Historique h WHERE h.hypertension = :hypertension")
    , @NamedQuery(name = "Historique.findByAutre", query = "SELECT h FROM Historique h WHERE h.autre = :autre")
    , @NamedQuery(name = "Historique.findByIdconsultation", query = "SELECT h FROM Historique h WHERE h.idconsultation = :idconsultation")})
public class Historique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "diabete")
    private int diabete;
    @Basic(optional = false)
    @Column(name = "tuberculose")
    private int tuberculose;
    @Basic(optional = false)
    @Column(name = "orthepedique")
    private int orthepedique;
    @Basic(optional = false)
    @Column(name = "hypertension")
    private int hypertension;
    @Basic(optional = false)
    @Column(name = "autre")
    private int autre;
    @Basic(optional = false)
    @Column(name = "idconsultation")
    private int idconsultation;

    public Historique() {
    }

    public Historique(Integer id) {
        this.id = id;
    }

    public Historique(Integer id, int diabete, int tuberculose, int orthepedique, int hypertension, int autre, int idconsultation) {
        this.id = id;
        this.diabete = diabete;
        this.tuberculose = tuberculose;
        this.orthepedique = orthepedique;
        this.hypertension = hypertension;
        this.autre = autre;
        this.idconsultation = idconsultation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDiabete() {
        return diabete;
    }

    public void setDiabete(int diabete) {
        this.diabete = diabete;
    }

    public int getTuberculose() {
        return tuberculose;
    }

    public void setTuberculose(int tuberculose) {
        this.tuberculose = tuberculose;
    }

    public int getOrthepedique() {
        return orthepedique;
    }

    public void setOrthepedique(int orthepedique) {
        this.orthepedique = orthepedique;
    }

    public int getHypertension() {
        return hypertension;
    }

    public void setHypertension(int hypertension) {
        this.hypertension = hypertension;
    }

    public int getAutre() {
        return autre;
    }

    public void setAutre(int autre) {
        this.autre = autre;
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
        if (!(object instanceof Historique)) {
            return false;
        }
        Historique other = (Historique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Historique[ id=" + id + " ]";
    }
    
}
