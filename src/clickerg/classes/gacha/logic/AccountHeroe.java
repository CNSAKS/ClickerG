/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.gacha.logic;

/**
 *
 * @author Dragon
 */
public class AccountHeroe extends PrototypeHeroe{

    protected String active;
    protected String id_heroe;

    public AccountHeroe(String active, String id_heroe, String name, String id, String lvl, String base_atk, String exp) {
        super(name, id, lvl, base_atk, exp);
        this.active = active;
        this.id_heroe = id_heroe;
    }
    
    @Override
    public PrototypeHeroe cloneObject() throws CloneNotSupportedException{
       return (AccountHeroe)this.clone();
    }
    
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AuxiliarHeroe{" + "id=" + id + ", name=" + name + ", lvl=" + lvl + ", base_atk=" + base_atk + ", active=" + active + ", exp=" + exp + ", id_heroe=" + id_heroe + '}';
    }

    public String getId_heroe() {
        return id_heroe;
    }

    public void setId_heroe(String id_heroe) {
        this.id_heroe = id_heroe;
    }
}
    

