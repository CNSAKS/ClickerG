/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.boss.logic;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

 
    static{
        BossFactory.registerBoss("2", new SylvanasBoss());
    }

    @Override
    public PatternBoss createBoss() {
        return new SylvanasBoss();
    }

}
