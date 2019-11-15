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
@Table(name = "infirmier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infirmier.findAll", query = "SELECT i FROM Infirmier i")
    , @NamedQuery(name = "Infirmier.findById", query = "SELECT i FROM Infirmier i WHERE i.id = :id")
    , @NamedQuery(name = "Infirmier.findByAdresse", query = "SELECT i FROM Infirmier i WHERE i.adresse = :adresse")
    , @NamedQuery(name = "Infirmier.findByLogin", query = "SELECT i FROM Infirmier i WHERE i.login = :login")
    , @NamedQuery(name = "Infirmier.findByMail", query = "SELECT i FROM Infirmier i WHERE i.mail = :mail")
    , @NamedQuery(name = "Infirmier.findByMatricule", query = "SELECT i FROM Infirmier i WHERE i.matricule = :matricule")
    , @NamedQuery(name = "Infirmier.findByNaissance", query = "SELECT i FROM Infirmier i WHERE i.naissance = :naissance")
    , @NamedQuery(name = "Infirmier.findByNom", query = "SELECT i FROM Infirmier i WHERE i.nom = :nom")
    , @NamedQuery(name = "Infirmier.findByPassword", query = "SELECT i FROM Infirmier i WHERE i.password = :password")
    , @NamedQuery(name = "Infirmier.findByPrenom", query = "SELECT i FROM Infirmier i WHERE i.prenom = :prenom")
    , @NamedQuery(name = "Infirmier.findByTelephone", query = "SELECT i FROM Infirmier i WHERE i.telephone = :telephone")})
public class Infirmier implements Serializable {

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
    @JoinColumn(name = "idspecialite", referencedColumnName = "id")
    @ManyToOne
    private Specialite idspecialite;

    public Infirmier() {
    }

    public Infirmier(Integer id) {
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

    public Specialite getIdspecialite() {
        return idspecialite;
    }

    public void setIdspecialite(Specialite idspecialite) {
        this.idspecialite = idspecialite;
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
        if (!(object instanceof Infirmier)) {
            return false;
        }
        Infirmier other = (Infirmier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Infirmier[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
