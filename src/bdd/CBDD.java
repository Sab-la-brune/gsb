/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sabrina Cos
 */
public class CBDD {

    protected CParametresStockageBDD parametresStockageBDD = null;
    Connection conn = null;
    Statement stmt = null;

    public CBDD(CParametresStockageBDD parametresStockageBDD) {
        this.parametresStockageBDD = parametresStockageBDD;
        try {
            Class.forName(parametresStockageBDD.getDriver()); // Chargement du driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean connecter() {
        try {
            conn = DriverManager.getConnection(getParametresStockageBDD().getProtocole() + "//"
                    + getParametresStockageBDD().getIp() + "/"
                    + getParametresStockageBDD().getNomBase(),
                    getParametresStockageBDD().getUtilisateur(),
                    getParametresStockageBDD().getMotDePasse()
            );
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deconnecter() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int executerRequeteUpdate(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

   public static boolean testerConnexion(String nomFichierProperties) {
        CBDD bdd = new CBDD(new CParametresStockageBDD(nomFichierProperties));
        if (bdd.connecter() == true) {

            bdd.deconnecter();
            return true;
        } else {
            return false;

        }
    }

    public ResultSet executerRequeteQuery(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String pretraiterChaineSQL(String s)
    {
        return s.trim().replace("'", "''");
    }
    
    public static void main(String[] args) {

        // Programme de test de connexion/déconnexion à la BDD
        //
        if (CBDD.testerConnexion("parametresBdd.properties")) {
            System.out.println("Connexion OK");
        } else {
            System.out.println("Connexion impossible");
        }

    }

    public CParametresStockageBDD getParametresStockageBDD() {
        return parametresStockageBDD;
    }

}
