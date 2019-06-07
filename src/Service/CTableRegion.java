/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CRegion;
import Donnees.CSecteur;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Sabrina Cos
 */
public class CTableRegion {
    
    //attributs

    protected ArrayList<CRegion> listeregions;
    protected CBDD bdd;
    
    // Getters et Setters

    public ArrayList<CRegion> getListeregions() {
        return listeregions;
    }

    public void setListeregions(ArrayList<CRegion> listeregions) {
        this.listeregions = listeregions;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableRegion() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    
    //Méthodes qui appelle et modifie la table region

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
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return res;
    }*/
    
    
    //inserer region
    public int insererRegion(CRegion region, CSecteur secteur) {
        String req = "INSERT INTO `region` (`REG_CODE_REGION`,`REG_NOM_REGION`,`SEC_CODE_SECTEUR`)"
                + "VALUES ('" + region.getIdRegion() + "', '" + region.getNomRegion() + "','" + secteur.getIdSecteur() + "') ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return res;

    }

    //
    CRegion convertir_RS_Region(ResultSet rs) {
        try {
            int idRegion = rs.getInt("REG_CODE_REGION");
            String nomRegion = rs.getString("REG_NOM_REGION");
            int idSecteur = rs.getInt("SEC_CODE_SECTEUR");
            

            return new CRegion(idRegion, nomRegion);
        } catch (SQLException ex) {
            Logger.getLogger(CTableRegion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de region
    public ArrayList<CRegion> lireRegion() {
        if (bdd.connecter() == true) {
            ArrayList<CRegion> listDeRegions = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `region`;");
            try {
            while (rs.next())  {
            CRegion region = convertir_RS_Region(rs);
            listDeRegions.add(region);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeRegions;
        } else {
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return null;
    }

    //lire region
    public ArrayList<CRegion> lireUneRegion(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CRegion> listRegion = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `region` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CRegion region = convertir_RS_Region(rs);
            listRegion.add(region);
            
            }  
            if(listRegion.isEmpty()){
                System.out.println("REGION not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listRegion;
        } else {
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return null;
    }
    
    public void printRegion(ArrayList<CRegion> list){
        list.forEach((list1)->{
            System.out.println("nomRegion:"+list1.getNomRegion());
        });
    }
    
    // supprimer region
    public int supprimerRegion(String nomRegion) {
        String req = "DELETE FROM `region` WHERE REG_NOM_REGION = '" + nomRegion + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return res;

    }

    //mofifier region

    /**
     *
     * @param region
     * @param secteur
     * @return
     */
    public int modifierRegion(CRegion region, CSecteur secteur) {
        String req = "UPDATE region SET "
                + "REG_CODE_REGION = '" + region.getIdRegion() + "', "
                + "REG_NOM_REGION = '" + region.getNomRegion() + "', "
                + "SEC_CODE_SECTEUR = '" + secteur.getIdSecteur() + "' "
                + "WHERE REG_CODE_REGION = '" + region.getIdRegion() + "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd REGION KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableRegion tableRegion = new CTableRegion();
        CSecteur secteur = new CSecteur(8, "Ortho");
        CRegion region = new CRegion(6, "truc");
        //tableRegion.insererRegion(region, secteur);
        //tableRegion.supprimerRegion("bibi");

        tableRegion.modifierRegion(region, secteur);
        
        //tableRegion.printRegion(tableRegion.lireUneRegion("REG_NOM_REGION","Bio"));

    }

}
