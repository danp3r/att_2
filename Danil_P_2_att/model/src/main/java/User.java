import java.util.List;

public class User {
    private Long id;
    private String fio;
    private String mail;
    private List<Order> orders;
    private Long shopId;

    public User() {
    }

    public User(Long id, String fio, String mail, List<Order> orders, Long shopId) {
        this.id = id;
        this.fio = fio;
        this.mail = mail;
        this.orders = orders;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", mail='" + mail + '\'' +
                ", orders=" + orders +
                ", shopId=" + shopId +
                '}';
    }
}
