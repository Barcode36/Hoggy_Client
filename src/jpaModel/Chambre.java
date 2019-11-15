/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaModel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fallou
 */
@Entity
@Table(name = "chambre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chambre.findAll", query = "SELECT c FROM Chambre c")
    , @NamedQuery(name = "Chambre.findById", query = "SELECT c FROM Chambre c WHERE c.id = :id")
    , @NamedQuery(name = "Chambre.findByMatricule", query = "SELECT c FROM Chambre c WHERE c.matricule = :matricule")
    , @NamedQuery(name = "Chambre.findBySuperficie", query = "SELECT c FROM Chambre c WHERE c.superficie = :superficie")
    , @NamedQuery(name = "Chambre.findByCapacite", query = "SELECT c FROM Chambre c WHERE c.capacite = :capacite")})
public class Chambre implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idchambre")
    private List<Hospitalisation> hospitalisationList;

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
    @Column(name = "superficie")
    private String superficie;
    @Basic(optional = false)
    @Column(name = "capacite")
    private String capacite;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Typech type;

    public Chambre() {
    }

    public Chambre(Integer id) {
        this.id = id;
    }

    public Chambre(Integer id, String matricule, String superficie, String capacite) {
        this.id = id;
        this.matricule = matricule;
        this.superficie = superficie;
        this.capacite = capacite;
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

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public Typech getType() {
        return type;
    }

    public void setType(Typech type) {
        this.type = type;
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
        if (!(object instanceof Chambre)) {
            return false;
        }
        Chambre other = (Chambre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Chambre[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Hospitalisation> getHospitalisationList() {
        return hospitalisationList;
    }

    public void setHospitalisationList(List<Hospitalisation> hospitalisationList) {
        this.hospitalisationList = hospitalisationList;
    }
    
}
