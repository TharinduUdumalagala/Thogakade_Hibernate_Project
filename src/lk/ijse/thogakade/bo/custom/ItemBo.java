package lk.ijse.thogakade.bo.custom;

import lk.ijse.thogakade.bo.SuperBo;
import lk.ijse.thogakade.dto.ItemDTO;

import java.util.List;

public interface ItemBo extends SuperBo {

    public boolean saveItem(ItemDTO itemDTO)throws Exception;
    public boolean updateItem(ItemDTO itemDTO)throws Exception;
    public boolean deleteItem(String id)throws Exception;
    public List<ItemDTO> getAllItem() throws Exception;
}
