/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;


/**
 *
 * @author Sabrina Cos
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class CTableTests {

    protected CBDD bdd;

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public String formaterDate(GregorianCalendar gc) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setCalendar(gc);
        return fmt.format(gc.getTime());
    }

    public int supprimerTable() {
        String req = "DROP TABLE test";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int creerTableTests() {
        String requete = "CREATE TABLE `tableTests` ("
                + "`id` int(11) NOT NULL AUTO_INCREMENT , PRIMARY KEY (`id`), `nom` varchar(50) NOT NULL, `age` int(11) NOT NULL,"
                + "`dateNaissance` date NOT NULL ) ENGINE = MyISAM DEFAULT CHARSET = latin1;";
        int res = -1;
        System.out.println("" + requete);

        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(requete);
            System.out.println("Resultat requête = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion à la base impossible");
        }
        return res;
    }

    public CTest convertir_RS_Test(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int age = rs.getInt("age");
            Date date = rs.getDate("dateNaissance");
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            return new CTest(id, nom, age, gc);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTests.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<CTest> lireTests() {

        if (bdd.connecter() == true) {
            ArrayList<CTest> listeTests = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("select * from tableTest");
            try {
                while (rs.next()) {
                    CTest test = convertir_RS_Test(rs);
                    listeTests.add(test);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeTests;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public ArrayList<CTest> lireTests(String debutNom) {

        if (bdd.connecter() == true) {
            ArrayList<CTest> listeTests = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("select * from tableTests WHERE `nom` LIKE '" + debutNom + "%'");
            try {
                while (rs.next()) {
                    CTest test = convertir_RS_Test(rs);
                    listeTests.add(test);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeTests;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public CTest lireTest(int id) {
        CTest test = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet rs = bdd.executerRequeteQuery("select * from tabelTest  WHERE `tabelTest`.`id` = " + id);
            try {
                if (rs.next()) {
                    test = convertir_RS_Test(rs);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return test;
    }

    public int insererTest(CTest test) {
        int res = -1;
        if (bdd.connecter() == true) {
            String dateNaissance = formaterDate(test.getDateNaissance());
            String req = "INSERT INTO `tableTests` (`id`, `nom`, `age`, `dateNaissance`)"
                    + "VALUES (NULL, '" + test.getNom() + "', '" + test.getAge() + "', '" + dateNaissance + "');";
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int mettreAJourTest(CTest test) {
        int res = -1;
        if (bdd.connecter() == true) {
            String dateNaissance = formaterDate(test.getDateNaissance());
            String req = "UPDATE `tableTests`"
                    + "SET `nom` = '" + test.getNom() + "', `age` = '" + test.getAge() + "', `dateNaissance` = '" + dateNaissance + "' WHERE `tableTests`.`id` =" + test.getId();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
    
    public int supprimerTest(int id) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM `tableTests` WHERE `id` = " + id;
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int supprimerTousLesTests() {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM `tableTests` ";
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        CTableTests tableTests = new CTableTests();
        tableTests.setBdd(bdd);  // Création de l'association simple entre TableEtudiants et CBDD

        //  tableEtudiants.supprimerTable();
        //   tableTests.creerTableTests();   // Validé
        //
        tableTests.insererTest(new CTest(-1, "AAA", ((int) (Math.random() * 100)), new GregorianCalendar()));
        tableTests.insererTest(new CTest(-1, "BBB", ((int) (Math.random() * 100)), new GregorianCalendar()));
        tableTests.insererTest(new CTest(-1, "CCC", ((int) (Math.random() * 100)), new GregorianCalendar()));
        tableTests.insererTest(new CTest(-1, "DDD", ((int) (Math.random() * 100)), new GregorianCalendar()));
        tableTests.insererTest(new CTest(-1, "EEE", ((int) (Math.random() * 100)), new GregorianCalendar()));
        tableTests.mettreAJourTest(new CTest(3, "ccc", 14, new GregorianCalendar()));
        tableTests.supprimerTest(4);/*
         ArrayList<CTest> listeTests = tableTests.lireTests();
         for (CTest test : listeTests) {
         System.out.println("" + test.toString());
         }
    
         */

        ArrayList<CTest> listeTests = tableTests.lireTests();
        for (CTest test : listeTests) {
            System.out.println("" + test.toString());
        }

    }
}

