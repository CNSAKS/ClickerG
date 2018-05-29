/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

/**
 *
 * @author Dragon
 */
public class GachaHeroe extends PrototypeHeroe{

    protected String prob;
    
    public GachaHeroe(GachaHeroe e){
        super(e);
        if(e!=null){
            this.prob = e.prob;
        }
   // public GachaHeroe(String id, String name, String lvl, String atk, String prob){
    
    }
     /*public GachaHeroe(String id, String name, String lvl, String atk, String prob){
     }*/
     
    @Override
    protected PrototypeHeroe clone() {
       return new GachaHeroe(this);
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


    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "AuxiliarHeroe{" + "id=" + id + ", name=" + name + ", lvl=" + lvl + ", base_atk=" + base_atk + ", prob=" + prob + ", exp=" + exp + ", id_heroe=" + id_heroe + '}';
    }

    public String getId_heroe() {
        return id_heroe;
    }

    public void setId_heroe(String id_heroe) {
        this.id_heroe = id_heroe;
    }
}
