/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquantance.IBusiness;
import Acquantance.IData;

/**
 *
 * @author malte
 */
public class BusinessFacade implements IBusiness{
    
    private IData data;

    @Override
    public void injectData(IData data) {
        this.data = data;
    }
    
}
