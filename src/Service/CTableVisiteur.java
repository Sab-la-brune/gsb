/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CSecteur;
import Donnees.CVisiteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CTableVisiteur {

    protected ArrayList<CVisiteur> listevisiteurs;
    protected CBDD bdd;

    public ArrayList<CVisiteur> getListevisiteurs() {
        return listevisiteurs;
    }

    public void setListevisiteurs(ArrayList<CVisiteur> listevisiteurs) {
        this.listevisiteurs = listevisiteurs;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableVisiteur() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }

    //créer la table
    /*public int creerTable() {
        String req = "CREATE TABLE if NOT EXISTS `bpgobretagne`.`tableclient` "
                + "( `matricule` VARCHAR(11) NOT NULL ,`nom` VARCHAR(20) NOT NULL , `prenom` VARCHAR(20) NOT NULL '0' , PRIMARY KEY (`matricule`))"
                + " ENGINE = InnoDB;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }*/
    
    
    //inserer visteur
    public int insererVisiteur(CVisiteur visiteur, CSecteur secteur) {
        String req = "INSERT INTO `visiteur` (`VIS_MATRICULE_VISITEUR`,`VIS_NOM_VISITEUR`, `VIS_PRENOM_VISITEUR`,`VIS_ADRESSE_VISITEUR`,"
                + "`VIS_CP_VISITEUR`, `VIS_VILLE_VISITEUR`,`VIS_DATEEMBAUCHE_VISITEUR`,"
                + "`secteur_sec_code_secteur`, `DEP_CODE_DEPARTEMENT`) VALUES ('" + visiteur.getMatricule() + "', '" + visiteur.getNom() + "','" + visiteur.getPrenom() + "', "
                + "'" + visiteur.getAdresse().getNumero() + "," + visiteur.getAdresse().getRue() + "','" + visiteur.getAdresse().getCodePostal() + "', '" + visiteur.getAdresse().getVille() + "',"
                + "'" + new Date(visiteur.getDateEmbauche().getTimeInMillis()) + "',"
                + "'" + secteur.getIdSecteur() + "',"
                + "'" + visiteur.getDepartement() + "') ;";
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

    //
    CVisiteur convertir_RS_Visiteur(ResultSet rs) {
        try {
            String matricule = rs.getString("VIS_MATRICULE_VISITEUR");
            String nom = rs.getString("VIS_NOM_VISITEUR");
            String prenom = rs.getString("VIS_PRENOM_VISITEUR");
            
            GregorianCalendar dateEmbauche =  new GregorianCalendar();
            dateEmbauche.setTime(Date.valueOf(rs.getString("VIS_DATEEMBAUCHE_VISITEUR")));
            String adress = rs.getString("VIS_ADRESSE_VISITEUR");
            int ind = adress.indexOf(",");
            String num = adress.substring(0,ind);
            String rue = adress.substring(ind +1);
            String codepostal = rs.getString("VIS_CP_VISITEUR");
            String ville = rs.getString("VIS_VILLE_VISITEUR");
            int departement = rs.getInt("DEP_CODE_DEPARTEMENT");
            

            return new CVisiteur(matricule, nom, prenom, dateEmbauche, "",new CAdresse(num, rue, codepostal, ville), departement);
        } catch (SQLException ex) {
            Logger.getLogger(CTableVisiteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de visiteur
    public ArrayList<CVisiteur> lireVisiteur() {
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listDeVisiteurs = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `visiteur`;");
            try {
            while (rs.next())  {
            CVisiteur visiteur = convertir_RS_Visiteur(rs);
            listDeVisiteurs.add(visiteur);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeVisiteurs;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire visiteur
    public ArrayList<CVisiteur> lireUnVisiteur(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> listVisiteur = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `visiteur` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CVisiteur visiteur = convertir_RS_Visiteur(rs);
            listVisiteur.add(visiteur);
            
            }  
            if(listVisiteur.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listVisiteur;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printVisiteur(ArrayList<CVisiteur> list){
        list.forEach((list1)->{
            System.out.println("nom:"+list1.getNom());
        });
    }
    
    // supprimer visiteur
    public int supprimerVisiteur(String matricule) {
        String req = "DELETE FROM `visiteur` WHERE VIS_MATRICULE_VISITEUR = '" + matricule + "';";
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

    //mofifier visiteur
    public int modifierVisiteur(CVisiteur visiteur, CSecteur secteur) {
        String req = "UPDATE visiteur SET "
                + "VIS_MATRICULE_VISITEUR = '" + visiteur.getMatricule() + "', "
                + "VIS_NOM_VISITEUR = '" + visiteur.getNom() + "', "
                + "VIS_PRENOM_VISITEUR = '" + visiteur.getPrenom() + "', "
                + "VIS_ADRESSE_VISITEUR = '" + visiteur.getAdresse() + "', "
                + "VIS_CP_VISITEUR = '" + visiteur.getAdresse().getCodePostal() + "', "
                + "VIS_VILLE_VISITEUR = '" + visiteur.getAdresse().getVille() + "', "
                + "VIS_DATEEMBAUCHE_VISITEUR = '" + new Date(visiteur.getDateEmbauche().getTimeInMillis()) + "', "
                + "secteur_sec_code_secteur = '" + secteur.getIdSecteur() + "', "
                + "DEP_CODE_DEPARTEMENT = '" + visiteur.getDepartement() + "' "
                + "WHERE VIS_MATRICULE_VISITEUR = '" + visiteur.getMatricule() + "'; ";
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
        CTableVisiteur tableVisiteur = new CTableVisiteur();
        CSecteur secteur = new CSecteur(1, "EST");
        CVisiteur visiteur = new CVisiteur("vw66", "Leclerc", "Sabrina", new GregorianCalendar(), "infographiste", new CAdresse("28", "rue de la mer", "22130", "Frehel"), 22);
        tableVisiteur.printVisiteur(tableVisiteur.lireUnVisiteur("VIS_PRENOM_VISITEUR","Sabrina")); 
        //tableVisiteur.insererVisiteur(visiteur, secteur);
        //tableVisiteur.modifierVisiteur(visiteur, secteur);
        //tableVisiteur.supprimerVisiteur("vw77");

    }

}
