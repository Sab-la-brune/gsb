/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CFicheFrais;
import Donnees.CFrais;
import Donnees.CTypeFrais;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CTableInclure {
    
    protected ArrayList<CFrais> listeinclure;
    protected CBDD bdd;

    public ArrayList<CFrais> getlisteinclure() {
        return listeinclure;
    }

    public void setlisteinclure(ArrayList<CFrais> listeinclure) {
        this.listeinclure = listeinclure;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableInclure() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
   
    //inserer inclure
    public int insererInclure(CFrais frais, CFicheFrais fichefrais) {
        String req = "INSERT INTO `inclure` (`TF_CODE_TYPE_FRAIS`,`FF_MOIS_FICHE_FRAIS`,`VIS_MATRICULE_VISITEUR`,`INC_QTE_INCLURE`,`INC_MONTANT_INCLURE`)"
                + "VALUES ('" + frais.getTypeFrais().getCode()+ "',"
                + "'" + fichefrais.getMois() + "',"
                + "'" + fichefrais.getMatriculeVisiteur() + "', "
                + "'" + frais.getQuantiteFrais() + "', "
                + "'" + frais.getMontantFrais() + "') ;";
        int res = -1;
        System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;

    }

    //methode afin de convertir les données récupérées de la table
    CFrais convertir_RS_Frais(ResultSet rs) {
        try {
            String code = rs.getString("TF_CODE_TYPE_FRAIS");
            
            CTableTypeFrais lireTypeFrais =  new CTableTypeFrais();
            ArrayList<CTypeFrais> tab = lireTypeFrais.lireUnTypeFrais("TF_CODE_TYPE_FRAIS",code);
            CTypeFrais typefrais = tab.get(0);
                    
            int quantiteFrais = rs.getInt("INC_QTE_INCLURE");
            float montantFrais = rs.getFloat("INC_MONTANT_INCLURE");
            

            return new CFrais(quantiteFrais, montantFrais, typefrais);
        } catch (SQLException ex) {
            Logger.getLogger(CTableInclure.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de inclure
    public ArrayList<CFrais> lireInclure() {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> listDeInclure = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inclure`;");
            try {
            while (rs.next())  {
            CFrais frais = convertir_RS_Frais(rs);
            listDeInclure.add(frais);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeInclure;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire inclure
    public ArrayList<CFrais> lireUnInclure(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> listInclure = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inclure` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CFrais frais = convertir_RS_Frais(rs);
            listInclure.add(frais);
            
            }  
            if(listInclure.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listInclure;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printInclure(ArrayList<CFrais> list){
        list.forEach((list1)->{
            System.out.println("code:"+list1.getTypeFrais());
        });
    }
    
    // supprimer inclure
    public int supprimerInclure(String code, String mois, String matricule) {
        String req = "DELETE FROM `inclure` WHERE TF_CODE_TYPE_FRAIS = '" + code + "' AND FF_MOIS_FICHE_FRAIS = '"+ mois +"' AND VIS_MATRICULE_VISITEUR = '" + matricule + "' ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;

    }

    //mofifier inclure

    public int modifierInclure(CFrais frais, CFicheFrais fichefrais) {
        String req = "UPDATE inclure SET "
                + "TF_CODE_TYPE_FRAIS = '" + frais.getTypeFrais().getCode() + "', "
                + "FF_MOIS_FICHE_FRAIS = '" + fichefrais.getMois() + "', "
                + "VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "', "
                + "INC_QTE_INCLURE = '" + frais.getQuantiteFrais() +"',"
                + "INC_MONTANT_INCLURE = '" + frais.getMontantFrais()+"'"
                + "WHERE TF_CODE_TYPE_FRAIS = '" + frais.getTypeFrais().getCode() + "' AND VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "' AND FF_MOIS_FICHE_FRAIS = '" + fichefrais.getMois() + "' ; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableInclure tableinclure = new CTableInclure();
        CTypeFrais typefrais = new CTypeFrais("22", "Resto", 3.5f);
        CFrais frais = new CFrais(52, 1.99f, typefrais);
        ArrayList<CFrais> listeinclure = new ArrayList();
        listeinclure.add(frais);
        CFicheFrais fichefrais = new CFicheFrais (1, 9, 70.2f, "vw66", listeinclure);
        //tableinclure.insererInclure(frais, fichefrais);
        //tableinclure.supprimerInclure("22", "3", "vw66");

        tableinclure.modifierInclure(frais, fichefrais);
        
        //tableinclure.printInclure(tableInclure.lireUnInclure("vw66","3"));

    }

}
    
