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
@Table(name = "observation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observation.findAll", query = "SELECT o FROM Observation o")
    , @NamedQuery(name = "Observation.findById", query = "SELECT o FROM Observation o WHERE o.id = :id")
    , @NamedQuery(name = "Observation.findByPhysique", query = "SELECT o FROM Observation o WHERE o.physique = :physique")
    , @NamedQuery(name = "Observation.findByLangage", query = "SELECT o FROM Observation o WHERE o.langage = :langage")
    , @NamedQuery(name = "Observation.findByDentition", query = "SELECT o FROM Observation o WHERE o.dentition = :dentition")
    , @NamedQuery(name = "Observation.findByAuditif", query = "SELECT o FROM Observation o WHERE o.auditif = :auditif")
    , @NamedQuery(name = "Observation.findByVision", query = "SELECT o FROM Observation o WHERE o.vision = :vision")
    , @NamedQuery(name = "Observation.findByCommunication", query = "SELECT o FROM Observation o WHERE o.communication = :communication")
    , @NamedQuery(name = "Observation.findByIdconsultation", query = "SELECT o FROM Observation o WHERE o.idconsultation = :idconsultation")})
public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Physique")
    private int physique;
    @Basic(optional = false)
    @Column(name = "langage")
    private int langage;
    @Basic(optional = false)
    @Column(name = "dentition")
    private int dentition;
    @Basic(optional = false)
    @Column(name = "auditif")
    private int auditif;
    @Basic(optional = false)
    @Column(name = "vision")
    private int vision;
    @Basic(optional = false)
    @Column(name = "communication")
    private int communication;
    @Basic(optional = false)
    @Column(name = "idconsultation")
    private int idconsultation;

    public Observation() {
    }

    public Observation(Integer id) {
        this.id = id;
    }

    public Observation(Integer id, int physique, int langage, int dentition, int auditif, int vision, int communication, int idconsultation) {
        this.id = id;
        this.physique = physique;
        this.langage = langage;
        this.dentition = dentition;
        this.auditif = auditif;
        this.vision = vision;
        this.communication = communication;
        this.idconsultation = idconsultation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPhysique() {
        return physique;
    }

    public void setPhysique(int physique) {
        this.physique = physique;
    }

    public int getLangage() {
        return langage;
    }

    public void setLangage(int langage) {
        this.langage = langage;
    }

    public int getDentition() {
        return dentition;
    }

    public void setDentition(int dentition) {
        this.dentition = dentition;
    }

    public int getAuditif() {
        return auditif;
    }

    public void setAuditif(int auditif) {
        this.auditif = auditif;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
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
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Observation[ id=" + id + " ]";
    }
    
}
