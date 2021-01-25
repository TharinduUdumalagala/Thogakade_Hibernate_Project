package lk.ijse.thogakade.dao;

import lk.ijse.thogakade.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.thogakade.dao.custom.impl.ItemDaoImpl;

public class DaoFactory {
    private  static DaoFactory daoFactory;

    public static DaoFactory getInstance(){

        return (null == daoFactory) ? daoFactory =new DaoFactory() : daoFactory;
    }

    public <T extends  SuperDao>T getDao(DaoType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            default:
                return null;
        }
    }
}
