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
public class CSecteur {
    
    //attributs Secteur
    
    protected int idSecteur;
    protected String nomSecteur;
    
    
    
    // Getters et Setters
    
    public int getIdSecteur() {
        return idSecteur;
    }

    public final void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    public String getNomSecteur() {
        return nomSecteur;
    }

    public void setNomSecteur(String nomSecteur) {
        this.nomSecteur = nomSecteur;
    }
    
    
    
    // Constructeur
    
    public CSecteur (int idSecteur, String nomSecteur){
        setIdSecteur(idSecteur);
        setNomSecteur(nomSecteur);
    
    }
}
