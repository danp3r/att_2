import java.lang.reflect.Field;
import java.util.*;

public class Mapper {
    private Object object;
    private Map<String, Object> map = null;

    public Mapper() {
    }
    public Mapper(Object object) {
        this.object = object;
    }
    public Map<String, Object> toDict() throws Exception {
        if (map == null) {
            Map<String, Object> map = new HashMap<>();
            for (Field f : ReflectionUtils.usefullFields(object)) {
                Object v = ReflectionUtils.getVal(object, f);
                if (v != null)
                    map.put(f.getName(), v);
            }
            this.map = map;
        }

        return this.map;
    }


}
