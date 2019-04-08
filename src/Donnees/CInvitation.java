/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @Sabrina Cos
 */
public class CInvitation {
    protected CPraticien praticien;
    protected String specialisteOn;

    public CPraticien getPraticien() {
        return praticien;
    }

    public final void setPraticien(CPraticien praticien) {
        this.praticien = praticien;
    }

    public String getSpecialisteOn() {
        return specialisteOn;
    }

    public final void setSpecialisteOn(String specialisteOn) {
        this.specialisteOn = specialisteOn;
    }
    
    public CInvitation(CPraticien praticien, String spelisteOn){
        setPraticien(praticien);
        setSpecialisteOn(spelisteOn);
    }
    
}
