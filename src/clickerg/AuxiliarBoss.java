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
public class AuxiliarBoss {
    private String id;
    private String name;
    private String base_hp;

    public AuxiliarBoss(String id, String name, String base_hp) {
        this.id = id;
        this.name = name;
        this.base_hp = base_hp;
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

    public String getBase_hp() {
        return base_hp;
    }

    public void setBase_hp(String base_hp) {
        this.base_hp = base_hp;
    }
    
    
}
