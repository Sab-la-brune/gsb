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
 * @author camilletilmont
 */
public class CRapportVisite {
    
    //Attributs Rapport de Visite
    
    protected int idRapportVisite;
    protected GregorianCalendar dateRapport;
    protected String bilanRapport;
    protected String motifRapport;
    protected CVisiteur visiteurRapport;
    protected CPraticien praticienRapport;
    protected List<CEchantillon> listeEchantillonRapport;
    protected List<CMedicament> listMedicamentPresenteRapport;
    
    
    //Getter et Setter

    public int getIdRapportVisite() {
        return idRapportVisite;
    }

    public final void setIdRapportVisite(int idRapportVisite) {
        this.idRapportVisite = idRapportVisite;
    }

    public GregorianCalendar getDateRapport() {
        return dateRapport;
    }

    public final void setDateRapport(GregorianCalendar dateRapport) {
        this.dateRapport = dateRapport;
    }

    public String getBilanRapport() {
        return bilanRapport;
    }

    public final void setBilanRapport(String bilanRapport) {
        this.bilanRapport = bilanRapport;
    }

    public String getMotifRapport() {
        return motifRapport;
    }

    public final void setMotifRapport(String motifRapport) {
        this.motifRapport = motifRapport;
    }

    public CVisiteur getVisiteurRapport() {
        return visiteurRapport;
    }

    public final void setVisiteurRapport(CVisiteur visiteurRapport) {
        this.visiteurRapport = visiteurRapport;
    }

    public CPraticien getPraticienRapport() {
        return praticienRapport;
    }

    public final void setPraticienRapport(CPraticien praticienRapport) {
        this.praticienRapport = praticienRapport;
    }

    public List<CEchantillon> getListeEchantillonRapport() {
        return listeEchantillonRapport;
    }

    public final void setListeEchantillonRapport(List<CEchantillon> listeEchantillonRapport) {
        this.listeEchantillonRapport = listeEchantillonRapport;
    }

    public List<CMedicament> getListMedicamentPresenteRapport() {
        return listMedicamentPresenteRapport;
    }

    public final void setListMedicamentPresenteRapport(List<CMedicament> listMedicamentPresenteRapport) {
        this.listMedicamentPresenteRapport = listMedicamentPresenteRapport;
    }
    
    // Constructeur Custom
    
    public CRapportVisite(int idRap, GregorianCalendar dateRap,String bilanRap,
            String motifRap,CVisiteur visiteurRap,CPraticien praticienRap,
            List<CEchantillon> listEchantillonRap, List<CMedicament> listMedicamentPresRap ){
        
        setIdRapportVisite(idRap);
        setDateRapport(dateRap); 
        setBilanRapport(bilanRap);
        setMotifRapport(motifRap);
        setVisiteurRapport(visiteurRap);
        setPraticienRapport(praticienRap);
        setListeEchantillonRapport(listEchantillonRap);
        setListMedicamentPresenteRapport(listMedicamentPresRap);
        
    
    
    }
    
    
}
