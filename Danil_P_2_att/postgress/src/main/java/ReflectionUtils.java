import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReflectionUtils {
    public static List<Field> usefullFields (Object obj){
        List<Field> fields = new ArrayList<>();
        for (Field f:obj.getClass().getDeclaredFields()) {
            if (isUsefull(f.getType()))
                fields.add(f);
        }
        return fields;
    }

    private static boolean isUsefull(Class c){
        return Set.of(
                Long.class, long.class, Integer.class, int.class, String.class
        ).contains(c);
    }

    public static Object getVal(Object o, Field f) throws Exception {
        return o.getClass().getMethod(
                "get" + capitalize(f.getName())
        ).invoke(o);
    }
    private static String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
