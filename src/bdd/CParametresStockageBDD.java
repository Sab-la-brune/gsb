/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Sabrina Cos
 */
public final class CParametresStockageBDD {

    protected String ip;
    protected int port;
    protected String driver;
    protected String protocole;
    protected String nomBase;
    protected String utilisateur;
    protected String motDePasse;
    protected boolean parametreBDDOK = false;

    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    public CParametresStockageBDD(String fichierProperties) {
        chargerFichierProperties(fichierProperties);
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNomBase() {
        return nomBase;
    }

    public void setNomBase(String nomBase) {
        this.nomBase = nomBase;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isParametreBDDOK() {
        return parametreBDDOK;
    }

    public void setParametreBDDOK(boolean parametreBDDOK) {
        this.parametreBDDOK = parametreBDDOK;
    }

    public void chargerFichierProperties(String fichierProperties) {
        try (InputStream in = new FileInputStream(fichierProperties)) {
            Properties prop = new Properties();
            prop.load(in);
            this.ip = prop.getProperty("ip");
            this.port = Integer.parseInt(prop.getProperty("port"));
            this.driver = prop.getProperty("driver");
            this.protocole = prop.getProperty("protocole");
            this.nomBase = prop.getProperty("nomBase");
            this.utilisateur = prop.getProperty("utilisateur");
            this.motDePasse = prop.getProperty("motDePasse");
            parametreBDDOK = true;
        } catch (IOException e) {
            parametreBDDOK = false;
        }
    }

    @Override
    public String toString() {
        return "" + ip + "   " + port + "   " + driver + "   " + protocole + "   " + nomBase + "   "
                + utilisateur + " " + motDePasse;
    }

    public static void main(String[] args) {

        // Programme de test de lecture du fichier properties de configuration de la base de données
        //
        CParametresStockageBDD parametresStockageBDD
                = new CParametresStockageBDD("parametresBdd.properties");
        if (parametresStockageBDD.isParametreBDDOK()) {
            System.out.println("Parmètres chargés : " + parametresStockageBDD.toString());
        } else {
            System.out.println("Erreur de chargement des paramètres BDD");
        }
    }

}