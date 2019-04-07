/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CAdresse;
import Donnees.CPraticien;
import Donnees.CSpePossede;
import Donnees.CSpecialite;
import Donnees.CTypePraticien;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CTablePosseder {
    

    
    protected ArrayList<CSpePossede> listespepossede;
    protected CBDD bdd;

    public ArrayList<CSpePossede> getlistespepossede() {
        return listespepossede;
    }

    public void setlistespepossede(ArrayList<CSpePossede> listespepossede) {
        this.listespepossede = listespepossede;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTablePosseder() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
   
    //inserer posseder
    public int insererPosseder(CSpePossede spepossede, CPraticien praticien) {
        String req = "INSERT INTO `posseder` (`PRA_NUM_PRATICIEN`,`SPE_CODE_SPECIALITE`,`POS_DIPLOME_POSSEDER`,`POS_COEFPRESCRIPTION_POSSEDER`)"
                + "VALUES ('" + praticien.getIdPraticien()+ "',"
                + "'" + spepossede.getSpecialite().getIdSpecialite() + "',"
                + "'" + spepossede.getDiplome() + "', "
                + "'" + spepossede.getCoeffPrescription() + "') ;";
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
    CSpePossede convertir_RS_SpePossede(ResultSet rs) {
        try {
            String diplome = rs.getString("POS_DIPLOME_POSSEDER");
            int coeffPrescription = rs.getInt("POS_COEFPRESCRIPTION_POSSEDER");
            
            CTableSpecialite tableSpecialite =  new CTableSpecialite();
            ArrayList<CSpecialite> liste = tableSpecialite.lireUnSpecialite("SPE_CODE_SPECIALITE", rs.getString("SPE_CODE_SPECIALITE"));
            CSpecialite specialite = liste.get(0);
                    
           
            return new CSpePossede(diplome, coeffPrescription, specialite);
        } catch (SQLException ex) {
            Logger.getLogger(CTablePosseder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de inclure
    public ArrayList<CSpePossede> lirePosseder() {
        if (bdd.connecter() == true) {
            ArrayList<CSpePossede> listDePosseder = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `posseder`;");
            try {
            while (rs.next())  {
            CSpePossede spepossede = convertir_RS_SpePossede(rs);
            listDePosseder.add(spepossede);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDePosseder;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire posseder
    public ArrayList<CSpePossede> lireUnPosseder(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CSpePossede> listSpePosseder = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `posseder` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CSpePossede spepossede = convertir_RS_SpePossede(rs);
            listSpePosseder.add(spepossede);
            
            }  
            if(listSpePosseder.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listSpePosseder;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printPosseder(ArrayList<CSpePossede> list){
        list.forEach((list1)->{
            System.out.println("idSpecialite:"+list1.getSpecialite());
        });
    }
    
    // supprimer posseder
    public int supprimerPosseder(String idPraticien, String idSpecialite) {
        String req = "DELETE FROM `posseder` WHERE PRA_NUM_PRATICIEN = '" + idPraticien + "' AND SPE_CODE_SPECIALITE = '"+ idSpecialite +"';";
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

    //mofifier posseder

    public int modifierPosseder(CSpePossede spepossede, CPraticien praticien) {
        String req = "UPDATE posseder SET "
                + "PRA_NUM_PRATICIEN = '" + praticien.getIdPraticien() + "', "
                + "SPE_CODE_SPECIALITE = '" + spepossede.getSpecialite().getIdSpecialite() + "', "
                + "POS_DIPLOME_POSSEDER = '" + spepossede.getDiplome() + "', "
                + "POS_COEFPRESCRIPTION_POSSEDER = '" + spepossede.getCoeffPrescription() +"'"
                + "WHERE PRA_NUM_PRATICIEN = '" + praticien.getIdPraticien() + "' AND POS_DIPLOME_POSSEDER = '" + spepossede.getSpecialite().getIdSpecialite() + "' ; ";
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
        CTablePosseder tableposseder = new CTablePosseder();
        CTableSpecialite tablespecialite = new CTableSpecialite();
        CSpePossede spepossede = new CSpePossede("Doctorat de philo", 15, new CSpecialite(6,"ortho"));
        List<CSpePossede> listespepossede = new ArrayList();
        listespepossede.add(spepossede);
        CTypePraticien typepraticien = new CTypePraticien (6, "Ortho", "Hopital");
        CPraticien praticien = new CPraticien(1, "Leclerc", "Charles", new CAdresse("28", "rue du Général de Gaulle", "35400", "Saint Malo"), typepraticien, listespepossede, 2);
        //tableposseder.insererPosseder(spepossede, praticien);
        //tableposseder.supprimerPosseder("1", "15");

        tableposseder.modifierPosseder(spepossede, praticien);
        
        //tableposseder.printPosseder(tablePosseder.lireUnPossder("15"));

    }

}