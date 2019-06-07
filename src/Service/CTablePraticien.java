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
 * @author Sabrina Cos
 */
public class CTablePraticien {
    
    //attributs

    protected ArrayList<CPraticien> listepraticiens;
    protected CBDD bdd;
    
    // Getters et Setters

    public ArrayList<CPraticien> getListepraticiens() {
        return listepraticiens;
    }

    public void setListepraticiens(ArrayList<CPraticien> listepraticiens) {
        this.listepraticiens = listepraticiens;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

 
    public CTablePraticien(){
       this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));  
    }
    
    
    //Méthodes qui appelle et modifie la table praticien
   
    //Methode pour inserer un praticien
    public int insererPraticien(CPraticien praticien){
        String req = "INSERT INTO `praticien`(`PRA_NOM_PRATICIEN`, `PRA_PRENOM_PRATICIEN`,"
                + "`PRA_ADRESSE_PRATICIEN`, `PRA_CP_PRATICIEN`,"
                + " `PRA_VILLE_PRATICIEN`, `PRA_COEFNOTORIETE_PRATICIEN`,"
                + " `TYP_CODE_TYPE_PRATICIEN`)"
                + " VALUES ('"+praticien.getNom()+"',"
                + " '"+praticien.getPrenom()+"', '"+praticien.getAdresse().getNumero()+","+praticien.getAdresse().getRue()+"',"
                + " '"+praticien.getAdresse().getCodePostal()+"', '"+praticien.getAdresse().getVille()+"',"
                + " '"+praticien.getCoeffNotoriete()+"',"
                + " '"+praticien.getTypePraticien().getIdType()+"');";
         int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd PRATICIEN KO");
        }
        return res;
    }
    
        CPraticien convertir_RS_Praticien(ResultSet rs) {
        try {
            int id = rs.getInt("PRA_NUM_PRATICIEN");
            String nom = rs.getString("PRA_NOM_PRATICIEN");
            String prenom = rs.getString("PRA_PRENOM_PRATICIEN");
            String adresse = rs.getString("PRA_ADRESSE_PRATICIEN");
            int index = adresse.indexOf(",");
            String numero = adresse.substring(0, index);
            String rue = adresse.substring(index + 1);
            String codepostal = rs.getString("PRA_CP_PRATICIEN");
            String ville = rs.getString("PRA_VILLE_PRATICIEN");
            
            CTableTypePraticien tableTypePraticien = new CTableTypePraticien();
            ArrayList<CTypePraticien> liste = tableTypePraticien.lireUnTypePraticien("TYP_CODE_TYPE_PRATICIEN", rs.getString("TYP_CODE_TYPE_PRATICIEN"));
            CTypePraticien typePraticien = liste.get(0);
            
            CTablePosseder tableposseder = new CTablePosseder();
            List<CSpePossede> liste2 = tableposseder.lireUnPosseder(rs.getString("PRA_NUM_PRATICIEN"), "");
            
            int coeffNotoriete = rs.getInt("PRA_COEFNOTORIETE_PRATICIEN");
            
            
            return new CPraticien(id, nom, prenom, new CAdresse(numero, rue, codepostal, ville), typePraticien, liste2, coeffNotoriete);
        } catch (SQLException ex) {
            Logger.getLogger(CTablePraticien.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
      public ArrayList<CPraticien> lirePraticien() {
        if (bdd.connecter() == true) {
            ArrayList<CPraticien> listeDePraticiens = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `manipulationbdd`.`praticien`");
            try {
                while (rs.next()) {
                    CPraticien praticien = convertir_RS_Praticien(rs);
                    listeDePraticiens.add(praticien);
                }
                if(listeDePraticiens.isEmpty()){
                    System.out.println("PRATICIEN not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDePraticiens;
        } else {
            System.out.println("Connexion manipulationbdd PRATICIEN KO");
        }
        return null;
    }
     
     public ArrayList<CPraticien> lireUnPraticien(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CPraticien> listeDePraticiens = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `manipulationbdd`.`praticien` WHERE "+colonne+" = '"+data+"';");
            try {
                while (rs.next()) {
                    CPraticien praticien = convertir_RS_Praticien(rs);
                    listeDePraticiens.add(praticien);
                }
                if(listeDePraticiens.isEmpty()){
                    System.out.println("PRATICIEN not found");
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listeDePraticiens;
        } else {
            System.out.println("Connexion manipulationbdd PRATICIEN KO");
        }
        return null;
    }
     
     
     public void printPraticiens(ArrayList<CPraticien> liste){
         liste.forEach((list1)->{
             System.out.println("nom :" + list1.getNom()); 
         });
     }
    
    //Methode pour supprimer un praticien d'après de son matricule
    public int supprimerPraticien(String id){
         String req = "DELETE FROM `praticien` WHERE `PRA_NUM_PRATICIEN` = '"+id+"';";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd PRATICIEN KO");
        }
        return res;
    }
    
    //Methode pour modifier un praticien 
    public int modifierPraticien(CPraticien praticien){
    String req = "UPDATE `praticien` SET"
            + "PRA_NUM_PRATICIEN ='"+ praticien.getIdPraticien()+ "' , "
            + "PRA_NOM_PRATICIEN = '"+ praticien.getNom()+ "' ,"
            + "PRA_PRENOM_PRATICIEN = '"+ praticien.getPrenom()+ "' ,"
            + "PRA_ADRESSE_PRATICIEN = '"+ praticien.getAdresse().getNumero()+","+praticien.getAdresse().getRue()+ "' ,"
            + "PRA_CP_PRATICIEN = '"+ praticien.getAdresse().getCodePostal()+ "' ,"
            + "PRA_VILLE_PRATICIEN = '"+ praticien.getAdresse().getVille()+ "' ,"
            + "PRA_COEFNOTORIETE_PRATICIEN = '"+ praticien.getCoeffNotoriete()+ "' ,"
            + "TYP_CODE_TYPE_PRATICIEN = '"+ praticien.getTypePraticien().getIdType()+"'"
            + "' WHERE PRA_NUM_PRATICIEN = '"+praticien.getIdPraticien()+"';";
    
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion manipulationbdd PRATICIEN KO");
        }
        return res;
    }

    
    public static void main(String[] args) {
        
        CTablePraticien tablepraticien = new CTablePraticien();
        CTypePraticien typepraticien = new CTypePraticien(6, "ortho", "hopital");
        CTablePosseder tableposseder = new CTablePosseder();
        CSpecialite specialite = new CSpecialite(8, "Kiné");
        CSpePossede spepossede = new CSpePossede("Doctorat de philo", 15, specialite);
        List<CSpePossede> list = new ArrayList();
        list.add(spepossede);
       CPraticien praticien = new CPraticien(1, "Nelson", "Montfort", new CAdresse("28", "rue Foch", "22100", "Saint Brieuc"),typepraticien, list, 5);
        tablepraticien.insererPraticien(praticien);
        //tablepraticien.modifierPraticien(praticien);
        //tablepraticien.supprimerPraticien("1");
        //tablepraticien.printPraticiens(tablePraticien.lirePraticien());
        //tablepraticien.printPraticiens(tablePraticien.lireUnPraticien("PRA_NUM_PRATICIEN", "12"));
    }

}

