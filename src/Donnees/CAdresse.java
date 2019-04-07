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
public final class CAdresse {
    
    //attributs Adresse
    
    protected String numero; 
    protected String rue;
    protected String codePostal;
    protected String Ville;
    
    
    
    //Getters et Settes

    public String getNumero() {
        return numero;
    }

    public final void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }
    
    
    
    //Constructeur
    
    public CAdresse(String numero, String rue, String codePostal, String ville){
        setNumero(numero);
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
    
    }
    
    
    
}
