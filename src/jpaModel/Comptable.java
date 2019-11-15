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
@Table(name = "comptable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comptable.findAll", query = "SELECT c FROM Comptable c")
    , @NamedQuery(name = "Comptable.findById", query = "SELECT c FROM Comptable c WHERE c.id = :id")
    , @NamedQuery(name = "Comptable.findByAdresse", query = "SELECT c FROM Comptable c WHERE c.adresse = :adresse")
    , @NamedQuery(name = "Comptable.findByLogin", query = "SELECT c FROM Comptable c WHERE c.login = :login")
    , @NamedQuery(name = "Comptable.findByMail", query = "SELECT c FROM Comptable c WHERE c.mail = :mail")
    , @NamedQuery(name = "Comptable.findByMatricule", query = "SELECT c FROM Comptable c WHERE c.matricule = :matricule")
    , @NamedQuery(name = "Comptable.findByNaissance", query = "SELECT c FROM Comptable c WHERE c.naissance = :naissance")
    , @NamedQuery(name = "Comptable.findByNom", query = "SELECT c FROM Comptable c WHERE c.nom = :nom")
    , @NamedQuery(name = "Comptable.findByPassword", query = "SELECT c FROM Comptable c WHERE c.password = :password")
    , @NamedQuery(name = "Comptable.findByPrenom", query = "SELECT c FROM Comptable c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Comptable.findByTelephone", query = "SELECT c FROM Comptable c WHERE c.telephone = :telephone")})
public class Comptable implements Serializable {

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "login")
    private String login;
    @Column(name = "mail")
    private String mail;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "naissance")
    @Temporal(TemporalType.DATE)
    private Date naissance;
    @Column(name = "nom")
    private String nom;
    @Column(name = "password")
    private String password;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "telephone")
    private String telephone;
    @JoinColumn(name = "idprofil", referencedColumnName = "id")
    @ManyToOne
    private Profil idprofil;
    @JoinColumn(name = "idsexe", referencedColumnName = "id")
    @ManyToOne
    private Sexe idsexe;

    public Comptable() {
    }

    public Comptable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Profil getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Profil idprofil) {
        this.idprofil = idprofil;
    }

    public Sexe getIdsexe() {
        return idsexe;
    }

    public void setIdsexe(Sexe idsexe) {
        this.idsexe = idsexe;
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
        if (!(object instanceof Comptable)) {
            return false;
        }
        Comptable other = (Comptable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Comptable[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
