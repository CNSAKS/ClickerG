/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.others.damageCalculators;

import clickerg.classes.others.auxiliars.AuxiliarHeroe;
import clickerg.classes.others.auxiliars.AuxiliarItem;
import java.util.ArrayList;

/**
 *
 * @author cnsak
 */
public class HeroeDamageCalculatorSlot3 extends HeroeDamageCalculatorItems {
    AuxiliarHeroe heroe;
    ArrayList<AuxiliarItem> items;
    HeroeDamageCalculator calc;
    double item_mult = 0;
    
    public HeroeDamageCalculatorSlot3(HeroeDamageCalculator calc, AuxiliarHeroe heroe, ArrayList<AuxiliarItem> items){
        this.calc = calc;
        this.heroe = heroe;
        this.items = items;
    }
    
    @Override
    public double calcularAtaque() {
        items.stream().filter((i) -> (heroe.getItem_3().equals(i.getId()))).forEachOrdered((i) -> {
            item_mult = Double.parseDouble(i.getBase_mult().replaceAll(",", "."));
        });
        return calc.calcularAtaque()+calc.calcularAtaque()*item_mult;
    }
     
}
