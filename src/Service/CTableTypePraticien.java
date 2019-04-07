/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CTypePraticien;
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
public class CTableTypePraticien {
    
protected ArrayList<CTypePraticien> listetypepraticiens;
    protected CBDD bdd;

    public ArrayList<CTypePraticien> getlistetypepraticiens() {
        return listetypepraticiens;
    }

    public void setlistetypepraticiens(ArrayList<CTypePraticien> listetypepraticiens) {
        this.listetypepraticiens = listetypepraticiens;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableTypePraticien() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    
    //inserer type praticien
    public int insererTypePraticien(CTypePraticien typepraticien) {
        String req = "INSERT INTO `type_praticien` (`TYP_CODE_TYPE_PRATICIEN`,`TYP_LIBELLE_TYPE_PRATICIEN`,`TYP_LIEU_TYPE_PRATICIEN`)"
                + "VALUES ('" + typepraticien.getIdType() + "', '" + typepraticien.getLibelle() + "','" + typepraticien.getLieu() + "') ;";
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
    CTypePraticien convertir_RS_TypePraticien(ResultSet rs) {
        try {
            int idType = rs.getInt("TYP_CODE_TYPE_PRATICIEN");
            String libelle = rs.getString("TYP_LIBELLE_TYPE_PRATICIEN");
            String lieu = rs.getString("TYP_LIEU_TYPE_PRATICIEN");
            

            return new CTypePraticien(idType, libelle, lieu);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTypePraticien.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de type praticien
    public ArrayList<CTypePraticien> lireTypePraticien() {
        if (bdd.connecter() == true) {
            ArrayList<CTypePraticien> listDePraticiens = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_praticien`;");
            try {
            while (rs.next())  {
            CTypePraticien typepraticien = convertir_RS_TypePraticien(rs);
            listDePraticiens.add(typepraticien);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDePraticiens;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire type praticien
    public ArrayList<CTypePraticien> lireUnTypePraticien(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CTypePraticien> listTypePraticien = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_praticien` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CTypePraticien typepraticien = convertir_RS_TypePraticien(rs);
            listTypePraticien.add(typepraticien);
            
            }  
            if(listTypePraticien.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listTypePraticien;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printTypePraticien(ArrayList<CTypePraticien> list){
        list.forEach((list1)->{
            System.out.println("libelle:"+list1.getLibelle());
        });
    }
    
    // supprimer type praticien
    public int supprimerTypePraticien(String libelle) {
        String req = "DELETE FROM `type_praticien` WHERE TYP_LIBELLE_TYPE_PRATICIEN = '" + libelle + "';";
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

    //mofifier type praticien

    public int modifierTypePraticien(CTypePraticien typepraticien) {
        String req = "UPDATE type_praticien SET "
                + "TYP_CODE_TYPE_PRATICIEN = '" + typepraticien.getIdType() + "', "
                + "TYP_LIBELLE_TYPE_PRATICIEN = '" + typepraticien.getLibelle() + "', "
                + "TYP_LIEU_TYPE_PRATICIEN = '" + typepraticien.getLieu() + "' "
                + "WHERE TYP_LIBELLE_TYPE_PRATICIEN = '" + typepraticien.getLibelle() + "'; ";
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
        CTableTypePraticien tabletypepraticien = new CTableTypePraticien();
        CTypePraticien typepraticien = new CTypePraticien(6, "ortho", "hopital");
        tabletypepraticien.insererTypePraticien(typepraticien);
        //tabletypepraticien.supprimerTypePraticien("ortho");

        //tabletypepraticien.modifierTypePraticien(typepraticien);
        
        //tabletypepraticien.printTypePraticien(tabletypepraticien.lireUnTypePraticien("TYP_CODE_TYPE_PRATICIEN","Kiné"));

    }

}

