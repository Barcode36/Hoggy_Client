/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jpaModel.Administrateur;
import jpaModel.Medecin;

/**
 *
 * @author Fallou
 */
public interface IMedecin  extends Remote{
 
    public void InsererMedecin(Medecin m) throws RemoteException;

    public void ModifierMedecin(Medecin m) throws RemoteException;

    public List listeMedecin() throws RemoteException;
       public Medecin find(String matricule) throws RemoteException;


}
