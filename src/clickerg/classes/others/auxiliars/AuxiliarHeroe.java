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
public class AuxiliarHeroe {

    public AuxiliarHeroe(String id, String name, String lvl, String base_atk, String prob, String active, String exp, String id_heroe) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.prob = prob;
        this.active = active;
        this.exp = exp;
        this.id_heroe = id_heroe;
    }
    
    public AuxiliarHeroe(String id, String name, String lvl, String base_atk, String prob, String active, String exp, String id_heroe, String item_1, String item_2, String item_3) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.prob = prob;
        this.active = active;
        this.exp = exp;
        this.id_heroe = id_heroe;
        this.item_1 = item_1;
        this.item_2 = item_2; 
        this.item_3 = item_3; 
    }
    
    public AuxiliarHeroe(String id, String name, String lvl, String base_atk, String prob, String exp) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.prob = prob;
        this.active = "irrelevante";
        this.exp = exp;
        this.id_heroe = id_heroe;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getBase_atk() {
        return base_atk;
    }

    public void setBase_atk(String base_atk) {
        this.base_atk = base_atk;
    }

    public String getProb() {
        return prob;
    }

    public void setProb(String prob) {
        this.prob = prob;
    }

    private String id;
    private String name;
    private String lvl;
    private String base_atk;
    private String prob;
    private String active;
    private String exp;
    private String id_heroe;
    private String item_1;
    private String item_2;
    private String item_3;

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "AuxiliarHeroe{" + "id=" + id + ", name=" + name + ", lvl=" + lvl + ", base_atk=" + base_atk + ", prob=" + prob + ", active=" + active + ", exp=" + exp + ", id_heroe=" + id_heroe + '}';
    }

    public String getId_heroe() {
        return id_heroe;
    }

    public void setId_heroe(String id_heroe) {
        this.id_heroe = id_heroe;
    }

    public String getItem_1() {
        return item_1;
    }

    public void setItem_1(String item_1) {
        this.item_1 = item_1;
    }

    public String getItem_2() {
        return item_2;
    }

    public void setItem_2(String item_2) {
        this.item_2 = item_2;
    }

    public String getItem_3() {
        return item_3;
    }

    public void setItem_3(String item_3) {
        this.item_3 = item_3;
    }
    
}
