package lk.ijse.thogakade.bo.custom.impl;

import lk.ijse.thogakade.bo.custom.ItemBo;
import lk.ijse.thogakade.dao.DaoFactory;
import lk.ijse.thogakade.dao.DaoType;
import lk.ijse.thogakade.dao.custom.impl.ItemDaoImpl;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDaoImpl itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws Exception {
        return itemDao.add(new Item(
           itemDTO.getCode(),
           itemDTO.getDescription(),
           itemDTO.getPrice()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        return itemDao.update(new Item(
           itemDTO.getCode(),
           itemDTO.getDescription(),
           itemDTO.getPrice()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDao.delete(id);
    }

    @Override
    public List<ItemDTO> getAllItem() throws Exception {
        List<Item> all = itemDao.findAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for(Item item : all){
            itemDTOS.add(new ItemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getPrice()
            ));
        }
        return itemDTOS;
    }
}
