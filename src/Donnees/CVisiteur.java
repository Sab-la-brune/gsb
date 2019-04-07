/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.GregorianCalendar;

/**
 *
 * @author Sabrina Cos
 */
public class CVisiteur {
    
    //attributs Visiteur
    
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected GregorianCalendar dateEmbauche;
    protected String role;
    protected CAdresse adresse;
    protected int departement;

    public CVisiteur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // Getters et Setters
    
    public String getMatricule() {
        return matricule;
    }

    public final void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public final void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public final void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public GregorianCalendar getDateEmbauche() {
        return dateEmbauche;
    }

    public final void setDateEmbauche(GregorianCalendar dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getRole() {
        return role;
    }

    public final void setRole(String role) {
        this.role = role;
    }

    public CAdresse getAdresse() {
        return adresse;
    }

    public final void setAdresse(CAdresse adresse) {
        this.adresse = adresse;
    }

    public int getDepartement() {
        return departement;
    }

    public final void setDepartement(int departement) {
        this.departement = departement;
    }
    
    // Constructeur
    
    public CVisiteur (String matricule, String nom, String prenom, GregorianCalendar dateEmbauche,
            String role, CAdresse adresse, int departement){
        setMatricule(matricule);
        setNom(nom);
        setPrenom(prenom);
        setDateEmbauche(dateEmbauche);
        setRole(role);
        setAdresse(adresse);
        setDepartement(departement);
    
    }
    
}
