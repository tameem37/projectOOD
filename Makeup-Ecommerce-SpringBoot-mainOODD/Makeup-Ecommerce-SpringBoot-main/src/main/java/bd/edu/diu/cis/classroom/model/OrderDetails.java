package bd.edu.diu.cis.classroom.model;

import javax.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private double price;
    private int quantity;

    public OrderDetails(Long id, Orders orders, double price, int quantity, Makeup makeup) {
        this.id = id;
        this.orders = orders;
        this.price = price;
        this.quantity = quantity;
        this.makeup = makeup;
    }

    public OrderDetails() {
    }

    @ManyToOne
    @JoinColumn(name = "makeup_id")
    private Makeup makeup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Makeup getMakeup() {
        return makeup;
    }

    public void setMakeup(Makeup makeup) {
        this.makeup = makeup;
    }
}
