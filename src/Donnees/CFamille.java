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
public class CFamille {
    
    // attributs Famille de Medicament
    
    protected int idFamille;
    protected String nomFamille;

    // Getter et Setter
    
    public int getIdFamille() {
        return idFamille;
    }

    public final void setIdFamille(int idFamille) {
        this.idFamille = idFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public final void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }
    
    // Constructeur Custom
    
    public CFamille(int id,String nom){
        setIdFamille(id);
        setNomFamille(nom);
        
    
    
    }
    
}
