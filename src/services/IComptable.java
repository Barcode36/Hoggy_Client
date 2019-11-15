/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Comptable;
import jpaModel.Secretaire;

public interface IComptable extends Remote {

    public void InsererComptable(Comptable m) throws RemoteException;

    public void ModifierComptable(Comptable m) throws RemoteException;

    public List listeComptable() throws RemoteException;
          public void InsererSecretaire(Secretaire m) throws RemoteException;

    public void ModifierSecretaire(Secretaire m) throws RemoteException;

    public List listeSecretaire() throws RemoteException;
}
