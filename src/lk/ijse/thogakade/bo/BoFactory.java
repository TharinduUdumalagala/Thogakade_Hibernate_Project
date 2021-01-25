package lk.ijse.thogakade.bo;

import lk.ijse.thogakade.bo.custom.impl.CustomerBoImpl;
import lk.ijse.thogakade.bo.custom.impl.ItemBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    public BoFactory() {
    }
    public static BoFactory getInstance(){
        return (null == boFactory) ? boFactory = new BoFactory() : boFactory;
    }


    public <T extends SuperBo> T getBo(BoType boType){
        switch (boType){
            case ITEM:
                return (T) new ItemBoImpl();
            case CUSTOMER:
                return  (T) new CustomerBoImpl();
            default:
                return null;
        }
    }
}
