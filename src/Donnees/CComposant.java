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
public class CComposant {
    
    //attributs Composant
    protected int idComposant;
    protected String nomComposant;

    
    
    //Getter et Setter
    public int getIdComposant() {
        return idComposant;
    }

    public final void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }

    public String getNomComposant() {
        return nomComposant;
    }

    public final void setNomComposant(String nomComposant) {
        this.nomComposant = nomComposant;
    }
    
    //Constructeur Custom
    
    public CComposant(int idComp, String nomComp){
        setIdComposant(idComp);
        setNomComposant(nomComp);
    
    }
    
    
}
