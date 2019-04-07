/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;
 
/**
 *
 * @author camilletilmont
 */
public class CFormeMedicament {
    
    //attributs Forme de Medicament
    
    protected int idFormeMedic;
    protected String NomFormMedic;
    
    
    //Getter et Setter
    
    public int getIdFormeMedic() {
        return idFormeMedic;
    }

    public final void setIdFormeMedic(int idFormeMedic) {
        this.idFormeMedic = idFormeMedic;
    }

    public String getNomFormMedic() {
        return NomFormMedic;
    }

    public final void setNomFormMedic(String NomFormMedic) {
        this.NomFormMedic = NomFormMedic;
    }
    
    // Constructeur Custom

    public CFormeMedicament(int id,String nomForm){
        setIdFormeMedic(id);
        setNomFormMedic(nomForm);
    
    }
    
}
