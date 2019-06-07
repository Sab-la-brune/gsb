/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CRegion;
import Donnees.CRole;
import Donnees.CVisiteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Sabrina Cos
 */
public class CTableTravailler {
    
    //attributs

    protected ArrayList<CRole> listetravailler;
    protected CBDD bdd;
    
    // Getters et Setters

    public ArrayList<CRole> getListetravailler() {
        return listetravailler;
    }

    public void setListetravailler(ArrayList<CRole> listetravailler) {
        this.listetravailler = listetravailler;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableTravailler() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    
    //Méthodes qui appelle et modifie la table travailler
    
    //inserer travailler
    public int insererTravailler(CRole role, CRegion region, CVisiteur visiteur) {
        String req = "INSERT INTO `travailler` (`JJMMAA_EMBDATE`,`REG_CODE_REGION`,"
                + "`VIS_MATRICULE_VISITEUR`,`TRA_ROLE_TRAVAILLER`)"
                + "VALUES ('" + new Date(visiteur.getDateEmbauche().getTimeInMillis()) + "',"
                + "'" + region.getIdRegion() + "','" + visiteur.getMatricule() + "', "
                + "'" + role.getNomRole() + "') ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TRAVAILLER KO");
        }
        return res;

    }

    //
    CRole convertir_RS_Travailler(ResultSet rs) {
        try {
            GregorianCalendar dateEmbauche =  new GregorianCalendar();
            dateEmbauche.setTime(Date.valueOf(rs.getString("JJMMAA_EMBDATE")));
            int idRegion = rs.getInt("REG_CODE_REGION");
            String nomRole = rs.getString("TRA_ROLE_TRAVAILLER");
            

            return new CRole(nomRole, dateEmbauche, idRegion);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTravailler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de travailler
    public ArrayList<CRole> lireTravailler() {
        if (bdd.connecter() == true) {
            ArrayList<CRole> listDeTravailler = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `travailler`;");
            try {
            while (rs.next())  {
            CRole role = convertir_RS_Travailler(rs);
            listDeTravailler.add(role);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeTravailler;
        } else {
            System.out.println("Connexion manipulationbdd TRAVAILLER KO");
        }
        return null;
    }

    //lire visiteur
    public ArrayList<CRole> lireUnTravail(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CRole> listTravailler = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `travailler` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CRole role = convertir_RS_Travailler(rs);
            listTravailler.add(role);
            
            }  
            if(listTravailler.isEmpty()){
                System.out.println("TRAVAILLER not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listTravailler;
        } else {
            System.out.println("Connexion manipulationbdd TRAVAILLER KO");
        }
        return null;
    }
    
    public void printTravailler(ArrayList<CRole> list){
        list.forEach((list1)->{
            System.out.println("nomRole:"+list1.getNomRole());
        });
    }
    
    // supprimer travailler
    public int supprimerTravailler(String nomRole) {
        String req = "DELETE FROM `travailler` WHERE TRA_ROLE_TRAVAILLER = '" + nomRole + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TRAVAILLER KO");
        }
        return res;

    }

    //mofifier travailler
    public int modifierTravailler(CRole role, CRegion region, CVisiteur visiteur) {
        String req = "UPDATE travailler SET "
                + "VIS_DATEEMBAUCHE_VISITEUR = '" + new Date(visiteur.getDateEmbauche().getTimeInMillis()) + "', "
                + "REG_CODE_REGION = '" + region.getIdRegion() + "', "
                + "VIS_MATRICULE_VISITEUR = '" + visiteur.getMatricule() + "', "
                + "TRA_ROLE_TRAVAILLER = '" + role.getNomRole() + "' "
                + "WHERE TRA_ROLE_TRAVAILLER = '" + role.getNomRole() + "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TRAVAILLER KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableTravailler tableTravailler = new CTableTravailler();
        CRole role = new CRole("visiteur", new GregorianCalendar(), 5);
        CRegion region = new CRegion (9, "truc");
        CVisiteur visiteur = new CVisiteur("vw66", "Leclerc", "Sabrina", new GregorianCalendar(), "infographiste", new CAdresse("28", "rue de la mer", "22130", "Frehel"), 22);
        //tableTravailler.printTravailler(tableTravailler.lireUnTravail("VIS_PRENOM_VISITEUR","Sabrina")); 
        tableTravailler.insererTravailler(role, region, visiteur);
        //tableTravailler.modifierTravailler(role, region, visiteur);
        //tableTravailler.supprimerTravailler("vw77");

    }

}
