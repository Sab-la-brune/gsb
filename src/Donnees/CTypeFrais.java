/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author admin
 */
public class CTypeFrais {
    
    //attributs Frais
    
    protected String code;
    protected String nom;
    protected float forfait; 
    
    
    
    // Getters et Setters

    public String getCode() {
        return code;
    }

    public final void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public final void setNom(String nom) {
        this.nom = nom;
    }

    public float getForfait() {
        return forfait;
    }

    public final void setForfait(float forfait) {
        this.forfait = forfait;
    }

    
    
    
    //Constructeur
    
    public CTypeFrais (String code, String nom, Float Forfait){
        setCode(code);
        setNom(nom);
        setForfait(Forfait);
    
    }
}
