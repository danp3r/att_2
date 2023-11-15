public class Order {
    private Long id;
    private String postalCode;
    private Long userId;
    private Long shopId;

    public Order() {
    }

    public Order(Long id, String postalCode, Long userId, Long shopId) {
        this.id = id;
        this.postalCode = postalCode;
        this.userId = userId;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", postalCode='" + postalCode + '\'' +
                ", userId=" + userId +
                ", shopId=" + shopId +
                '}';
    }
}
