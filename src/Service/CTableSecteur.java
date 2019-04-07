/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
 * @author admin
 */
public class CTableSecteur {

    protected ArrayList<CSecteur> listesecteurs;
    protected CBDD bdd;

    public ArrayList<CSecteur> getListesecteurs() {
        return listesecteurs;
    }

    public void setListesecteurs(ArrayList<CSecteur> listesecteurs) {
        this.listesecteurs = listesecteurs;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableSecteur() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
    //inserer secteur
    public int insererSecteur(CSecteur secteur) {
        String req = "INSERT INTO `secteur` (`SEC_CODE_SECTEUR`,`SEC_LIBELLE`) VALUES ('" + secteur.getIdSecteur() + "', '" + secteur.getNomSecteur() + "') ;";
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
    CSecteur convertir_RS_Secteur(ResultSet rs) {
        try {
            int idsecteur = rs.getInt("SEC_CODE_SECTEUR");
            String nomSecteur = rs.getString("SEC_LIBELLE");
            

            return new CSecteur(idsecteur, nomSecteur);
        } catch (SQLException ex) {
            Logger.getLogger(CTableSecteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de secteur
    public ArrayList<CSecteur> lireSecteur() {
        if (bdd.connecter() == true) {
            ArrayList<CSecteur> listDeSecteurs = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur`;");
            try {
            while (rs.next())  {
            CSecteur secteur = convertir_RS_Secteur(rs);
            listDeSecteurs.add(secteur);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeSecteurs;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire secteur
    public ArrayList<CSecteur> lireUnSecteur(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CSecteur> listSecteur = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CSecteur secteur = convertir_RS_Secteur(rs);
            listSecteur.add(secteur);
            
            }  
            if(listSecteur.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listSecteur;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printSecteur(ArrayList<CSecteur> list){
        list.forEach((list1)->{
            System.out.println("nomSecteur:"+list1.getNomSecteur());
        });
    }
    
    // supprimer secteur
    public int supprimerSecteur(String nomSecteur) {
        String req = "DELETE FROM `secteur` WHERE SEC_LIBELLE = '" + nomSecteur + "';";
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
    
//mofifier secteur
    public int modifierSecteur(CSecteur secteur) {
        String req = "UPDATE secteur SET "
                + "SEC_CODE_SECTEUR = '" + secteur.getIdSecteur() + "', "
                + "SEC_LIBELLE = '" + secteur.getNomSecteur() + "' "
                + "WHERE SEC_CODE_SECTEUR = '" + secteur.getIdSecteur() + "'; ";
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
        CTableSecteur tableSecteur = new CTableSecteur();
        CSecteur secteur = new CSecteur(8, "Ortho");
        tableSecteur.insererSecteur(secteur);
        //tableSecteur.supprimerSecteur("Cabinet");

        //tableSecteur.modifierSecteur(secteur);
        
        //tableSecteur.printSecteur(tableSecteur.lireUnSecteur("SEC_LIBELLE","Bio"));         
        

    }

}
