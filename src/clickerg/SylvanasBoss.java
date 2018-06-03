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
public class SylvanasBoss extends PatternBoss{
    String base_hp = "150";
    String id = "2";
    String name = "Sylvanas";

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

 
    static{
        BossFactory.registerBoss("2", new SylvanasBoss());
    }

    @Override
    public PatternBoss createBoss() {
        return new SylvanasBoss();
    }

}
