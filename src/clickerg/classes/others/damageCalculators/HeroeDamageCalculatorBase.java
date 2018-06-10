/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.damageCalculators;

import clickerg.classes.others.auxiliars.AuxiliarHeroe;

/**
 *
 * @author cnsak
 */
public class HeroeDamageCalculatorBase extends HeroeDamageCalculator {
    AuxiliarHeroe heroe;
    
    static final double escalado_por_nivel = 1.05;
    public HeroeDamageCalculatorBase(AuxiliarHeroe heroe){
        this.heroe = heroe;
    }
    
    @Override
    public double calcularAtaque() {
        return Double.parseDouble(heroe.getBase_atk())* Math.pow(escalado_por_nivel, Double.parseDouble(heroe.getLvl())-1);
    }
     
}
