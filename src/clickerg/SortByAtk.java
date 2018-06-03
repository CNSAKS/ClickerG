/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.util.Comparator;

/**
 *
 * @author cnsak
 */
public class SortByAtk implements Comparator<AuxiliarItem>{
    @Override
    public int compare(AuxiliarItem a, AuxiliarItem b){
        if(a.getBase_mult().length() > b.getBase_mult().length()){
            return -1;
        }else if (a.getBase_mult().length() < b.getBase_mult().length()){
            return 1;
        }
        return (a.getBase_mult().compareTo(b.getBase_mult()))*-1;
    }
}
