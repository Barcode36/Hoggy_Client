/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

public class Genre {

    public Genre() {
    }

                public String nom;

                public Genre(String nom) {
                    this.nom = nom;
                }

                public String getNom() {
                    return nom;
                }

                public void setNom(String nom) {
                    this.nom = nom;
                }

                @Override
                public String toString() {
                    return nom;
                }
}