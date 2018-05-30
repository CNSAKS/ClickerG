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

    public GachaHeroe(String prob, String name, String id, String lvl, String base_atk, String exp) {
        super(name, id, lvl, base_atk, exp);
        this.prob = prob;
    }
    
    
    
    
     /*public GachaHeroe(String id, String name, String lvl, String atk, String prob){
     }*/
     
    @Override
    protected PrototypeHeroe cloneObject() throws CloneNotSupportedException{
       return (PrototypeHeroe)this.clone();
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
        return "AuxiliarHeroe{" + "id=" + id + ", name=" + name + ", lvl=" + lvl + ", base_atk=" + base_atk + ", prob=" + prob + ", exp=" + exp + '}';
    }

}
