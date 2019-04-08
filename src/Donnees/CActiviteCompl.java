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
public class CActiviteCompl {
    
    //attributs activiteCompl
    
    protected int idACtCompl;
    protected GregorianCalendar date;
    protected String lieu;
    protected String theme;
    protected List<CVisiteur> visiteurList;
    protected List<CInvitation> invitationList;
    protected String etat;
    
    
    
    //Getters et setters

    public int getIdACtCompl() {
        return idACtCompl;
    }

    public final void setIdACtCompl(int idACtCompl) {
        this.idACtCompl = idACtCompl;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public final void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public final void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTheme() {
        return theme;
    }

    public final void setTheme(String theme) {
        this.theme = theme;
    }

    public List<CVisiteur> getVisiteurList() {
        return visiteurList;
    }

    public final void setVisiteurList(List<CVisiteur> visiteurList) {
        this.visiteurList = visiteurList;
    }

    public List<CInvitation> getInvitationList() {
        return invitationList;
    }

    public final void setInvitationList(List<CInvitation> invitationList) {
        this.invitationList = invitationList;
    }

    public String getEtat() {
        return etat;
    }

    public final void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    //Constructeur
    
    public CActiviteCompl(int idACtCompl, GregorianCalendar date, String lieu, String theme,
            List<CVisiteur> visiteurList, List<CInvitation> invitationList, String etat){
                setIdACtCompl(idACtCompl);
                setDate(date);
                setLieu(lieu);
                setTheme(theme);
                setTheme(theme);
                setVisiteurList(visiteurList);
                setInvitationList(invitationList);
                setEtat(etat);
            
    }
}
