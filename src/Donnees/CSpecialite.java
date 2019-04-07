/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author Sabrina Cos
 */
public class CSpecialite {
    
    //attributs Specialite
     
    protected int idSpecialite;
    protected String libelle;
    
    
    
    //Getters et Setters

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public final void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getLibelle() {
        return libelle;
    }

    public final void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    
    //Constructeur
    
    public CSpecialite (int idSpecialite, String libelle){
        setIdSpecialite(idSpecialite);
        setLibelle(libelle);
    
    }
}
