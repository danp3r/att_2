import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class QuaeryGenerator {
    private String tableName;
    private Class aClass;
    private List<Field> fields;

    public QuaeryGenerator(String tableName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.tableName = tableName;
        this.aClass = Class.forName(String.format("org.example.%s", tableName));
        this.fields = ReflectionUtils.usefullFields(aClass.newInstance());
    }

    public QuaeryGenerator(Class aClass) throws InstantiationException, IllegalAccessException {
        this.aClass = aClass;
        this.tableName = aClass.getSimpleName();
        this.fields = ReflectionUtils.usefullFields(aClass.newInstance());
    }

    public QuaeryGenerator(String tableName, Class aClass) throws InstantiationException, IllegalAccessException {
        this.tableName = tableName;
        this.aClass = aClass;
        this.fields = ReflectionUtils.usefullFields(aClass.newInstance());
    }

    public String create(){
        String quaery = "CREATE TABLE IF NOT EXISTS %s (".formatted(tableName);

        for(int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            quaery+= "%s %s".formatted(
                    f.getName(),
                    SqlUtils.sqlType(f.getType())
            );
            quaery+= (i != fields.size()-1) ? ", " : ");";
        }

        return quaery;
    }

    public String drop(){
        return "DROP TABLE IF EXISTS %s;".formatted(tableName);
    }

    public String insert(Map<String, Object> values){
        String quaery = "INSERT INTO %s VALUES (".formatted(tableName);

        for(int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String v = SqlUtils.sqlString(values.get(f.getName()));
            quaery+= v;
            quaery+= (i != fields.size()-1) ? ", " : ");";
        }
        return quaery;
    }

    public String delete(Map<String, Object> values) throws Exception {
        String quaery = "DELETE FROM %s WHERE ".formatted(tableName);
        quaery += condition(values, " and ", ";");
        return quaery;
    }

    public String find(Map<String, Object> values) throws Exception {
        String quaery = "SELECT * FROM %s WHERE ".formatted(tableName);
        quaery+= condition(values, " and ", ";");;
        return quaery;
    }

    public String findAll(){
        return "SELECT * FROM %s;".formatted(tableName);
    }


    public String update(Map<String, Object> conditionValues, Map<String, Object> setValues) throws Exception {
        String quaery = "UPDATE %s SET ".formatted(tableName);
        quaery+= condition(setValues, ", ", " WHERE ");
        quaery+= condition(conditionValues, " and ", ";");

        return quaery;
    }




    private String condition(Map<String, Object> values, String ordinary, String last) throws Exception {
        List<String> fields = new ArrayList<>(values.keySet());
        String condition = "";

        for (int i = 0; i < fields.size(); i++) {
            String f = fields.get(i);
            String v = SqlUtils.sqlString(values.get(f));

            condition+= "%s = %s".formatted(f, v);
            condition+= (i != fields.size()-1) ? ordinary : last;
        }
        return condition;
    }

    public String getTableName() {
        return tableName;
    }

    public Class getaClass() {
        return aClass;
    }

    public List<Field> getFields() {
        return fields;
    }
}
