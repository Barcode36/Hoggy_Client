/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Allergie;
import jpaModel.Carnet;
import jpaModel.Chambre;
import jpaModel.Comptable;
import jpaModel.ConsultationWeb;
import jpaModel.Consultations;
import jpaModel.Historique;
import jpaModel.Hospitalisation;
import jpaModel.Medecin;
import jpaModel.Observation;
import jpaModel.Ordonnance;
import jpaModel.OrdonnanceWeb;
import jpaModel.RenderVousWeb;
import jpaModel.RendezVous;
import jpaModel.Simplevaccination;
import jpaModel.Vaccinations;

/**
 *
 * @author Fallou
 */
public interface IConsultation extends  Remote{
    public void InsererConsultationWeb(ConsultationWeb m) throws RemoteException;
      public void InsererCarnet(Carnet m) throws RemoteException;
        public void InsererConsultation(Consultations m) throws RemoteException;
  public void  InsererHistorique(Historique h)throws RemoteException;
             public void InsererObservation(Observation o) throws RemoteException;
         public void  InsererAllergie(Allergie a)throws RemoteException;
            public List<Consultations> listeConsultations()throws RemoteException;
               public Consultations find(String matricule) throws RemoteException;
                  public void InsererRV(RendezVous m) throws RemoteException; 
    public Allergie findAllergie(String matricule) throws RemoteException;
      public Historique findHistorique(String matricule) throws RemoteException;
        public Observation findObservation(String matricule) throws RemoteException;
           public void InsererOrdonnance(Ordonnance m) throws RemoteException;
         public List<RendezVous> listeRv()throws RemoteException;
          public int getNumberConsultation()throws RemoteException;
          public List<Ordonnance> listeOrdonnance()throws RemoteException;
             public  void updateRenderVous(RendezVous v) throws RemoteException;
              public  RendezVous findRenderVous(String matricule) throws RemoteException;
     public void InsererSimpleVaccin(Simplevaccination m) throws RemoteException;
      public List<Carnet> listCarnet()throws RemoteException;
         public  void updateCarnet(Carnet v) throws RemoteException;
          public Carnet findcarnet(String matricule) throws RemoteException;
            public  void updateRenderVousWeb(RendezVous v) throws RemoteException;
       public void    InsererRenderVousWeb (RenderVousWeb m) throws RemoteException;
        public void InsererOrdonnanceWeb(OrdonnanceWeb m) throws RemoteException;
            public void InsererHospitalisation(Hospitalisation a) throws RemoteException;
 public Chambre findChambre(String matricule) throws RemoteException;
    public List listeHospitalisation() throws RemoteException;
     public Hospitalisation findHospi(String matricule) throws RemoteException;
}
