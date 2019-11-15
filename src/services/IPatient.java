package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Administrateur;
import jpaModel.DemandeRv;
import jpaModel.Patient;
import jpaModel.Ticket;

public interface IPatient extends Remote {
     public List listeDemande() throws RemoteException;
 public Ticket findticket( String pwd) throws RemoteException;
    public Administrateur findUser(String login, String pwd) throws RemoteException;
 public Patient find(String matricule) throws RemoteException;
    public void activer(Ticket m) throws RemoteException;
   public void valider(DemandeRv m) throws RemoteException;

    public void refuser(DemandeRv m) throws RemoteException;
    public void bloquer(Ticket m) throws RemoteException;
  public void ModifierPatient(Patient m) throws RemoteException;
    public void InsererPatient(Patient m) throws RemoteException;

    public void InsererTicket(Ticket t) throws RemoteException;

    public void ModifierUtilisateur(Administrateur m) throws RemoteException;

    public List listeticket() throws RemoteException;

    public int status() throws RemoteException;

    public List listeSexe() throws RemoteException;

    public Administrateur findByMail(String mail) throws RemoteException;

    public List listePatient() throws RemoteException;

    public void reset(String login) throws RemoteException;

    public void change(String login, String password, String matricule) throws RemoteException;

    public void activer(Administrateur m) throws RemoteException;
  

    public void bloquer(Administrateur m) throws RemoteException;

    public void addCodeSecret(String m) throws RemoteException;

    public String getCodeSecret() throws RemoteException;
}
