package java112.employee;

import java112.utilities.PropertiesLoader;

import java.sql.*;
import java.util.Properties;

public class EmployeeDirectory implements PropertiesLoader {
    private Properties properties;

    public EmployeeDirectory(Properties properties) {
        properties = loadProperties("/project4.properties");
        this.properties = properties;
    }

    private Connection establishConnection() {
        Connection conn =null;
        try {
            Class.forName( properties.getProperty("driver") ) ;
            conn  = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;//returns null in case of exception.
    }

    public void addRecord(String employeeID, String firstName, String lastName,
                          String ssn, String department, String room, String phone) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "student", "student");
             PreparedStatement insertEmployee = connection.prepareStatement(
                     "INSERT INTO employees "
                             + "VALUES(?, ?, ?, ?, ?, ?, ?);" );) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            insertEmployee.setInt(1, Integer.parseInt(employeeID));
            insertEmployee.setString(2,firstName);
            insertEmployee.setString(3, lastName);
            insertEmployee.setString(4, ssn);
            insertEmployee.setString(5, department);
            insertEmployee.setString(6, room);
            insertEmployee.setString(7, phone);
            insertEmployee.executeUpdate();
            System.out.printf("Record on %s %s was added%n", firstName, lastName);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception exception) {
            System.err.println("General Error");
            exception.printStackTrace();
        }
    }

    public Search searchEmployeeDB(String searchType, String searchTerm ) {
        Search search = new Search(searchType, searchTerm);
        selectEmployee(search);
        return search;
    }

    private void selectEmployee(Search search) {
        String term = search.getSearchTerm();
        String type = search.getSearchType();
        String query = "";
        if(type.equals("emp_id")) {
            query = "select * from employees where emp_id="
                    + Integer.parseInt(term) + ";";
        } else {
            query = String.format("select * from employees where %s='%s';", type, term);
        }
        try(Connection connection = establishConnection();
            Statement statement = connection.createStatement()
            ) {
            try(ResultSet resultSet = statement.executeQuery(query);){
                //Retrieving the data
                if (!resultSet.isBeforeFirst() ) {
                    search.setFound(false);
                } else {
                    search.setFound(true);
                    while(resultSet.next()) {
                        Employee employee = new Employee();
                        employee.setEmployeeID(Integer.parseInt(resultSet.getString("emp_id")));
                        employee.setFirstName(resultSet.getString("first_name"));
                        employee.setLastName(resultSet.getString("last_name"));
                        employee.setSsn(resultSet.getString("ssn"));
                        employee.setDepartment(resultSet.getString("dept"));
                        employee.setRoom(resultSet.getString("room"));
                        employee.setPhone(resultSet.getString("phone"));
                        search.addFoundEmployee(employee);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}