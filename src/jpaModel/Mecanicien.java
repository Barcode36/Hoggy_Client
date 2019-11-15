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
import javax.persistence.Lob;
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
@Table(name = "mecanicien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mecanicien.findAll", query = "SELECT m FROM Mecanicien m")
    , @NamedQuery(name = "Mecanicien.findById", query = "SELECT m FROM Mecanicien m WHERE m.id = :id")
    , @NamedQuery(name = "Mecanicien.findByAdresse", query = "SELECT m FROM Mecanicien m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "Mecanicien.findByLogin", query = "SELECT m FROM Mecanicien m WHERE m.login = :login")
    , @NamedQuery(name = "Mecanicien.findByMail", query = "SELECT m FROM Mecanicien m WHERE m.mail = :mail")
    , @NamedQuery(name = "Mecanicien.findByMatricule", query = "SELECT m FROM Mecanicien m WHERE m.matricule = :matricule")
    , @NamedQuery(name = "Mecanicien.findByNaissance", query = "SELECT m FROM Mecanicien m WHERE m.naissance = :naissance")
    , @NamedQuery(name = "Mecanicien.findByNom", query = "SELECT m FROM Mecanicien m WHERE m.nom = :nom")
    , @NamedQuery(name = "Mecanicien.findByPassword", query = "SELECT m FROM Mecanicien m WHERE m.password = :password")
    , @NamedQuery(name = "Mecanicien.findByPrenom", query = "SELECT m FROM Mecanicien m WHERE m.prenom = :prenom")
    , @NamedQuery(name = "Mecanicien.findByTelephone", query = "SELECT m FROM Mecanicien m WHERE m.telephone = :telephone")
    , @NamedQuery(name = "Mecanicien.findByIdexpert", query = "SELECT m FROM Mecanicien m WHERE m.idexpert = :idexpert")
    , @NamedQuery(name = "Mecanicien.findByIdprofil", query = "SELECT m FROM Mecanicien m WHERE m.idprofil = :idprofil")
    , @NamedQuery(name = "Mecanicien.findByIdsexe", query = "SELECT m FROM Mecanicien m WHERE m.idsexe = :idsexe")})
public class Mecanicien implements Serializable {

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
    @Column(name = "idexpert")
    private Integer idexpert;
    @Column(name = "idprofil")
    private Integer idprofil;
    @Column(name = "idsexe")
    private Integer idsexe;

    public Mecanicien() {
    }

    public Mecanicien(Integer id) {
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

    public Integer getIdexpert() {
        return idexpert;
    }

    public void setIdexpert(Integer idexpert) {
        this.idexpert = idexpert;
    }

    public Integer getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Integer idprofil) {
        this.idprofil = idprofil;
    }

    public Integer getIdsexe() {
        return idsexe;
    }

    public void setIdsexe(Integer idsexe) {
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
        if (!(object instanceof Mecanicien)) {
            return false;
        }
        Mecanicien other = (Mecanicien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Mecanicien[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
