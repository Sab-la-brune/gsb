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
    protected ArrayList<CFicheFrais> listeFicheFrais;
    protected CFicheFrais ficheFrais;
    
    

    //getter et setter
    
    public CVisiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(CVisiteur visiteur) {
        this.visiteur = visiteur;
    }

    public ArrayList<CFicheFrais> getListeFicheFrais() {
        return listeFicheFrais;
    }

    public void setListeFicheFrais(ArrayList<CFicheFrais> listeFicheFrais) {
        this.listeFicheFrais = listeFicheFrais;
    }
    
    
    
    //constructeur
    
    public CMetierFicheFrais(){
        CTableFicheFrais tableFicheFrais = new CTableFicheFrais();
        setListeFicheFrais(tableFicheFrais.lireFicheFrais());
    }
    
    
    
    //methode
    
    public int connexion(String id, String nom) {
        CTableVisiteur tableVisiteur = new CTableVisiteur();
        ArrayList<CVisiteur> visiCo = tableVisiteur.lireUnVisiteur("VIS_MATRICULE_VISITEUR", id);
        if (visiCo.isEmpty()) {

            return 0;
        } else if (!visiCo.get(0).getNom().equals(nom)) {

            return 1;

        } else if (visiCo.get(0).getNom().equals(nom)) {
            setVisiteur(visiCo.get(0));

            return 2;
        } else {

            return 3;

        }

    }
    
        public void deconnexion() {

    }
    
        
        
            public void CreerFiche(){
    }
            
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
    //CVisiteur visiteur = tableVisiteur.lireUnVisiteur(matricule);
    String nomPrenom = visiteur.getNom()+" "+visiteur.getPrenom();
    return nomPrenom;
    }
    
    /*public actionConsulterFiche(){
        

    }
    
    
    public void ModifierFiche(){
    }*/
        
     /**
     * Supprimer une fiche frais grace a son matricule dans le tableau
     * @param matricule
     */
    public void SupprimerFiche(String matricule){
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
