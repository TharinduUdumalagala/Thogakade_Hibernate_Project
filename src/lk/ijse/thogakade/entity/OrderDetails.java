package lk.ijse.thogakade.entity;

import javax.persistence.*;

@Entity
public class OrderDetails implements SuperEntity{
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int qyt;
    private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Item item, int qyt, double unitPrice) {
        this.order = order;
        this.item = item;
        this.qyt = qyt;
        this.unitPrice = unitPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQyt() {
        return qyt;
    }

    public void setQyt(int qyt) {
        this.qyt = qyt;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "order=" + order +
                ", item=" + item +
                ", qyt=" + qyt +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
