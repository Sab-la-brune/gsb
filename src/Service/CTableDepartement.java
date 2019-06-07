/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CDepartement;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Sabrina Cos
 */
public class CTableDepartement {
    
    //attributs

    protected ArrayList<CDepartement> listedepartements;
    protected CBDD bdd;
    
    // Getters et Setters

    public ArrayList<CDepartement> getListedepartements() {
        return listedepartements;
    }

    public void setListedepartements(ArrayList<CDepartement> listedepartements) {
        this.listedepartements = listedepartements;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableDepartement() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    
    //Méthodes qui appelle et modifie la table departement
    
    //inserer departement
    public int insererDepartement(CDepartement departement) {
        String req = "INSERT INTO `departement` (`DEP_CODE_DEPARTEMENT`,`DEP_NOM_DEPARTEMENT`, `DEP_CHEFVENTE_DEPARTEMENT`)"
                + "VALUES ('" + departement.getIdDepartement() + "', '" + departement.getNomDep() + "','" + (departement.isChefVente() ? 1 : 0) + "') ;";

        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd DEPARTEMENT KO");
        }
        return res;

    }

    //
    CDepartement convertir_RS_Departement(ResultSet rs) {
        try {
            int idDepartement = rs.getInt("DEP_CODE_DEPARTEMENT");
            String nomDep = rs.getString("DEP_NOM_DEPARTEMENT");
            boolean chefVente = rs.getBoolean("DEP_CHEFVENTE_DEPARTEMENT");
            
            

            return new CDepartement(idDepartement, nomDep, chefVente);
        } catch (SQLException ex) {
            Logger.getLogger(CTableDepartement.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de departement
    public ArrayList<CDepartement> lireDepartement() {
        if (bdd.connecter() == true) {
            ArrayList<CDepartement> listDeDepartements = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `departement`;");
            try {
            while (rs.next())  {
            CDepartement departement = convertir_RS_Departement(rs);
            listDeDepartements.add(departement);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeDepartements;
        } else {
            System.out.println("Connexion manipulationbdd DEPARTEMENT KO");
        }
        return null;
    }

    //lire departement
    public ArrayList<CDepartement> lireUnDepartement(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CDepartement> listDepartement = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `departement` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CDepartement departement = convertir_RS_Departement(rs);
            listDepartement.add(departement);
            
            }  
            if(listDepartement.isEmpty()){
                System.out.println("DEPARTEMENT not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDepartement;
        } else {
            System.out.println("Connexion manipulationbdd DEPARTEMENT KO");
        }
        return null;
    }
    
    public void printDepartement(ArrayList<CDepartement> list){
        list.forEach((list1)->{
            System.out.println("nomDep:"+list1.getNomDep());
        });
    }
    
    // supprimer departement
    public int supprimerDepartement(String nomDep) {
        String req = "DELETE FROM `departement` WHERE DEP_NOM_DEPARTEMENT = '" + nomDep + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd DEPARTEMENT KO");
        }
        return res;

    }

    //mofifier departement
    public int modifierDepartement(CDepartement departement) {
        String req = "UPDATE departement SET "
                + "DEP_CODE_DEPARTEMENT = '" + departement.getIdDepartement() + "', "
                + "DEP_NOM_DEPARTEMENT = '" + departement.getNomDep() + "', "
                + "DEP_CHEFVENTE_DEPARTEMENT = '" + (departement.isChefVente() ? 1 : 0) + "' "
                + "WHERE DEP_CODE_DEPARTEMENT = '" + departement.getIdDepartement()+ "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd DEPARTEMENT KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableDepartement tableDepartement = new CTableDepartement();
        CDepartement departement = new CDepartement(56, "Manche", true );
        tableDepartement.insererDepartement(departement);
        //tableDepartement.supprimerDepartement("Ille-et-Vilaine");

        //tableDepartement.modifierDepartement(departement);
        
        //tableDepartement.printDepartement(tableDepartement.lireUnDepartement("DEP_NOM_DEPARTEMENT","Ille-et-Vilaine")); 


    }

}
