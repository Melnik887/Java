import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Main {
    public static ArrayList<SportObject> array;
    public static void main(String[] args) {
        array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Объекты спорта.csv"))) {
            String title = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",\"");
                if (values.length == 5) {
                    array.add(new SportObject(values[1], values[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sportObject.db");
            if(conn!=null){
                DataBase.connect(conn);
                //DataBase.CreateDB();
                //DataBase.WriteDB(array);

                DataBase.first();
                DataBase.second();
                DataBase.third();

            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}