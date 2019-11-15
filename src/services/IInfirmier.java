package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Infirmier;

public interface IInfirmier extends Remote {

    public void InsererInfirmier(Infirmier m) throws RemoteException;

    public void ModifierInfirmier(Infirmier m) throws RemoteException;

    public List listeInfirmier() throws RemoteException;
}
