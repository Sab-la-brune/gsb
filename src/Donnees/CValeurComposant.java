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
public class CValeurComposant {
    
    // attributs Valeur de Composant
    protected CComposant composant;
    protected float quantiteValComposant;
    protected String uniteValComposant;
    
    
    
    // Getter et Setter
    
    public CComposant getComposant() {
        return composant;
    }

    public final void setComposant(CComposant composant) {
        this.composant = composant;
    }

    public float getQuantite() {
        return quantiteValComposant;
    }

    public final void setQuantite(float quantite) {
        this.quantiteValComposant = quantite;
    }

    public String getUnite() {
        return uniteValComposant;
    }

    public final void setUnite(String unite) {
        this.uniteValComposant = unite;
    }
    
    
    
    // Constructeur Custom

    public CValeurComposant(CComposant compo, float quant, String unit){
        setComposant(compo);
        setQuantite(quant);
        setUnite(unit);
        
    
    }
    
    
}
