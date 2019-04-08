/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Donnees.CInvitation;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @Sabrina Cos
 */
public class CTableInviter {
    
    protected ArrayList<CInvitation> listeinviter;
    protected CBDD bdd;

    public ArrayList<CInvitation> getlisteinviter() {
        return listeinviter;
    }

    public void setlisteinviter(ArrayList<CInvitation> listeinviter) {
        this.listeinviter = listeinviter;
    }

    public CBDD getBdd() {
        return bdd;
    }

    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableInviter() {
        this.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
    }
    
   
    //inserer inviter
    public int insererInviter(CInvitation frais, CFicheFrais fichefrais) {
        String req = "INSERT INTO `inviter` (`TF_CODE_TYPE_FRAIS`,`FF_MOIS_FICHE_FRAIS`,`VIS_MATRICULE_VISITEUR`,`INC_QTE_INCLURE`,`INC_MONTANT_INCLURE`)"
                + "VALUES ('" + frais.getTypeFrais().getCode()+ "',"
                + "'" + fichefrais.getMois() + "',"
                + "'" + fichefrais.getMatriculeVisiteur() + "', "
                + "'" + frais.getQuantiteFrais() + "', "
                + "'" + frais.getMontantFrais() + "') ;";
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
    CInvitation convertir_RS_Invitation(ResultSet rs) {
        try {
            String code = rs.getString("TF_CODE_TYPE_FRAIS");
            
            CTableTypeFrais lireTypeFrais =  new CTableTypeFrais();
            ArrayList<CTypeFrais> tab = lireTypeFrais.lireUnTypeFrais("TF_CODE_TYPE_FRAIS",code);
            CTypeFrais typefrais = tab.get(0);
                    
            int quantiteFrais = rs.getInt("INC_QTE_INCLURE");
            float montantFrais = rs.getFloat("INC_MONTANT_INCLURE");
            

            return new CInvitation(quantiteFrais, montantFrais, typefrais);
        } catch (SQLException ex) {
            Logger.getLogger(CTableInviter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //lire liste de inviter
    public ArrayList<CInvitation> lireInviter() {
        if (bdd.connecter() == true) {
            ArrayList<CInvitation> listDeInviter = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inviter`;");
            try {
            while (rs.next())  {
            CInvitation frais = convertir_RS_Frais(rs);
            listDeInviter.add(frais);
            
            }  
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listDeInviter;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    //lire inviter
    public ArrayList<CInvitation> lireUnInviter(String colonne, String data) {
        if (bdd.connecter() == true) {
            ArrayList<CInvitation> listInviter = new ArrayList();
            
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inviter` WHERE "+colonne+" = '"+data+"';");
            try {
            while (rs.next())  {
            CInvitation invitation = convertir_RS_Invitation(rs);
            listInviter.add(invitation);
            
            }  
            if(listInviter.isEmpty()){
                System.out.println("not found");
            }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return listInviter;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    public void printInviter(ArrayList<CInvitation> list){
        list.forEach((list1)->{
            System.out.println("code:"+list1.getTypeFrais());
        });
    }
    
    // supprimer inviter
    public int supprimerInviter(String code, String mois, String matricule) {
        String req = "DELETE FROM `inviter` WHERE TF_CODE_TYPE_FRAIS = '" + code + "' AND FF_MOIS_FICHE_FRAIS = '"+ mois +"' AND VIS_MATRICULE_VISITEUR = '" + matricule + "' ;";
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

    //mofifier inviter

    public int modifierInviter(CInvitation frais, CFicheFrais fichefrais) {
        String req = "UPDATE inviter SET "
                + "TF_CODE_TYPE_FRAIS = '" + frais.getTypeFrais().getCode() + "', "
                + "FF_MOIS_FICHE_FRAIS = '" + fichefrais.getMois() + "', "
                + "VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "', "
                + "INC_QTE_INCLURE = '" + frais.getQuantiteFrais() +"',"
                + "INC_MONTANT_INCLURE = '" + frais.getMontantFrais()+"'"
                + "WHERE TF_CODE_TYPE_FRAIS = '" + frais.getTypeFrais().getCode() + "' AND VIS_MATRICULE_VISITEUR = '" + fichefrais.getMatriculeVisiteur() + "' AND FF_MOIS_FICHE_FRAIS = '" + fichefrais.getMois() + "' ; ";
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
        CTableInviter tableinviter = new CTableInviter();
        CTypeFrais typefrais = new CTypeFrais("22", "Resto", 3.5f);
        CInvitation frais = new CInvitation(52, 1.99f, typefrais);
        ArrayList<CInvitation> listeinviter = new ArrayList();
        listeinviter.add(frais);
        CFicheFrais fichefrais = new CFicheFrais (1, 9, 70.2f, "vw66", listeinviter);
        //tableinviter.insererInviter(frais, fichefrais);
        //tableinviter.supprimerInviter("22", "3", "vw66");

        tableinviter.modifierInviter(frais, fichefrais);
        
        //tableinviter.printInviter(tableInviter.lireUnInviter("vw66","3"));

    }
    
}
