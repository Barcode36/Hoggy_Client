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
@Table(name = "patient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
    , @NamedQuery(name = "Patient.findById", query = "SELECT p FROM Patient p WHERE p.id = :id")
    , @NamedQuery(name = "Patient.findByAdresse", query = "SELECT p FROM Patient p WHERE p.adresse = :adresse")
    , @NamedQuery(name = "Patient.findByMail", query = "SELECT p FROM Patient p WHERE p.mail = :mail")
    , @NamedQuery(name = "Patient.findByMatricule", query = "SELECT p FROM Patient p WHERE p.matricule = :matricule")
    , @NamedQuery(name = "Patient.findByNaissance", query = "SELECT p FROM Patient p WHERE p.naissance = :naissance")
    , @NamedQuery(name = "Patient.findByNom", query = "SELECT p FROM Patient p WHERE p.nom = :nom")
    , @NamedQuery(name = "Patient.findByPrenom", query = "SELECT p FROM Patient p WHERE p.prenom = :prenom")
    , @NamedQuery(name = "Patient.findByTelephone", query = "SELECT p FROM Patient p WHERE p.telephone = :telephone")
    , @NamedQuery(name = "Patient.findByIdticket", query = "SELECT p FROM Patient p WHERE p.idticket = :idticket")
    , @NamedQuery(name = "Patient.findByHosptaliser", query = "SELECT p FROM Patient p WHERE p.hosptaliser = :hosptaliser")})
public class Patient implements Serializable {

    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Hospitalisation> hospitalisationList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "mail")
    private String mail;
    @Column(name = "matricule")
    private String matricule;
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
    @Column(name = "idticket")
    private String idticket;
    @Basic(optional = false)
    @Column(name = "hosptaliser")
    private int hosptaliser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Vaccinations> vaccinationsList;
    @JoinColumn(name = "idsexe", referencedColumnName = "id")
    @ManyToOne
    private Sexe idsexe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Consultations> consultationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Carnet> carnetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Ordonnance> ordonnanceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<RendezVous> rendezVousList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpatient")
    private List<Simplevaccination> simplevaccinationList;

    public Patient() {
    }

    public Patient(Integer id) {
        this.id = id;
    }

    public Patient(Integer id, String idticket, int hosptaliser) {
        this.id = id;
        this.idticket = idticket;
        this.hosptaliser = hosptaliser;
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

    public String getIdticket() {
        return idticket;
    }

    public void setIdticket(String idticket) {
        this.idticket = idticket;
    }

    public int getHosptaliser() {
        return hosptaliser;
    }

    public void setHosptaliser(int hosptaliser) {
        this.hosptaliser = hosptaliser;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Patient[ id=" + id + " ]";
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @XmlTransient
    public List<Hospitalisation> getHospitalisationList() {
        return hospitalisationList;
    }

    public void setHospitalisationList(List<Hospitalisation> hospitalisationList) {
        this.hospitalisationList = hospitalisationList;
    }
    
}
