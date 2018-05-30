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
public abstract class PrototypeHeroe implements Cloneable {
    protected String name;
    protected String id;
    protected String lvl;
    protected String base_atk;
    protected String exp;

    public PrototypeHeroe(String name, String id, String lvl, String base_atk, String exp) {
        this.name = name;
        this.id = id;
        this.lvl = lvl;
        this.base_atk = base_atk;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
    
    
    
    protected abstract PrototypeHeroe cloneObject() throws CloneNotSupportedException;
}
