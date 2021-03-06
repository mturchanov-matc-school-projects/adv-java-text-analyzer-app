package java112.project4;

import java.sql.*;

/**
 * This app enters
 * a new employee to a
 * database
 *
 * @author Mike Turchanov
 */
public class JDBCInsertEmployee {

    /**
     * Inserts a new employee to DB
     *
     * @param id         id
     * @param firstName  first name
     * @param lastName   last name
     * @param ssn        ssn
     * @param department department
     * @param room       room number
     * @param phone      phone number
     */
    public void insertEmployee(String id, String firstName, String lastName,
                               String ssn, String department, String room, String phone) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "student", "student");
             Statement statement = connection.createStatement();) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String insertString = String.format("INSERT INTO employees "
                            + "VALUES(%d, '%s', '%s', '%s', '%s', '%s', '%s');",
                    Integer.parseInt(id), firstName, lastName, ssn, department, room, phone
            );
            int updatedEntries = statement.executeUpdate(insertString);
            System.out.println("queryString: " + insertString);
            System.out.println(updatedEntries + " entry/entries were changed");
        } catch (ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        }
    }

    /**
     * The main program for the JDBCInsertEmployee class
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        JDBCInsertEmployee employees = new JDBCInsertEmployee();
        if (args.length == 7) {
            employees.insertEmployee(args[0], args[1], args[2],
                    args[3], args[4], args[5], args[6]);
            System.out.printf("New employee: %s %s was added%n",
                    args[1], args[2]);
            employees.readLastAddedEntry();
        } else {
            System.out.println("Please enter appropriate input");
        }
    }

    public void readLastAddedEntry() {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "student", "student");
            Statement statement = connection.createStatement(); ) {
            try(ResultSet resultSet = statement.executeQuery("select * from employees order by emp_id desc limit 1");){
                //Retrieving the data
                while(resultSet.next()) {
                    String employeeId = resultSet.getString("emp_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String ssn = resultSet.getString("ssn");
                    String dept = resultSet.getString("dept");
                    String room = resultSet.getString("room");
                    String phone = resultSet.getString("phone");
                    System.out.printf("Last added entry:  %s %s %s %s %s %s %s%n",
                            employeeId, firstName, lastName, ssn, dept, room, phone);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}