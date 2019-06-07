/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFicheFrais;
import Donnees.CFrais;
import Donnees.CTypeFrais;
import Donnees.CVisiteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Sabrina Cos
 */
public class CTableFicheFrais {

    //attributs
    
    protected ArrayList<CFicheFrais> listefichefrais;
    protected CBDD bdd;
    
    // Getters et Setters
    
    public ArrayList<CFicheFrais> getlistefichefrais() {
        return listefichefrais;
    }

    public void setlistefichefrais(ArrayList<CFicheFrais> listefichefrais) {
        this.listefichefrais = listefichefrais;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableFicheFrais() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }

    
    //Méthodes qui appelle et modifie la table fiche frais
    
    //inserer d'une fichefrais
    public int insererFicheFrais(CFicheFrais fichefrais, CVisiteur visiteur) {
        String req = "INSERT INTO `fiche_frais` (`FF_MOIS_FICHE_FRAIS`,`FF_NBHORSCLASSIF`, `FF_MONTANTHORSCLASSIF`, `VIS_MATRICULE_VISITEUR`) "
                + "VALUES ('" + fichefrais.getMois() + "', '" + fichefrais.getNbrHorsClassif() + "','" + fichefrais.getMontantHorsClassif() + "', "
                + "'" + visiteur.getMatricule() + "') ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd FICHE FRAIS KO");
        }
        return res;

    }

    //Convertie les données récupérées de la table
    CFicheFrais convertir_RS_FicheFrais(ResultSet rs) {
        try {
            int mois = rs.getInt("FF_MOIS_FICHE_FRAIS");
            int nbrHorsClassif = rs.getInt("FF_NBHORSCLASSIF");
            Float montantHorsClassif = rs.getFloat("FF_MONTANTHORSCLASSIF");
            String matricule = rs.getString("VIS_MATRICULE_VISITEUR");
            
            CTableInclure TypeFrais =  new CTableInclure();
            ArrayList<CFrais> liste = TypeFrais.lireUnInclure(rs.getString("VIS_MATRICULE_VISITEUR"), rs.getString("FF_MOIS_FICHE_FRAIS"));       
            

            return new CFicheFrais(mois, nbrHorsClassif, montantHorsClassif, matricule, liste);
        } catch (SQLException ex) {
            Logger.getLogger(CTableFicheFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de fiche frais
    public ArrayList<CFicheFrais> lireFicheFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> listDeFicheFrais = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais`;");
            try {
            while (rs.next())  {
            CFicheFrais fichefrais = convertir_RS_FicheFrais(rs);
            listDeFicheFrais.add(fichefrais);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeFicheFrais;
        } else {
            System.out.println("Connexion manipulationbdd FICHE FRAIS KO");
        }
        return null;
    }

    //lire fiche frais
    public ArrayList<CFicheFrais> lireUneFicheFrais(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> listFicheFrais = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `visiteur` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CFicheFrais fichefrais = convertir_RS_FicheFrais(rs);
            listFicheFrais.add(fichefrais);
            
            }  
            if(listFicheFrais.isEmpty()){
                System.out.println("FICHE FRAIS not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listFicheFrais;
        } else {
            System.out.println("Connexion manipulationbdd FICHE FRAIS KO");
        }
        return null;
    }
    
    public void printVisiteur(ArrayList<CFicheFrais> liste){
        liste.forEach((list1)->{
            System.out.println("montant:"+list1.getMontantHorsClassif());
        });
    }
    
    // supprimer fiche frais
    public int supprimerFicheFrais(String matricule) {
        String req = "DELETE FROM `fiche_frais` WHERE VIS_MATRICULE_VISITEUR = '" + matricule + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd FICHE FRAIS KO");
        }
        return res;

    }

    //mofifier visiteur
    public int modifierFicheFrais(CFicheFrais fichefrais) {
        String req = "UPDATE fiche_frais SET "
                + "FF_MOIS_FICHE_FRAIS = '" + fichefrais.getMois() + "', "
                + "FF_NBHORSCLASSIF = '" + fichefrais.getNbrHorsClassif() + "', "
                + "FF_MONTANTHORSCLASSIF = '" + fichefrais.getMontantHorsClassif() + "', "
                + "VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "' "
                + "WHERE VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd FICHE FRAIS KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableFicheFrais tablefichefrais = new CTableFicheFrais();
        CTypeFrais typefrais = new CTypeFrais("22", "Resto", 3.5f);
        CFrais frais = new CFrais(2, 1.99f, typefrais);
        ArrayList<CFrais> liste = new ArrayList();
        CFicheFrais fichefrais = new CFicheFrais(2, 56, 3.5f, "vw66", liste);
        //tablefichefrais.printFicheFrais(tablefichefrais.lireUnFicheFrais("VIS_MATRICULE_VISITEUR","vw66")); 
        //tablefichefrais.insererFicheFrais(fichefrais);
        tablefichefrais.modifierFicheFrais(fichefrais);
        //tablefichefrais.supprimerFicheFrais("vw77");

    }

}
