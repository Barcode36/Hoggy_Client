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
@Table(name = "profil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p")
    , @NamedQuery(name = "Profil.findById", query = "SELECT p FROM Profil p WHERE p.id = :id")
    , @NamedQuery(name = "Profil.findByNom", query = "SELECT p FROM Profil p WHERE p.nom = :nom")})
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofil")
    private List<Administrateur> administrateurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofil")
    private List<Secretaire> secretaireList;
    @OneToMany(mappedBy = "idprofil")
    private List<Comptable> comptableList;
    @OneToMany(mappedBy = "idprofil")
    private List<Medecin> medecinList;
    @OneToMany(mappedBy = "idprofil")
    private List<Infirmier> infirmierList;

    public Profil() {
    }

    public Profil(Integer id) {
        this.id = id;
    }

    public Profil(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Administrateur> getAdministrateurList() {
        return administrateurList;
    }

    public void setAdministrateurList(List<Administrateur> administrateurList) {
        this.administrateurList = administrateurList;
    }

    @XmlTransient
    public List<Secretaire> getSecretaireList() {
        return secretaireList;
    }

    public void setSecretaireList(List<Secretaire> secretaireList) {
        this.secretaireList = secretaireList;
    }

    @XmlTransient
    public List<Comptable> getComptableList() {
        return comptableList;
    }

    public void setComptableList(List<Comptable> comptableList) {
        this.comptableList = comptableList;
    }

    @XmlTransient
    public List<Medecin> getMedecinList() {
        return medecinList;
    }

    public void setMedecinList(List<Medecin> medecinList) {
        this.medecinList = medecinList;
    }

    @XmlTransient
    public List<Infirmier> getInfirmierList() {
        return infirmierList;
    }

    public void setInfirmierList(List<Infirmier> infirmierList) {
        this.infirmierList = infirmierList;
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
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Profil[ id=" + id + " ]";
    }
    
}
