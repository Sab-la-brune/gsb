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
public class CRegion {
    
    //attributs Region
    
    protected int idRegion;
    protected String nomRegion;
    
    
    
    // Getters et Setters
    
    public int getIdRegion() {
        return idRegion;
    }

    public final void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public final void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }
    
    
    // Constructeur
    
    public CRegion (int idRegion, String nomRegion){
        setIdRegion(idRegion);
        setNomRegion(nomRegion);
    }
}
