/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CSpecialite;
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
public class CTableSpecialite {
    
    //attributs

    protected ArrayList<CSpecialite> listespecialites;
    protected CBDD bdd;
    
    // Getters et Setters

    public ArrayList<CSpecialite> getListespecialites() {
        return listespecialites;
    }

    public void setListespecialites(ArrayList<CSpecialite> listespecialites) {
        this.listespecialites = listespecialites;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableSpecialite() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    
    //Méthodes qui appelle et modifie la table specialite
    
    //inserer specialite
    public int insererSpecialite(CSpecialite specialite) {
        String req = "INSERT INTO `specialite` (`SPE_CODE_SPECIALITE`,`SPE_LIBELLE_SPECIALITE`)"
                + "VALUES ('" + specialite.getIdSpecialite() + "', '" + specialite.getLibelle() + "') ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd SPECIALITE KO");
        }
        return res;

    }
    
    
    //
    CSpecialite convertir_RS_Specialite(ResultSet rs) {
        try {
            int idSpecialite = rs.getInt("SPE_CODE_SPECIALITE");
            String libelle = rs.getString("SPE_LIBELLE_SPECIALITE");
            

            return new CSpecialite(idSpecialite, libelle);
        } catch (SQLException ex) {
            Logger.getLogger(CTableSpecialite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de specialite
    public ArrayList<CSpecialite> lireSpecialite() {
        if (bdd.connecter() == true) {
            ArrayList<CSpecialite> listDeSpecialites = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `specialite`;");
            try {
            while (rs.next())  {
            CSpecialite specialite = convertir_RS_Specialite(rs);
            listDeSpecialites.add(specialite);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeSpecialites;
        } else {
            System.out.println("Connexion manipulationbdd SPECIALITE KO");
        }
        return null;
    }

    //lire specialite
    public ArrayList<CSpecialite> lireUnSpecialite(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CSpecialite> listSpecialite = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `specialite` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CSpecialite specialite = convertir_RS_Specialite(rs);
            listSpecialite.add(specialite);
            
            }  
            if(listSpecialite.isEmpty()){
                System.out.println("SPECIALITE not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listSpecialite;
        } else {
            System.out.println("Connexion manipulationbdd SPECIALITE KO");
        }
        return null;
    }
    
    public void printSpecialite(ArrayList<CSpecialite> list){
        list.forEach((list1)->{
            System.out.println("idSpecialite:"+list1.getIdSpecialite());
        });
    }
    
    // supprimer specialite
    public int supprimerSpecialite(String libelle) {
        String req = "DELETE FROM `specialite` WHERE SPE_LIBELLE_SPECIALITE = '" + libelle + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd SPECIALITE KO");
        }
        return res;

    }    
    
//mofifier specialite
    public int modifierSpecialite(CSpecialite specialite) {
        String req = "UPDATE specialite SET "
                + "SPE_CODE_SPECIALITE = '" + specialite.getIdSpecialite() + "', "
                + "SPE_LIBELLE_SPECIALITE = '" + specialite.getLibelle() + "' "
                + "WHERE SPE_CODE_SPECIALITE = '" + specialite.getIdSpecialite() + "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd SPECIALITE KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableSpecialite tableSpecialite = new CTableSpecialite();
        CSpecialite specialite = new CSpecialite(10, "Ortho");
        tableSpecialite.insererSpecialite(specialite);
        //tableSpecialite.supprimerSpecialite("Ortho");

        tableSpecialite.modifierSpecialite(specialite);
        
        //tableSpecialite.printSpecialite(tableSpecialite.lireUnSpecialite("SPE_LIBELLE_SPECIALITE","Bio"));         
        

    }

}
