import java.util.Map;

public class SqlUtils {

    public static String sqlType(Class type){
        if(type.equals(Long.class) || type.equals(long.class))
            return "BIGINT";
        else if (type.equals(Integer.class) || type.equals(int.class))
            return "INT";
        else if (type.equals(String.class))
            return "VARCHAR(256)";

        return "";
    }

    public static String sqlString(Object val){
        String v = val.toString();
        if(val.getClass().equals(String.class))
            v = "\'%s\'".formatted(v);
        return v;
    }

}
