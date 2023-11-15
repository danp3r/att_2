import java.util.List;

public class OnlineShop {

    private Long id;
    private String shopName;

    private List<PointOfIssue> pointOfIssues;
    private List<User> users;

    public OnlineShop() {
    }

    public OnlineShop(Long id, String shopName, List<PointOfIssue> pointOfIssues, List<User> users) {
        this.id = id;
        this.shopName = shopName;
        this.pointOfIssues = pointOfIssues;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<PointOfIssue> getPointOfIssues() {
        return pointOfIssues;
    }

    public void setPointOfIssues(List<PointOfIssue> pointOfIssues) {
        this.pointOfIssues = pointOfIssues;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "OnlineShop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", pointOfIssues=" + pointOfIssues +
                ", users=" + users +
                '}';
    }
}
