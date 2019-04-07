/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author camilletilmont
 */
public class CTypeIndividu {
    
    // attributs Type Individu
    
    protected int idTypeIndividu;
    protected String libelleTypeIndividu;
    protected float poidsTypeIndividu;

    // Getters et Setters
    
    public int getIdTypeIndividu() {
        return idTypeIndividu;
    }

    public final void setIdTypeIndividu(int idTypeIndividu) {
        this.idTypeIndividu = idTypeIndividu;
    }

    public String getLibelleTypeIndividu() {
        return libelleTypeIndividu;
    }

    public final void setLibelleTypeIndividu(String libelleTypeIndividu) {
        this.libelleTypeIndividu = libelleTypeIndividu;
    }

    public float getPoidsTypeIndividu() {
        return poidsTypeIndividu;
    }

    public final void setPoidsTypeIndividu(float poidsTypeIndividu) {
        this.poidsTypeIndividu = poidsTypeIndividu;
    }
    
    
    // Constructeur Custom
    
    public CTypeIndividu(int idTypeIndi, String libelleTypeIndi, float poidsTypeIndi) {
        setIdTypeIndividu(idTypeIndi);
        setLibelleTypeIndividu(libelleTypeIndi);
        setPoidsTypeIndividu(poidsTypeIndi); 
    
    }
    
}
