/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.List;

/**
 *
 * @author camilletilmont
 */
public class CPosologie {
    
    //attributs Posologie
    
    protected List<CTypeIndividu> listTypeIndividu;
    protected List<CDosageperiodique> listDosagePeriodique;
    protected int nbrJourPosologie;
    
    
    // Getters et Setters

    public List<CTypeIndividu> getListTypeIndividu() {
        return listTypeIndividu;
    }

    public final void setListTypeIndividu(List<CTypeIndividu> listTypeIndividu) {
        this.listTypeIndividu = listTypeIndividu;
    }

    public List<CDosageperiodique> getListDosagePeriodique() {
        return listDosagePeriodique;
    }

    public final void setListDosagePeriodique(List<CDosageperiodique> listDosagePeriodique) {
        this.listDosagePeriodique = listDosagePeriodique;
    }

    public int getNbrJourPosologie() {
        return nbrJourPosologie;
    }

    public final void setNbrJourPosologie(int nbrJour) {
        this.nbrJourPosologie = nbrJour;
    }
    
    
    //Constucteur Custom
    
    public CPosologie(List<CTypeIndividu> listTypeInd, List<CDosageperiodique> listDosPer, int nbrJourPoso){
        setListTypeIndividu(listTypeInd);
        setListDosagePeriodique(listDosPer);
        setNbrJourPosologie(nbrJourPoso);
    
    }
    
    
}
