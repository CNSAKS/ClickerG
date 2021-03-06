/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.boss.logic;

import java.util.HashMap;


/**
 *
 * @author Dragon
 */
public class BossFactory{
    
    private static final HashMap m_RegisteredBosses = new HashMap();
    
    public static void registerBoss(String bossID, PatternBoss b){
        m_RegisteredBosses.put(bossID, b);
    }
    
    public PatternBoss createBoss(String bossID){
      return ((PatternBoss) m_RegisteredBosses.get(bossID)).createBoss();
    }
    
}
