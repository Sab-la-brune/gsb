/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.GregorianCalendar;
import java.util.List;
 
/**
 *
 * @author Sabrina Cos
 */
public class CRole {
    
    //attributs Role
    
    protected String nomRole;
    protected GregorianCalendar date;
    protected int idRegion;
    
    
    // Getters et Setters
    
    public String getNomRole() {
        return nomRole;
    }

    public final void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public final void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public final void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    
    
    // Constructeur

    public CRole (String nomRole, GregorianCalendar date, int idRegion){
        setNomRole(nomRole);
        setDate(date);
        setIdRegion(idRegion);

    }
    
    
}
