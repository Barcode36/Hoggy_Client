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
@Table(name = "secretaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Secretaire.findAll", query = "SELECT s FROM Secretaire s")
    , @NamedQuery(name = "Secretaire.findById", query = "SELECT s FROM Secretaire s WHERE s.id = :id")
    , @NamedQuery(name = "Secretaire.findByAdresse", query = "SELECT s FROM Secretaire s WHERE s.adresse = :adresse")
    , @NamedQuery(name = "Secretaire.findByIdstatus", query = "SELECT s FROM Secretaire s WHERE s.idstatus = :idstatus")
    , @NamedQuery(name = "Secretaire.findByLogin", query = "SELECT s FROM Secretaire s WHERE s.login = :login")
    , @NamedQuery(name = "Secretaire.findByMail", query = "SELECT s FROM Secretaire s WHERE s.mail = :mail")
    , @NamedQuery(name = "Secretaire.findByMatricule", query = "SELECT s FROM Secretaire s WHERE s.matricule = :matricule")
    , @NamedQuery(name = "Secretaire.findByNaissance", query = "SELECT s FROM Secretaire s WHERE s.naissance = :naissance")
    , @NamedQuery(name = "Secretaire.findByNom", query = "SELECT s FROM Secretaire s WHERE s.nom = :nom")
    , @NamedQuery(name = "Secretaire.findByPassword", query = "SELECT s FROM Secretaire s WHERE s.password = :password")
    , @NamedQuery(name = "Secretaire.findByPrenom", query = "SELECT s FROM Secretaire s WHERE s.prenom = :prenom")
    , @NamedQuery(name = "Secretaire.findByTelephone", query = "SELECT s FROM Secretaire s WHERE s.telephone = :telephone")
    , @NamedQuery(name = "Secretaire.findByLog", query = "SELECT s FROM Secretaire s WHERE s.log = :log")})
public class Secretaire implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "photo")
    private byte[] photo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "idstatus")
    private int idstatus;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @Column(name = "matricule")
    private String matricule;
    @Basic(optional = false)
    @Column(name = "naissance")
    @Temporal(TemporalType.DATE)
    private Date naissance;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "log")
    private int log;
    @JoinColumn(name = "idprofil", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Profil idprofil;
    @JoinColumn(name = "idsexe", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sexe idsexe;

    public Secretaire() {
    }

    public Secretaire(Integer id) {
        this.id = id;
    }

    public Secretaire(Integer id, String adresse, int idstatus, String login, String mail, String matricule, Date naissance, String nom, String password, byte[] photo, String prenom, String telephone, int log) {
        this.id = id;
        this.adresse = adresse;
        this.idstatus = idstatus;
        this.login = login;
        this.mail = mail;
        this.matricule = matricule;
        this.naissance = naissance;
        this.nom = nom;
        this.password = password;
        this.photo = photo;
        this.prenom = prenom;
        this.telephone = telephone;
        this.log = log;
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

    public int getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(int idstatus) {
        this.idstatus = idstatus;
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

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
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
        if (!(object instanceof Secretaire)) {
            return false;
        }
        Secretaire other = (Secretaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Secretaire[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
