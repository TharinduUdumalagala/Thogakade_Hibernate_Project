package lk.ijse.thogakade.dao.custom.impl;

import lk.ijse.thogakade.dao.custom.ItemDao;
import lk.ijse.thogakade.entity.Item;
import lk.ijse.thogakade.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(Item entity) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, s);
        session.delete(item);

        transaction.commit();

        return true;
    }

    @Override
    public Item find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Item");
        List <Item> list = ((org.hibernate.query.Query) query).list();

        transaction.commit();
        session.close();
        return list;
    }
}
