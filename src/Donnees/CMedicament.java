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
public class CMedicament {
    
    // attributs Medicament
    
    protected int depotLegal; 
    protected String nomCommercial;
    protected String composition;
    protected String effets;
    protected String contreIndic;
    protected float prixEchantillon;
    protected CFamille famille;
    protected List<CValeurComposant> listValeurComposants;
    protected List<CFormeMedicament> listFormeMedicament;
    protected List<CMedicament> listMedicamentsInteraction;
    protected List<CPosologie> listPosologie;


    // Getter et Setter

    public int getDepotLegal() {
        return depotLegal;
    }

    public final void setDepotLegal(int depotLegal) {
        this.depotLegal = depotLegal;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public final void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public String getComposition() {
        return composition;
    }

    public final void setComposition(String composition) {
        this.composition = composition;
    }

    public String getEffets() {
        return effets;
    }

    public final void setEffets(String effets) {
        this.effets = effets;
    }

    public String getContreIndic() {
        return contreIndic;
    }

    public final void setContreIndic(String contreIndic) {
        this.contreIndic = contreIndic;
    }

    public float getPrixEchantillon() {
        return prixEchantillon;
    }

    public final void setPrixEchantillon(float prixEchantillon) {
        this.prixEchantillon = prixEchantillon;
    }

    public CFamille getFamille() {
        return famille;
    }

    public final void setFamille(CFamille famille) {
        this.famille = famille;
    }

    public List<CValeurComposant> getListValeurComposants() {
        return listValeurComposants;
    }

    public final void setListValeurComposants(List<CValeurComposant> listValeurComposants) {
        this.listValeurComposants = listValeurComposants;
    }

    public List<CFormeMedicament> getListFormeMedicament() {
        return listFormeMedicament;
    }

    public final void setListFormeMedicament(List<CFormeMedicament> listFormeMedicament) {
        this.listFormeMedicament = listFormeMedicament;
    }
    
    
    public List<CMedicament> getListMedicamentsInteraction() {
        return listMedicamentsInteraction;
    }

    public final void setListMedicamentsInteraction(List<CMedicament> medicamentsInteraction) {
        this.listMedicamentsInteraction = medicamentsInteraction;
    }
    
    
     public List<CPosologie> getListPosologie() {
        return listPosologie;
    }

    public final void setListPosologie(List<CPosologie> listPosologie) {
        this.listPosologie = listPosologie;
    }
    
    // Constructeur Custom
    
    public CMedicament(int id, String nom, String compo, String effet, String contreInd,
    float prixEch, CFamille fami, List<CValeurComposant> listValeurComp, 
    List<CFormeMedicament> listFormeMed,List<CMedicament> listMedInteract, List<CPosologie> listPoso){
        setDepotLegal(id);
        setNomCommercial(nom);
        setComposition(compo);
        setEffets(effet);
        setContreIndic(contreInd);
        setPrixEchantillon(prixEch);
        setFamille(fami);
        setListValeurComposants(listValeurComp);
        setListFormeMedicament(listFormeMed);
        setListMedicamentsInteraction(listMedInteract);
        setListPosologie(listPoso);
        
    
    }
    
}
