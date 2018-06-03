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
public class MacarraBoss extends PatternBoss{
    String base_hp = "50";

    public String getBase_hp() {
        return base_hp;
    }

    public void setBase_hp(String base_hp) {
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
    String id = "0";
    String name = "Macarra";

    static{
        BossFactory.registerBoss("0", new MacarraBoss());
    }

    @Override
    public PatternBoss createBoss() {
        return new MacarraBoss();
    }
}
