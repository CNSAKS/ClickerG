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
abstract class PatternBoss {
    public abstract PatternBoss createBoss();
    public abstract String getBase_hp();
    public abstract String getName();
    public abstract String getId();
}
