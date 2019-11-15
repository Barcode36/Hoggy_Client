package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Secretaire;

public interface ISecretaire extends Remote {

    public void InsererSecretaire(Secretaire m) throws RemoteException;

    public void ModifierSecretaire(Secretaire m) throws RemoteException;

    public List listeSecretaire() throws RemoteException;
}
