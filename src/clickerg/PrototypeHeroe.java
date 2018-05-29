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
public abstract class PrototypeHeroe {
    protected String name;
    protected String id;
    protected String lvl;
    protected String base_atk;
    protected String exp;
    protected String id_heroe;
    
    protected PrototypeHeroe(PrototypeHeroe ph){
        if(ph != null){
            this.name = ph.name;
            this.id = ph.id;
            this.lvl = ph.lvl;
            this.base_atk = ph.base_atk;
            this.exp = ph.exp;
            this.id_heroe = ph.id_heroe;
        }
    }
    
    protected abstract PrototypeHeroe clone();
}
