import java.sql.SQLException;
import java.util.Map;

public class InsertMain {
    private static PostgresConnection postgressConnection = new PostgresConnection();
    private static Map<Class, QuaeryGenerator> quaeryGeneratorMap;

    static {
        try {
            quaeryGeneratorMap = Map.of(
                    Location.class, new QuaeryGenerator("locations", Location.class),
                    OnlineShop.class, new QuaeryGenerator("shops", OnlineShop.class),
                    Order.class, new QuaeryGenerator("orders", Order.class),
                    PointOfIssue.class, new QuaeryGenerator("issues", PointOfIssue.class),
                    User.class, new QuaeryGenerator("users", User.class)
            );

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        for (QuaeryGenerator qg: quaeryGeneratorMap.values()) {
            try {
                postgressConnection.execute(qg.drop());
                postgressConnection.execute(qg.create());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws Exception {

        for (OnlineShop os: Deserializer.deserialize("file.json")) {
            postgressConnection.execute(
                    quaeryGeneratorMap.get(os.getClass()).insert(new Mapper(os).toDict())
            );
            for (PointOfIssue poi: os.getPointOfIssues()) {
                postgressConnection.execute(
                        quaeryGeneratorMap.get(poi.getClass()).insert(new Mapper(poi).toDict())
                );
                Location l = poi.getLocation();
                postgressConnection.execute(
                        quaeryGeneratorMap.get(l.getClass()).insert(new Mapper(l).toDict())
                );
            }

            for (User u: os.getUsers()) {
                postgressConnection.execute(
                        quaeryGeneratorMap.get(u.getClass()).insert(new Mapper(u).toDict())
                );
                for (Order o: u.getOrders()) {
                    postgressConnection.execute(
                            quaeryGeneratorMap.get(o.getClass()).insert(new Mapper(o).toDict())
                    );
                }
            }
        }


    }
}
