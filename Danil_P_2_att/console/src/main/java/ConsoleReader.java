import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ConsoleReader {

    private Map<Class, QuaeryGenerator> quaeryGeneratorMap;
    private PostgresConnection postgresConnection;

    private Set<String> commands = Set.of(
            "exit", "help", "tables", "commands", "insert", "delete", "update", "find", "findall"
    );

    private void commands(){
        System.out.println("COMMANDS:");
        for (String c:commands) {
            System.out.println(c);
        }
        System.out.println();
    }
    private void tables(){
        System.out.println("TABLES: ");
        for (QuaeryGenerator qg:quaeryGeneratorMap.values()) {
            System.out.println(qg.getTableName());
        }
        System.out.println();
    }
    private void help(){
        commands();
        tables();
    }


    public ConsoleReader(Map<Class, QuaeryGenerator> quaeryGeneratorMap, PostgresConnection postgresConnection) {
        this.quaeryGeneratorMap = quaeryGeneratorMap;
        this.postgresConnection = postgresConnection;
    }

    public void readAll() throws Exception {

        while (true){
            System.out.print("command: ");
            String command = new Scanner(System.in).nextLine().trim();
            if(!command.contains(command)){
                System.out.println("this command dont support");
                System.out.println("supported commands");
                commands();
            } else if (command.equals("exit")) {
                return;
            } else if (command.equals("help")) {
                help();
            } else if (command.equals("tables")) {
                tables();
            } else if(command.equals("commands")){
                commands();
            }else {
                System.out.print("table: ");
                String table = new Scanner(System.in).nextLine().trim();
                QuaeryGenerator qg = byTable(table);
                if (qg == null){
                    System.out.println("this table dont exist");
                    System.out.println("existing tables");
                    tables();
                    continue;
                }
                if (command.equals("findAll")){
                    System.out.println("++++");
                    showRecord(
                        postgresConnection.executeQuaery(qg.findAll()),
                        qg
                    );
                } else if (command.equals("find")) {
                    Map<String, Object> values = readValues(qg);
                    showRecord(
                            postgresConnection.executeQuaery(qg.find(values)),
                            qg
                    );
                } else if (command.equals("delete")) {
                    Map<String, Object> values = readValues(qg);
                    postgresConnection.execute(qg.delete(values));
                } else if (command.equals("insert")) {
                    Map<String, Object> values = readValues(qg);
                    postgresConnection.execute(qg.delete(values));
                } else if (command.equals("update")) {
                    System.out.println("condition: ?");
                    Map<String, Object> condition = readValues(qg);
                    System.out.println("set: ?");
                    Map<String, Object> set = readValues(qg);
                    postgresConnection.execute(qg.update(condition, set));
                }
            }
        }
    }

    private QuaeryGenerator byTable(String tableName){
        for (QuaeryGenerator qg: quaeryGeneratorMap.values()) {
            if(qg.getTableName().equals(tableName))
                return qg;
        }
        return null;
    }
    private void showRecord(ResultSet rs, QuaeryGenerator qg) throws SQLException {
        while (rs.next()){
            for (Field f:qg.getFields()) {
                System.out.println("%s == %s".formatted(
                        f.getName(), rs.getObject(f.getName()).toString()
                ));
            }
            System.out.println();
        }
    }

    private Map<String, Object> readValues(QuaeryGenerator qg){
        Map<String, Object> values = new HashMap<>();

        for (Field f:qg.getFields()) {
            System.out.println(f.getName()+" == ");
            Object val = readVal(f);
            if (val != null){
                values.put(f.getName(), val);
            }
        }
        System.out.println();
        return values;
    }

    private Object readVal(Field f){
        String val = new Scanner(System.in).nextLine().trim();
        if(val.isEmpty()){
            return null;
        } else if (f.getType().equals(int.class) || f.getType().equals(Integer.class)) {
            return Integer.parseInt(val);
        } else if (f.getType().equals(long.class) || f.getType().equals(Long.class)) {
            return Long.parseLong(val);
        }

        return val;
    }
}
