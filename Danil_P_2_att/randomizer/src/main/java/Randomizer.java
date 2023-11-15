import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {


    public static List<OnlineShop> rand(int count) {
        List<OnlineShop> onlineShops = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            Long shopId = (long) (i*1000000);

            List<PointOfIssue> pointOfIssues = new ArrayList<>();

            for (int j = 1; j <= new Random().nextInt(3)+1; j++) {
                Long pointId = (long) (i*1000000+j);

                Long locationId = (long) (i*1000000*j);

                Location location = new Location(
                        locationId,
                        "address#"+locationId,
                        pointId
                );

                PointOfIssue point = new PointOfIssue(
                        pointId,
                        location,
                        new Random().nextInt(100)+20,
                        new Random().nextInt(5)+2,
                        shopId
                );

                pointOfIssues.add(point);
            }


            List<User> users = new ArrayList<>();

            for (int j = 1; j <= new Random().nextInt(40)+10; j++) {
                long userId = i*1000000*j+j;


                List<Order> orders = new ArrayList<>();
                for (int k = 1; k <= new Random().nextInt(5)+2; k++) {
                    long orderId = (userId+shopId)*k;
                    Order order = new Order(
                            orderId,
                            "postal_code#"+orderId,
                            userId,
                            shopId
                    );
                    orders.add(order);
                }
                User user = new User(
                        userId,
                        "fio#"+userId,
                        userId+"@mail.ru",
                        orders,
                        shopId
                );

                users.add(user);
            }

            OnlineShop onlineShop = new OnlineShop(
                    shopId,
                    "shopName#"+shopId,
                    pointOfIssues,
                    users
            );
            onlineShops.add(onlineShop);
        }
        return onlineShops;
    }
}
