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
public final class CFrais {
    
    //attributs Frais
     
    protected int quantiteFrais;
    protected float montantFrais;
    protected CTypeFrais typeFrais;
    
    
    // Getters et Setters

    public int getQuantiteFrais() {
        return quantiteFrais;
    }

    public final void setQuantiteFrais(int quantiterais) {
        this.quantiteFrais = quantiterais;
    }

    public float getMontantFrais() {
        return montantFrais;
    }

    public final void setMontantFrais(float montantFrais) {
        this.montantFrais = montantFrais;
    }
    
    public CTypeFrais getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(CTypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }
    
    
    //Constructeur
    
    public CFrais (int quantite, float montant, CTypeFrais type){
        setQuantiteFrais(quantite);
        setMontantFrais(montant);
        setTypeFrais (type);
    
    }
    
    
}
