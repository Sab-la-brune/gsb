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
public class CTypePraticien {
    
    //attributs Adresse
    
    protected int idType;
    protected String libelle;
    protected String lieu;
    
    
    
    //Getters et Setters

    public int getIdType() {
        return idType;
    }

    public final void setIdType(int idType) {
        this.idType = idType;
    }

    public String getLibelle() {
        return libelle;
    }

    public final void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLieu() {
        return lieu;
    }

    public final void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    
    
    //Constructeur
    
    public CTypePraticien(int idType, String libelle, String lieu){
        setIdType(idType);
        setLibelle(libelle);
        setLieu(lieu);
        
    
    }
    
}
