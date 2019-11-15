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
import javax.persistence.Id;
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
@Table(name = "demande_rv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemandeRv.findAll", query = "SELECT d FROM DemandeRv d")
    , @NamedQuery(name = "DemandeRv.findById", query = "SELECT d FROM DemandeRv d WHERE d.id = :id")
    , @NamedQuery(name = "DemandeRv.findByAdresse", query = "SELECT d FROM DemandeRv d WHERE d.adresse = :adresse")
    , @NamedQuery(name = "DemandeRv.findByDaterv", query = "SELECT d FROM DemandeRv d WHERE d.daterv = :daterv")
    , @NamedQuery(name = "DemandeRv.findByEmail", query = "SELECT d FROM DemandeRv d WHERE d.email = :email")
    , @NamedQuery(name = "DemandeRv.findByNaissance", query = "SELECT d FROM DemandeRv d WHERE d.naissance = :naissance")
    , @NamedQuery(name = "DemandeRv.findByNom", query = "SELECT d FROM DemandeRv d WHERE d.nom = :nom")
    , @NamedQuery(name = "DemandeRv.findByPrenom", query = "SELECT d FROM DemandeRv d WHERE d.prenom = :prenom")
    , @NamedQuery(name = "DemandeRv.findByTelephone", query = "SELECT d FROM DemandeRv d WHERE d.telephone = :telephone")
    , @NamedQuery(name = "DemandeRv.findByValide", query = "SELECT d FROM DemandeRv d WHERE d.valide = :valide")
    , @NamedQuery(name = "DemandeRv.findByMatricule", query = "SELECT d FROM DemandeRv d WHERE d.matricule = :matricule")})
public class DemandeRv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "daterv")
    @Temporal(TemporalType.DATE)
    private Date daterv;
    @Column(name = "email")
    private String email;
    @Column(name = "naissance")
    @Temporal(TemporalType.DATE)
    private Date naissance;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "valide")
    private String valide;
    @Column(name = "matricule")
    private String matricule;

    public DemandeRv() {
    }

    public DemandeRv(Long id) {
        this.id = id;
    }

    public DemandeRv(Long id, String valide) {
        this.id = id;
        this.valide = valide;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDaterv() {
        return daterv;
    }

    public void setDaterv(Date daterv) {
        this.daterv = daterv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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
        if (!(object instanceof DemandeRv)) {
            return false;
        }
        DemandeRv other = (DemandeRv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.DemandeRv[ id=" + id + " ]";
    }
    
}
