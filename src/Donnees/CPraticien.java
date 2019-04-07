/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.List;

/**
 *
 * @author Sabrina Cos
 */
public class CPraticien { 
    
    //attributs Adresse
    
    protected int idPraticien; 
    protected String nom;
    protected String prenom;
    protected CAdresse adresse;
    protected CTypePraticien typePraticien;
    protected List<CSpePossede> specialiteList;
    protected int coeffNotoriete;
    
    
    
    //Getters et Setters

    public int getIdPraticien() {
        return idPraticien;
    }

    public final void setIdPraticien(int idPraticien) {
        this.idPraticien = idPraticien;
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

    public CAdresse getAdresse() {
        return adresse;
    }

    public final void setAdresse(CAdresse adresse) {
        this.adresse = adresse;
    }

    public CTypePraticien getTypePraticien() {
        return typePraticien;
    }

    public final void setTypePraticien(CTypePraticien typePraticien) {
        this.typePraticien = typePraticien;
    }

    public List<CSpePossede> getSpecialiteList() {
        return specialiteList;
    }

    public final void setSpecialiteList(List<CSpePossede> specialiteList) {
        this.specialiteList = specialiteList;
    }

    public int getCoeffNotoriete() {
        return coeffNotoriete;
    }

    public final void setCoeffNotoriete(int coeffNotoriete) {
        this.coeffNotoriete = coeffNotoriete;
    }
    
    
    
    //Constructeur
    
    public CPraticien (int idPatricien, String nom, String prenom, CAdresse adresse,
            CTypePraticien typePraticien, List<CSpePossede> specialiteList, int coeffNotoriete){
        setIdPraticien(idPraticien);
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setTypePraticien(typePraticien);
        setSpecialiteList(specialiteList);
        setCoeffNotoriete(coeffNotoriete);
    }
    
}
