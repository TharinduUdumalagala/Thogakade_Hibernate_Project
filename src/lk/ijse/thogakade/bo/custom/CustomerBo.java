package lk.ijse.thogakade.bo.custom;

import lk.ijse.thogakade.bo.SuperBo;
import lk.ijse.thogakade.dto.CustomerDTO;

import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean saveCustomer(CustomerDTO customerDTO)throws Exception;
    public boolean updateCustomer(CustomerDTO customerDTO)throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomer() throws Exception;
}
