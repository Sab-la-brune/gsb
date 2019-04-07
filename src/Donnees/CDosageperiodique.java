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
public class CDosageperiodique {
    
    // attributs Dosage Periodique
    protected int idDosage;
    protected int quantiteDosage;
    protected String uniteDosage;

    
    // Getters et Setters
    
    public int getIdDosage() {
        return idDosage;
    }

    public final void setIdDosage(int idDosage) {
        this.idDosage = idDosage;
    }

    public int getQuantiteDosage() {
        return quantiteDosage;
    }

    public final void setQuantiteDosage(int quantiteDosage) {
        this.quantiteDosage = quantiteDosage;
    }

    public String getUniteDosage() {
        return uniteDosage;
    }

    public final void setUniteDosage(String uniteDosage) {
        this.uniteDosage = uniteDosage;
    }
    
    
    // Construteur Custom
    
    public CDosageperiodique(int idDos, int quantiteDos, String uniteDos){
        setIdDosage(idDos);
        setQuantiteDosage(quantiteDos);
        setUniteDosage(uniteDos); 
    
    }
            
    
}
