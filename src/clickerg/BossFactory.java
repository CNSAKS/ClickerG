/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.util.HashMap;


/**
 *
 * @author Dragon
 */
public class BossFactory {
    
    private static HashMap m_RegisteredBosses = new HashMap();
    

    static {
        m_RegisteredBosses.put("2", new SylvanasBoss());
        m_RegisteredBosses.put("0", new MacarraBoss());
        m_RegisteredBosses.put("1", new KindredBoss());
        
    }
    public void registerBoss(String bossID, PatternBoss b){
        m_RegisteredBosses.put(bossID, b);
    }
    
    public static PatternBoss createBoss(String bossID){
      return ((PatternBoss) m_RegisteredBosses.get(bossID)).createBoss();
    }
    
}
