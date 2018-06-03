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
public class HeroeDamageCalculatorBase extends HeroeDamageCalculator {
    AuxiliarHeroe heroe;
    
    public HeroeDamageCalculatorBase(AuxiliarHeroe heroe){
        this.heroe = heroe;
    }
    
    @Override
    public double calcularAtaque() {
        return Double.parseDouble(heroe.getBase_atk())* Math.pow(1.05, Double.parseDouble(heroe.getLvl())-1);
    }
     
}
