import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static Statement statmt;
    public static Connection conn;

    public static void connect(Connection con)throws SQLException {
        conn = con;
        statmt = conn.createStatement();
    }
    public static void CreateDB() throws SQLException {
        statmt.execute("CREATE TABLE Sport_Object ('name' text, 'subjectRF' text);");
    }
    public static void WriteDB(ArrayList<SportObject> arr) throws SQLException {
        for (SportObject s : arr){
            String insert = "INSERT INTO Sport_Object VALUES (?,?)";
            PreparedStatement pr = conn.prepareStatement(insert);
            pr.setObject(1, s.getName());
            pr.setObject(2, s.getSubjectRF());
            pr.execute();
        }
    }
    public static void second() throws SQLException{
        double result = 0;
        int k = 0;
        ResultSet out = statmt.executeQuery("SELECT subjectRF, count(name) from Sport_Object group by 1;");
        while (out.next()) {
            result += Double.parseDouble(out.getString(2));
            k +=1 ;
        }
        String str = new DecimalFormat("#0").format(result/k);
        System.out.println("\n"+"Среднее количество объектов спорта в регионах = "+str+"\n");
    }
    public static void third() throws SQLException{
        ResultSet out = statmt.executeQuery("SELECT subjectRF, count(name) from Sport_Object group by 1 order by count(name) desc limit 3;");
        while (out.next()){
            System.out.println(out.getString(1) + " - " + out.getString(2) + " объектов спорта");
        }
    }
    public static void first() throws SQLException{
        List<String> a = new ArrayList<>();
        String Moscow = "";
        String MoscowObl = "";
        ResultSet out = statmt.executeQuery("SELECT subjectRF, count(name) from Sport_Object group by 1;");
        while (out.next()){
            if (out.getString(1).equals("Москва")){
                Moscow = out.getString(2);
            }
            if (out.getString(1).equals("Московская область")){
                MoscowObl = out.getString(2);
            }
            else {
                a.add(out.getString(1) + " -- " + out.getString(2));
            }
        }
        int r = Integer.parseInt(Moscow) + Integer.parseInt(MoscowObl);
        a.add("Московская область -- " + r);

        Graf g = new Graf("Graf", "Количество спортивных объектов в каждом регионе", a);
        g.pack();
        g.setSize(800,600);
        g.setVisible(true);
    }
}
