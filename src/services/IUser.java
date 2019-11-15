package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Administrateur;
import jpaModel.Chambre;
import jpaModel.ConsultationWeb;


public interface IUser extends Remote {
public List listeTypeChambe() throws RemoteException;
    public Administrateur findUser(String login, String pwd) throws RemoteException;
public void InsererChambre(Chambre m) throws RemoteException;
    public List listeChambre() throws RemoteException;
    public void InsererUtilisateur(Administrateur m) throws RemoteException;
  public void InsererConsultationWeb(ConsultationWeb m) throws RemoteException ;
    public void ModifierUtilisateur(Administrateur m) throws RemoteException;

    public int status() throws RemoteException;

    public List listeSexe() throws RemoteException;

    public Administrateur findByMail(String mail) throws RemoteException;

    public List listeAdministrateur() throws RemoteException;

    public void reset(String login) throws RemoteException;

    public void change(String login, String password, String matricule) throws RemoteException;

    public void activer(Administrateur m) throws RemoteException;

    public void bloquer(Administrateur m) throws RemoteException;

    public void addCodeSecret(String m) throws RemoteException;

    public String getCodeSecret() throws RemoteException;
}
