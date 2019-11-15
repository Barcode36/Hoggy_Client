/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fallou
 */
@Entity
@Table(name = "medecin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m")
    , @NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.id = :id")
    , @NamedQuery(name = "Medecin.findByAdresse", query = "SELECT m FROM Medecin m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "Medecin.findByLogin", query = "SELECT m FROM Medecin m WHERE m.login = :login")
    , @NamedQuery(name = "Medecin.findByMail", query = "SELECT m FROM Medecin m WHERE m.mail = :mail")
    , @NamedQuery(name = "Medecin.findByMatricule", query = "SELECT m FROM Medecin m WHERE m.matricule = :matricule")
    , @NamedQuery(name = "Medecin.findByNaissance", query = "SELECT m FROM Medecin m WHERE m.naissance = :naissance")
    , @NamedQuery(name = "Medecin.findByNom", query = "SELECT m FROM Medecin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Medecin.findByPassword", query = "SELECT m FROM Medecin m WHERE m.password = :password")
    , @NamedQuery(name = "Medecin.findByPrenom", query = "SELECT m FROM Medecin m WHERE m.prenom = :prenom")
    , @NamedQuery(name = "Medecin.findByTelephone", query = "SELECT m FROM Medecin m WHERE m.telephone = :telephone")})
public class Medecin implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<Vaccinations> vaccinationsList;
    @JoinColumn(name = "idsexe", referencedColumnName = "id")
    @ManyToOne
    private Sexe idsexe;
    @JoinColumn(name = "idprofil", referencedColumnName = "id")
    @ManyToOne
    private Profil idprofil;
    @JoinColumn(name = "idspecialite", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Specialite idspecialite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<Consultations> consultationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<Carnet> carnetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<Ordonnance> ordonnanceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<RendezVous> rendezVousList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddocteur")
    private List<Simplevaccination> simplevaccinationList;

    public Medecin() {
    }

    public Medecin(Integer id) {
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

    @XmlTransient
    public List<Vaccinations> getVaccinationsList() {
        return vaccinationsList;
    }

    public void setVaccinationsList(List<Vaccinations> vaccinationsList) {
        this.vaccinationsList = vaccinationsList;
    }

    public Sexe getIdsexe() {
        return idsexe;
    }

    public void setIdsexe(Sexe idsexe) {
        this.idsexe = idsexe;
    }

    public Profil getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Profil idprofil) {
        this.idprofil = idprofil;
    }

    public Specialite getIdspecialite() {
        return idspecialite;
    }

    public void setIdspecialite(Specialite idspecialite) {
        this.idspecialite = idspecialite;
    }

    @XmlTransient
    public List<Consultations> getConsultationsList() {
        return consultationsList;
    }

    public void setConsultationsList(List<Consultations> consultationsList) {
        this.consultationsList = consultationsList;
    }

    @XmlTransient
    public List<Carnet> getCarnetList() {
        return carnetList;
    }

    public void setCarnetList(List<Carnet> carnetList) {
        this.carnetList = carnetList;
    }

    @XmlTransient
    public List<Ordonnance> getOrdonnanceList() {
        return ordonnanceList;
    }

    public void setOrdonnanceList(List<Ordonnance> ordonnanceList) {
        this.ordonnanceList = ordonnanceList;
    }

    @XmlTransient
    public List<RendezVous> getRendezVousList() {
        return rendezVousList;
    }

    public void setRendezVousList(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @XmlTransient
    public List<Simplevaccination> getSimplevaccinationList() {
        return simplevaccinationList;
    }

    public void setSimplevaccinationList(List<Simplevaccination> simplevaccinationList) {
        this.simplevaccinationList = simplevaccinationList;
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
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Medecin[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
