/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
 * @Sabrina Cos
 */

public class CTableTypeFrais {
    
    //attributs

    protected ArrayList<CTypeFrais> listefrais;
    protected CBDD bdd;

    // Getters et Setters
    
    public ArrayList<CTypeFrais> getlistefrais() {
        return listefrais;
    }

    public void setlistefrais(ArrayList<CTypeFrais> listefrais) {
        this.listefrais = listefrais;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableTypeFrais() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }

    
    //Méthodes qui appelle et modifie la table type frais
    
    //inserer type frais
    public int insererTypeFrais(CTypeFrais typefrais) {
        String req = "INSERT INTO `type_frais` (`TF_CODE_TYPE_FRAIS`,`TF_LIBELLE_TYPE_FRAIS`, `TF_FORFAIT_TYPE_FRAIS`)"
                + "VALUES ('" + typefrais.getCode() + "', '" + typefrais.getNom() + "','" + typefrais.getForfait() + "') ;";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TYPE FRAIS KO");
        }
        return res;

    }

    //
    CTypeFrais convertir_RS_TypeFrais(ResultSet rs) {
        try {
            String code = rs.getString("TF_CODE_TYPE_FRAIS");
            String nom = rs.getString("TF_LIBELLE_TYPE_FRAIS");
            Float forfait = rs.getFloat("TF_FORFAIT_TYPE_FRAIS");
           
            return new CTypeFrais(code, nom, forfait);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTypeFrais.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de type de frais
    public ArrayList<CTypeFrais> lireTypeFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CTypeFrais> listeDeFrais = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_frais`;");
            try {
            while (rs.next())  {
            CTypeFrais typefrais = convertir_RS_TypeFrais(rs);
            listeDeFrais.add(typefrais);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDeFrais;
        } else {
            System.out.println("Connexion manipulationbdd TYPE FRAIS");
        }
        return null;
    }

    //lire type frais
    public ArrayList<CTypeFrais> lireUnTypeFrais(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CTypeFrais> listTypefrais = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_frais` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CTypeFrais typefrais = convertir_RS_TypeFrais(rs);
            listTypefrais.add(typefrais);
            
            }  
            if(listTypefrais.isEmpty()){
                System.out.println("TYPE FRAIS not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listTypefrais;
        } else {
            System.out.println("Connexion manipulationbdd TYPE FRAIS KO");
        }
        return null;
    }
    
    public void printTypeFrais(ArrayList<CTypeFrais> list){
        list.forEach((list1)->{
            System.out.println("nom:"+list1.getNom());
        });
    }
    
    // supprimer type frais
    public int supprimerTypeFrais(String code) {
        String req = "DELETE FROM `type_frais` WHERE TF_CODE_TYPE_FRAIS = '" + code + "';";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TYPE FRAIS KO");
        }
        return res;

    }

    //mofifier visiteur
    public int modifierTypeFrais(CTypeFrais typefrais) {
        String req = "UPDATE type_frais SET "
                + "TF_CODE_TYPE_FRAIS = '" + typefrais.getCode() + "', "
                + "TF_LIBELLE_TYPE_FRAIS = '" + typefrais.getNom() + "', "
                + "TF_FORFAIT_TYPE_FRAIS = '" + typefrais.getForfait() + "' "
                + "WHERE TF_CODE_TYPE_FRAIS = '" + typefrais.getCode() + "'; ";
        int res = -1;
        //System.out.println(req);
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd TYPE FRAIS KO");
        }
        return res;

    }

    public static void main(String[] args) {
        CTableTypeFrais tabletypefrais = new CTableTypeFrais();
        CTypeFrais typefrais = new CTypeFrais("99", "Pressing", 78.5f);
        
        tabletypefrais.insererTypeFrais(typefrais);
        //tabletypefrais.supprimerTypeFrais("22");
        
        //tabletypefrais.modifierTypeFrais(typefrais);

        
        //tabletypefrais.printtypefrais(tabletypefrais.lireUnTypeFrais("TF_CODE_TYPE_FRAIS","Hotel")); 

    }

}
