public class PointOfIssue {
    private Long id;
    private Location location;
    private int capacity;
    private int countEmployees;
    private Long shopId;

    public PointOfIssue() {
    }

    public PointOfIssue(Long id, Location location, int capacity, int countEmployees, Long shopId) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.countEmployees = countEmployees;
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public void setCountEmployees(int countEmployees) {
        this.countEmployees = countEmployees;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "PointOfIssue{" +
                "id=" + id +
                ", location=" + location +
                ", capacity=" + capacity +
                ", countEmployees=" + countEmployees +
                ", shopId=" + shopId +
                '}';
    }
}
