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
@Table(name = "carnet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carnet.findAll", query = "SELECT c FROM Carnet c")
    , @NamedQuery(name = "Carnet.findById", query = "SELECT c FROM Carnet c WHERE c.id = :id")
    , @NamedQuery(name = "Carnet.findByMatricule", query = "SELECT c FROM Carnet c WHERE c.matricule = :matricule")
    , @NamedQuery(name = "Carnet.findByDate", query = "SELECT c FROM Carnet c WHERE c.date = :date")
    , @NamedQuery(name = "Carnet.findByVacc1", query = "SELECT c FROM Carnet c WHERE c.vacc1 = :vacc1")
    , @NamedQuery(name = "Carnet.findByVacc2", query = "SELECT c FROM Carnet c WHERE c.vacc2 = :vacc2")
    , @NamedQuery(name = "Carnet.findByVacc3", query = "SELECT c FROM Carnet c WHERE c.vacc3 = :vacc3")
    , @NamedQuery(name = "Carnet.findByVacc4", query = "SELECT c FROM Carnet c WHERE c.vacc4 = :vacc4")
    , @NamedQuery(name = "Carnet.findByVacc5", query = "SELECT c FROM Carnet c WHERE c.vacc5 = :vacc5")
    , @NamedQuery(name = "Carnet.findByVacc6", query = "SELECT c FROM Carnet c WHERE c.vacc6 = :vacc6")
    , @NamedQuery(name = "Carnet.findByVacc7", query = "SELECT c FROM Carnet c WHERE c.vacc7 = :vacc7")
    , @NamedQuery(name = "Carnet.findByVacc8", query = "SELECT c FROM Carnet c WHERE c.vacc8 = :vacc8")
    , @NamedQuery(name = "Carnet.findByVacc9", query = "SELECT c FROM Carnet c WHERE c.vacc9 = :vacc9")
    , @NamedQuery(name = "Carnet.findByVacc10", query = "SELECT c FROM Carnet c WHERE c.vacc10 = :vacc10")
    , @NamedQuery(name = "Carnet.findByVacc11", query = "SELECT c FROM Carnet c WHERE c.vacc11 = :vacc11")
    , @NamedQuery(name = "Carnet.findByVacc12", query = "SELECT c FROM Carnet c WHERE c.vacc12 = :vacc12")
    , @NamedQuery(name = "Carnet.findByVacc13", query = "SELECT c FROM Carnet c WHERE c.vacc13 = :vacc13")
    , @NamedQuery(name = "Carnet.findByVac14", query = "SELECT c FROM Carnet c WHERE c.vac14 = :vac14")
    , @NamedQuery(name = "Carnet.findByVacc15", query = "SELECT c FROM Carnet c WHERE c.vacc15 = :vacc15")
    , @NamedQuery(name = "Carnet.findByVacc16", query = "SELECT c FROM Carnet c WHERE c.vacc16 = :vacc16")})
public class Carnet implements Serializable {

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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "vacc1")
    private int vacc1;
    @Basic(optional = false)
    @Column(name = "vacc2")
    private int vacc2;
    @Basic(optional = false)
    @Column(name = "vacc3")
    private int vacc3;
    @Basic(optional = false)
    @Column(name = "vacc4")
    private int vacc4;
    @Basic(optional = false)
    @Column(name = "vacc5")
    private int vacc5;
    @Basic(optional = false)
    @Column(name = "vacc6")
    private int vacc6;
    @Basic(optional = false)
    @Column(name = "vacc7")
    private int vacc7;
    @Basic(optional = false)
    @Column(name = "vacc8")
    private int vacc8;
    @Basic(optional = false)
    @Column(name = "vacc9")
    private int vacc9;
    @Basic(optional = false)
    @Column(name = "vacc10")
    private int vacc10;
    @Basic(optional = false)
    @Column(name = "vacc11")
    private int vacc11;
    @Basic(optional = false)
    @Column(name = "vacc12")
    private int vacc12;
    @Basic(optional = false)
    @Column(name = "vacc13")
    private int vacc13;
    @Basic(optional = false)
    @Column(name = "vac14")
    private int vac14;
    @Basic(optional = false)
    @Column(name = "vacc15")
    private int vacc15;
    @Basic(optional = false)
    @Column(name = "vacc16")
    private int vacc16;
    @JoinColumn(name = "idpatient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient idpatient;
    @JoinColumn(name = "iddocteur", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin iddocteur;

    public Carnet() {
    }

    public Carnet(Integer id) {
        this.id = id;
    }

    public Carnet(Integer id, String matricule, Date date, int vacc1, int vacc2, int vacc3, int vacc4, int vacc5, int vacc6, int vacc7, int vacc8, int vacc9, int vacc10, int vacc11, int vacc12, int vacc13, int vac14, int vacc15, int vacc16) {
        this.id = id;
        this.matricule = matricule;
        this.date = date;
        this.vacc1 = vacc1;
        this.vacc2 = vacc2;
        this.vacc3 = vacc3;
        this.vacc4 = vacc4;
        this.vacc5 = vacc5;
        this.vacc6 = vacc6;
        this.vacc7 = vacc7;
        this.vacc8 = vacc8;
        this.vacc9 = vacc9;
        this.vacc10 = vacc10;
        this.vacc11 = vacc11;
        this.vacc12 = vacc12;
        this.vacc13 = vacc13;
        this.vac14 = vac14;
        this.vacc15 = vacc15;
        this.vacc16 = vacc16;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVacc1() {
        return vacc1;
    }

    public void setVacc1(int vacc1) {
        this.vacc1 = vacc1;
    }

    public int getVacc2() {
        return vacc2;
    }

    public void setVacc2(int vacc2) {
        this.vacc2 = vacc2;
    }

    public int getVacc3() {
        return vacc3;
    }

    public void setVacc3(int vacc3) {
        this.vacc3 = vacc3;
    }

    public int getVacc4() {
        return vacc4;
    }

    public void setVacc4(int vacc4) {
        this.vacc4 = vacc4;
    }

    public int getVacc5() {
        return vacc5;
    }

    public void setVacc5(int vacc5) {
        this.vacc5 = vacc5;
    }

    public int getVacc6() {
        return vacc6;
    }

    public void setVacc6(int vacc6) {
        this.vacc6 = vacc6;
    }

    public int getVacc7() {
        return vacc7;
    }

    public void setVacc7(int vacc7) {
        this.vacc7 = vacc7;
    }

    public int getVacc8() {
        return vacc8;
    }

    public void setVacc8(int vacc8) {
        this.vacc8 = vacc8;
    }

    public int getVacc9() {
        return vacc9;
    }

    public void setVacc9(int vacc9) {
        this.vacc9 = vacc9;
    }

    public int getVacc10() {
        return vacc10;
    }

    public void setVacc10(int vacc10) {
        this.vacc10 = vacc10;
    }

    public int getVacc11() {
        return vacc11;
    }

    public void setVacc11(int vacc11) {
        this.vacc11 = vacc11;
    }

    public int getVacc12() {
        return vacc12;
    }

    public void setVacc12(int vacc12) {
        this.vacc12 = vacc12;
    }

    public int getVacc13() {
        return vacc13;
    }

    public void setVacc13(int vacc13) {
        this.vacc13 = vacc13;
    }

    public int getVac14() {
        return vac14;
    }

    public void setVac14(int vac14) {
        this.vac14 = vac14;
    }

    public int getVacc15() {
        return vacc15;
    }

    public void setVacc15(int vacc15) {
        this.vacc15 = vacc15;
    }

    public int getVacc16() {
        return vacc16;
    }

    public void setVacc16(int vacc16) {
        this.vacc16 = vacc16;
    }

    public Patient getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(Patient idpatient) {
        this.idpatient = idpatient;
    }

    public Medecin getIddocteur() {
        return iddocteur;
    }

    public void setIddocteur(Medecin iddocteur) {
        this.iddocteur = iddocteur;
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
        if (!(object instanceof Carnet)) {
            return false;
        }
        Carnet other = (Carnet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpaModel.Carnet[ id=" + id + " ]";
    }
    
}
