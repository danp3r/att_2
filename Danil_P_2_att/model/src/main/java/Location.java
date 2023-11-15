public class Location {
    private Long id;
    private String address;
    private Long pointId;

    public Location() {
    }

    public Location(Long id, String address, Long pointId) {
        this.id = id;
        this.address = address;
        this.pointId = pointId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", pointId=" + pointId +
                '}';
    }
}
