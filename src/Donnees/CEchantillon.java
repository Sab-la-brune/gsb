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
public class CEchantillon { 
    
    // Attributs Echantillon
    
    protected CMedicament medicamentEchantillon;
    protected int quantiteEchantillon;
    
    
    // Getter et Setters

    public CMedicament getMedicamentEchantillon() {
        return medicamentEchantillon;
    }

    public final void setMedicamentEchantillon(CMedicament medicamentEchantillon) {
        this.medicamentEchantillon = medicamentEchantillon;
    }

    public int getQuantiteEchantillon() {
        return quantiteEchantillon;
    }

    public final void setQuantiteEchantillon(int quantiteEchantillon) {
        this.quantiteEchantillon = quantiteEchantillon;
    }
    
    
    // Constructeur Custom
    
    public CEchantillon(CMedicament medEchan, int quantEchan){
        setMedicamentEchantillon(medEchan);
        setQuantiteEchantillon(quantEchan);
    
    
    }
    
}
