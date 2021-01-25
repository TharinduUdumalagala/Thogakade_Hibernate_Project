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
import lk.ijse.thogakade.bo.custom.impl.ItemBoImpl;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.view.tm.ItemTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ItemFormController {
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPrice;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colDelete;
    public TableView<ItemTm> tblItem;
    public AnchorPane root;

    ItemBoImpl itemBo= BoFactory.getInstance().getBo(BoType.ITEM);

    public void initialize(){
        getAllItem();
        tableListener();
        setCellValueFactory();
    }



    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory("Code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory("Price"));
        colDelete.setCellValueFactory(new PropertyValueFactory("btn"));

    }

    private void tableListener() {
        tblItem.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ItemTm) newValue);
                });
    }

    private void setData(ItemTm itemTm) {
        try {
            txtCode.setText(itemTm.getCode());
            txtDescription.setText(itemTm.getDescription());
            txtPrice.setText(String.valueOf(itemTm.getPrice()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getAllItem() {
        try {
            ObservableList<ItemTm> tms = FXCollections.observableArrayList();
            List<ItemDTO> all = itemBo.getAllItem();
            for (ItemDTO itemDTO : all){
                Button btn = new Button("Delete");
                ItemTm itemTm = new ItemTm(
                        itemDTO.getCode(),
                        itemDTO.getDescription(),
                        itemDTO .getPrice(),
                        btn
                );
                tms.add(itemTm);
                btn.setOnAction((e) -> {
                    try {

                        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("ON", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.orElse(no) == ok){
                            if (itemBo.deleteItem(itemTm.getCode())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
                                getAllItem();
                                return ;
                            }
                            new Alert(Alert.AlertType.WARNING,"Try Again..", ButtonType.OK).show();
                        }else {

                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
        }


    }


    public void btnSave(ActionEvent actionEvent) {
        String Code = txtCode.getText();
        String Description = txtDescription.getText();
        double Price = Double.parseDouble(txtPrice.getText());

        try {
            if (itemBo.saveItem(new ItemDTO(
                    Code,Description,Price
            ))){
                txtCode.setText(null);
                txtDescription.setText(null);
                txtPrice.setText(null);
                new Alert(Alert.AlertType.INFORMATION,"Item Added..").show();
                getAllItem();
            }
    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String Code = txtCode.getText();
        String Description = txtDescription.getText();
        double Price = Double.parseDouble(txtPrice.getText());


        try {
            if (itemBo.updateItem(new ItemDTO(
                    Code,Description,Price
            ))){
                getAllItem();
                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
                txtCode.setText(null);
                txtDescription.setText(null);
                txtPrice.setText(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnBackAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/DashBoardForm.fxml")));
    }
}
