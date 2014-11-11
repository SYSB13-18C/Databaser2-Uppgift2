import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Filip on 2014-11-06.
 */
public class Modell {
    static Connection myCon = null;
    private static Connection getConnection() throws SQLException {

       // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            myCon = DriverManager.getConnection("jdbc:sqlserver://FILIPSACER\\MSSQLSERVER;" +
                    "database=Cronus;" +
                    "user=GRUPP 18;" +
                    "password=Hemligt");

        return myCon;
    }

    public static ArrayList getKeys() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[CONSTRAINT_NAME], [TABLE_NAME], [CONSTRAINT_TYPE] " +
                                            "FROM " +
                                            "INFORMATION_SCHEMA.TABLE_CONSTRAINTS " +
                                            "WHERE " +
                                            "CONSTRAINT_TYPE = 'PRIMARY KEY' ");
        while (myRs.next()){
            result.add("\n" + myRs.getString("CONSTRAINT_NAME") + ", \t\t" + myRs.getString("TABLE_NAME") + ", \t\t" + myRs.getString("CONSTRAINT_TYPE"));
        }
        return result;
    }

    public static ArrayList getIndexes() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[object_id], [name] " +
                                            "FROM " +
                                            "sys.indexes " +
                                            "WHERE " +
                                            "NAME like 'CRONUS%'");
        while (myRs.next()){
            result.add("\n" +myRs.getString("object_id") + ", \t" + myRs.getString("name"));
        }
        return result;
    }

    public static ArrayList getTables() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[object_id], [name] " +
                                            "FROM " +
                                            "sys.tables");
        /*ELLER
            SELECT
                *
            FROM
                INFORMATION_SCHEMA.TABLES
        */
        while (myRs.next()){
            result.add("\n" + myRs.getString("object_id") + ", \t" + myRs.getString("name"));
        }
        return result;
    }

    public static ArrayList getColumns() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[TABLE_NAME], [COLUMN_NAME] " +
                                            "FROM " +
                                            "INFORMATION_SCHEMA.COLUMNS " +
                                            "WHERE " +
                                            "TABLE_NAME like 'CRONUS Sverige AB$Employee%' ");
        /* ELLER
            SELECT
                *
            FROM
                sys.all_columns
            WHERE
                object_id in
                (SELECT
                    object_id
                FROM
                    sys.objects
                WHERE
                    name like 'CRONUS Sverige AB$Employee%')
         */
        while (myRs.next()){
            result.add("\n" + myRs.getString("TABLE_NAME") + ", \t" + myRs.getString("COLUMN_NAME"));
        }
        return result;
    }

    public static ArrayList getConstraints() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[CONSTRAINT_NAME], [CONSTRAINT_TYPE] " +
                                            "FROM " +
                                            "INFORMATION_SCHEMA.TABLE_CONSTRAINTS");
        while (myRs.next()){
            result.add("\n" + myRs.getString("CONSTRAINT_NAME") + ", \t" + myRs.getString("CONSTRAINT_TYPE"));
        }
        return result;
    }

    public static ArrayList getMostRows() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT TOP 1 WITH TIES " +
                                            "t.name, " +
                                            "count(c.name) as rows " +
                                            "FROM " +
                                            "sys.tables t " +
                                            "inner join sys.columns c " +
                                            "ON t.object_id = c.object_id " +
                                            "WHERE " +
                                            "t.name like 'CRONUS%' " +
                                            "GROUP BY " +
                                            "t.name " +
                                            "ORDER BY " +
                                            "count(c.name) DESC");
        while (myRs.next()){
            result.add(myRs.getString("name") + ", " + myRs.getString("rows"));
        }
        return result;
    }

    public static ArrayList getEmployeeInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[No_],[First Name],[Last Name],[Initials],[Job Title] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("No_") + ", \t" + myRs.getString("First Name") + ", \t" + myRs.getString("Last Name") + ", \t" + myRs.getString("Initials") + ", \t" + myRs.getString("Job Title"));
        }
        return result;
    }

    public static ArrayList getEmployeeAbsenceInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("select [Employee No_],[From Date],[To Date],[Cause of Absence Code],[Description] FROM [Cronus].[dbo].[CRONUS Sverige AB$Employee Absence]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("Employee No_") + ", \t" + myRs.getString("From Date") + ", \t" + myRs.getString("To Date") + ", \t" + myRs.getString("Cause of Absence Code") + ", \t" + myRs.getString("Description"));
        }
        return result;
    }

    public static ArrayList getEmployeePortalSetupInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[Search Limit],[Temp_ Key Index],[Temp_ Table No_],[Temp_ Key String],[Temp_ Option Value] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Portal Setup]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("Search Limit") + ", \t" + myRs.getString("Temp_ Key Index") + ", \t" + myRs.getString("Temp_ Table No_") + ", \t" + myRs.getString("Temp_ Key String") + ", \t" + myRs.getString("Temp_ Option Value"));
        }
        return result;
    }

    public static ArrayList getEmployeeQualificationInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[Qualification Code],[From Date],[To Date],[Type],[Description] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Qualification]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("Qualification Code") + ", \t" + myRs.getString("From Date") + ", \t" + myRs.getString("To Date") + ", \t" + myRs.getString("Type") + ", \t" + myRs.getString("Description"));
        }
        return result;
    }

    public static ArrayList getEmployeeRelativeInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[Relative Code],[First Name],[Last Name],[Middle Name],[Birth Date] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Relative] ");
        while (myRs.next()){
            result.add("\n" + myRs.getString("Relative Code") + ", \t" + myRs.getString("First Name") + ", \t" + myRs.getString("Last Name") + ", \t" + myRs.getString("Middle Name") + ", \t" + myRs.getString("Birth Date"));
        }
        return result;
    }

    public static ArrayList getEmployeeStatisticsGroupInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[timestamp],[Code],[Description] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Statistics Group]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("timestamp") + ", \t" + myRs.getString("Code") + ", \t" + myRs.getString("Description"));
        }
        return result;
    }

    public static ArrayList getEmploymentContractInfo() throws SQLException {
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "[timestamp],[Code],[Description] " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employment Contract]");
        while (myRs.next()){
            result.add("\n" + myRs.getString("timestamp") + ", \t" + myRs.getString("Code") + ", \t" + myRs.getString("Description"));
        }
        return result;
    }

    public static ArrayList getEmployeeMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmployeeAbsenceMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Absence]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmployeePortalSetupMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Portal Setup]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmployeeQualificationMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Qualification]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmployeeRelativeMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Relative]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmployeeStatisticsGroupMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employee Statistics Group]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }

    public static ArrayList getEmploymentContractMetaData() throws SQLException{
        ArrayList result = new ArrayList();
        Statement myStmt = getConnection().createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT " +
                                            "* " +
                                            "FROM " +
                                            "[Cronus].[dbo].[CRONUS Sverige AB$Employment Contract]");
        ResultSetMetaData rsMetaData = myRs.getMetaData();

        int columnCount = rsMetaData.getColumnCount();
        result.add("\n\nColumn count: " + columnCount + "\n");

        for(int column = 1; column <= columnCount; column++){
            result.add("\nColumn name: " + rsMetaData.getColumnName(column));
            result.add("\t\tColumn Type: " + rsMetaData.getColumnTypeName(column));
            result.add("\t\tColumn Display size: " + rsMetaData.getColumnDisplaySize(column));
        }
        return result;
    }
}
