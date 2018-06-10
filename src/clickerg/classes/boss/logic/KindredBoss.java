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
public class KindredBoss extends PatternBoss {
    String base_hp = "100";
    String id = "1";
    String name = "Kindred";

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
        BossFactory.registerBoss("1", new KindredBoss());
    }
    
    @Override
    public PatternBoss createBoss() {
        return new KindredBoss();
    }
}
