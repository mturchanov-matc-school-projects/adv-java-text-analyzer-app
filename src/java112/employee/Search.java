package java112.employee;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private String searchType;
    private String searchTerm;
    private List<Employee> results;
    private boolean isFound;

    public Search(String searchType, String searchTerm) {
        this.searchType = searchType;
        this.searchTerm = searchTerm;
        results = new ArrayList<>();
    }

    public Search() { results = new ArrayList<>(); }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Employee> getResults() {
        return results;
    }

    public void setResults(ArrayList<Employee> results) {
        this.results = results;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public void addFoundEmployee(Employee employee) {
        results.add(employee);
    }
}