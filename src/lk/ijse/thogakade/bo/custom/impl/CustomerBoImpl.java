package lk.ijse.thogakade.bo.custom.impl;

import lk.ijse.thogakade.bo.custom.CustomerBo;
import lk.ijse.thogakade.dao.DaoFactory;
import lk.ijse.thogakade.dao.DaoType;
import lk.ijse.thogakade.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDaoImpl customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setSalary(customerDTO.getSalary());
        boolean add = customerDao.add(customer);

        return add;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setSalary(customerDTO.getSalary());
        boolean update = customerDao.update(customer);
        return update;
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> all = customerDao.findAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        CustomerDTO customerDTO = null;

        for (Customer customer : all) {
            dtoList.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return dtoList;
    }
}
