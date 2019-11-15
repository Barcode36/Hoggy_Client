/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

/**
 *
 * @author Fallou
 */
public class Type {
    public String mat;
     public String type;
      public String capacite;
       public String superficie;

    public Type() {
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public Type(String mat, String type, String capacite, String superficie) {
        this.mat = mat;
        this.type = type;
        this.capacite = capacite;
        this.superficie = superficie;
    }
       
    
}
