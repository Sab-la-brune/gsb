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
    protected List<CPraticien> praticienList;
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

    public List<CPraticien> getPraticienList() {
        return praticienList;
    }

    public final void setPraticienList(List<CPraticien> praticienList) {
        this.praticienList = praticienList;
    }

    public String getEtat() {
        return etat;
    }

    public final void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    //Constructeur
    
    public CActiviteCompl(int idACtCompl, GregorianCalendar date, String lieu, String theme,
            List<CVisiteur> visiteurList, List<CPraticien> praticienList, String etat){
                setIdACtCompl(idACtCompl);
                setDate(date);
                setLieu(lieu);
                setTheme(theme);
                setTheme(theme);
                setVisiteurList(visiteurList);
                setPraticienList(praticienList);
                setEtat(etat);
            
    }
}
