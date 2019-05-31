/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.util.ArrayList;
import Donnees.CFicheFrais;
import Donnees.CVisiteur;

/**
 *
 * @Sabrina Cos
 */
public class CMetierFicheFrais {
    
    
    
    //attributs
    
    protected CVisiteur visiteur;
    protected ArrayList<CFicheFrais listeFicheFrais;
    
    

    //getter et setter
    
    public CVisiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(CVisiteur visiteur) {
        this.visiteur = visiteur;
    }

    public <any> getListeFicheFrais() {
        return listeFicheFrais;
    }

    public void setListeFicheFrais(<any> listeFicheFrais) {
        this.listeFicheFrais = listeFicheFrais;
    }
    
    
    
    //constructeur
    
    public CMetierFicheFrais(){
        CTableCFicheFrais tablefichefrais = new CTableFicheFrais;
        setListeFicheFrais(tableFicheFrais.lireFicheFrais);
    }
    
    
    
    //methode
    
    /**
     * recupere la liste des fiches de frais
     * @return la liste des fiches de frais
     */
    public ArrayList<CFicheFrais> actionRecupererListeFiche(){
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        CTableFicheFrais tablefichefrais = new CTableFicheFrais();
        tablefichefrais.setBdd(bdd);
        ArrayList<CFicheFrais> liste = tablefichefrais.lireFicheFrais();
        return liste;
    }
    
     /**
     * recupere le nom et le prenom du visiteur grace a son matricule
     * @param matricule
     * @return une chaine de caractere sous la fome "nom prenom"
     */
    public String RecupererNomPrenomVisiteur(String matricule){
    CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    CTableVisiteur tableVisiteur = new CTableVisiteur();
    tableVisiteur.setBdd(bdd);
    CVisiteur visiteur = tableVisiteur.lireVisiteur(matricule);
    String nomPrenom = visiteur.getNom()+" "+visiteur.getPrenom();
    return nomPrenom;
    }
    
    /*public actionConsulterFiche(){
        

    }
    
    public actionCreerFiche(){
    }
    
    public actionModifierFiche(){
    }*/
        
     /**
     * Supprimer une fiche frais grace a son matricule dans le tableau
     * @param matricule
     */
    public void actionSupprimerFiche(String matricule){
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        CTableFicheFrais tablefichefrais = new CTableFicheFrais();
        tablefichefrais.setBdd(bdd);
        tablefichefrais.supprimerFicheFrais(matricule);
    }
    

    public static void main(String[] args){
        CMetierFicheFrais metier = new CMetierFicheFrais();
        System.out.println(metier.RecupererNomPrenomVisiteur("2"));
    }
    

    
    
    
}
