/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

/**
 *
 * @author cnsak
 */
public class AuxiliarHeroe {

    public AuxiliarHeroe(String id, String name, String lvl, String base_atk, String prob, String active) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.prob = prob;
        this.active = active;
    }
    
    public AuxiliarHeroe(String id, String name, String lvl, String base_atk, String prob) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.prob = prob;
        this.active = "irrelevante";
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

    @Override
    public String toString() {
        return "AuxiliarHeroe{" + "id=" + id + ", name=" + name + ", lvl=" + lvl + ", base_atk=" + base_atk + ", prob=" + prob + '}';
    }
    private String id;
    private String name;
    private String lvl;
    private String base_atk;
    private String prob;
    private String active;
    
}
