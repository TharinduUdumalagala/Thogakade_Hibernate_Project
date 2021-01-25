package lk.ijse.thogakade.view.tm;

import javafx.scene.control.Button;

public class ItemTm {
    private String  code;
    private String description;
    private double price;
    private Button btn;

    public ItemTm() {
    }

    public ItemTm(String code, String description, double price, Button btn) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", btn=" + btn +
                '}';
    }
}
