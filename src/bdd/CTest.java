package bdd;

import java.util.Date;
import java.util.GregorianCalendar;

public class CTest {
    protected int id;
    protected String nom;
    protected int age;
    protected GregorianCalendar dateNaissance;

    public CTest(int id, String nom, int age, GregorianCalendar dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the dateNaissance
     */
    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
