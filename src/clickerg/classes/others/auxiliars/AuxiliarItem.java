/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.auxiliars;

/**
 *
 * @author cnsak
 */
public class AuxiliarItem {
    private String id;
    private String name;
    private String base_mult;
    private String equipado;
    
     public AuxiliarItem(String id, String name, String base_mult) {
        this.id = id;
        this.name = name;
        this.base_mult = base_mult;
    }
     
    public AuxiliarItem(String id, String name, String base_mult, String equipado) {
        this.id = id;
        this.name = name;
        this.base_mult = base_mult;
        this.equipado = equipado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase_mult() {
        return base_mult;
    }

    public void setBase_mult(String base_mult) {
        this.base_mult = base_mult;
    }

    public String getEquipado() {
        return equipado;
    }

    public void setEquipado(String equipado) {
        this.equipado = equipado;
    }
     
}
