package lk.ijse.thogakade.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.thogakade.bo.BoFactory;
import lk.ijse.thogakade.bo.BoType;
import lk.ijse.thogakade.bo.custom.impl.CustomerBoImpl;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.view.tm.CustomerTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public TableView <CustomerTm>tbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colDelete;
    public JFXTextField txtName;
    public AnchorPane root;


    CustomerBoImpl customerBoImpl = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){
        getAllCustomer();
        tableListener();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory("Id"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colSalary.setCellValueFactory(new PropertyValueFactory("Salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    private void tableListener() {
        tbl.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue) ->{
            setData((CustomerTm) newValue);
        });
    }

    private void setData(CustomerTm customerTm) {
        try {
            txtId.setText(customerTm.getId());
            txtName.setText(customerTm.getName());
            txtAddress.setText(customerTm.getAddress());
            txtSalary.setText(String.valueOf(customerTm.getSalary()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAllCustomer() {
        try {
            ObservableList<CustomerTm> tmlist = FXCollections.observableArrayList();
            List<CustomerDTO> all = customerBoImpl.getAllCustomer();

            for (CustomerDTO customerDTO : all){
                Button button = new Button("Delete");
                CustomerTm customerTm = new CustomerTm(
                        customerDTO.getId(),
                        customerDTO.getName(),
                        customerDTO.getAddress(),
                        customerDTO.getSalary(),
                        button
                );
                tmlist.add(customerTm);
                button.setOnAction((e)->{
                    try {
                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("ON", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.orElse(no) == ok){
                            if (customerBoImpl.deleteCustomer(customerTm.getId())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
                                getAllCustomer();
                                return ;
                            }
                            new Alert(Alert.AlertType.WARNING,"Try Again..", ButtonType.OK).show();
                        }else {

                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                });
                tbl.setItems(tmlist);
            }
        }catch (Exception e){
        }

    }

    public void btnSave(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try{
            CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);
            boolean b = customerBoImpl.saveCustomer(customerDTO);
            if (b){
                new  Alert(Alert.AlertType.CONFIRMATION,"Do You Save it ?").showAndWait();
                txtId.setText(null);
                txtName.setText(null);
                txtAddress.setText(null);
                txtSalary.setText(null);
                getAllCustomer();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"try again carefully !").showAndWait();
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String Id = txtId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        double Salary = Double.parseDouble(txtSalary.getText());

        try{
            if (customerBoImpl.updateCustomer(new CustomerDTO(
                    Id,Name,Address,Salary
            ))){

                txtId.setText(null);
                txtName.setText(null);
                txtAddress.setText(null);
                txtSalary.setText(null);
                getAllCustomer();
            }else{
                new  Alert(Alert.AlertType.ERROR,"Something Happened").show();
        }
    } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something Happened").show();
        }
    }

    public void OnBackAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml")));
    }
}
