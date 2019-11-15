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
@Table(name = "hospitalisation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospitalisation.findAll", query = "SELECT h FROM Hospitalisation h")
    , @NamedQuery(name = "Hospitalisation.findById", query = "SELECT h FROM Hospitalisation h WHERE h.id = :id")
    , @NamedQuery(name = "Hospitalisation.findByMatricule", query = "SELECT h FROM Hospitalisation h WHERE h.matricule = :matricule")
    , @NamedQuery(name = "Hospitalisation.findByEntre", query = "SELECT h FROM Hospitalisation h WHERE h.entre = :entre")})
public class Hospitalisation implements Serializable {

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
    @Column(name = "entre")
    @Temporal(TemporalType.DATE)
    private Date entre;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;
    @JoinColumn(name = "idchambre", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chambre idchambre;

    public Hospitalisation() {
    }

    public Hospitalisation(Integer id) {
        this.id = id;
    }

    public Hospitalisation(Integer id, String matricule, Date entre) {
        this.id = id;
        this.matricule = matricule;
        this.entre = entre;
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

    public Date getEntre() {
        return entre;
    }

    public void setEntre(Date entre) {
        this.entre = entre;
    }

    public Patient getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Patient idpatient) {
        this.idpatient = idpatient;
    }

    public Chambre getIdchambre() {
        return idchambre;
    }

    public void setIdchambre(Chambre idchambre) {
        this.idchambre = idchambre;
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
        if (!(object instanceof Hospitalisation)) {
            return false;
        }
        Hospitalisation other = (Hospitalisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Hospitalisation[ id=" + id + " ]";
    }
    
}
