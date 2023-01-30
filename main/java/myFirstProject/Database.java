package myFirstProject;

import java.util.*;

public class Database {
    List<Employee> employees;
    Map<Integer, Employee> indexMap;

    public Database(List<Employee> employees) {
        // create list
        this.employees = new ArrayList<>(employees);
        // create map (index)
        this.indexMap = new HashMap<>();
        for (Employee employee : employees) {
            indexMap.put(employee.getId(), employee);
        }
    }

    public void create() {
        Employee employee = DataUtil.getEmployee("create: ");
        if (employee != null){
            employees.add(employee);
            indexMap.put(employee.getId(), employee);
            System.out.println("Added " + employee);
        }
    }

    public void read() {
        DataUtil.print(employees);
    }

    public void find() {
        String name = DataUtil.getString("find: ");
        List<Employee> found = new ArrayList<>();
        for (Employee employee:employees) {
            //if (employee.getName().equalsIgnoreCase(name)){
            if (employee.getName().contains(name)) {
                found.add(employee);
            }
        }
        DataUtil.print(found);
    }

    private Employee findById(int id){
        return indexMap.get(id);
    }

    public void update() {
        int id = DataUtil.getInt("update, find by id: ");
        Employee employee = findById(id);
        if (employee != null) {
            Employee tmp = DataUtil.getEmployeePart("update (position, salary, age): ");
            if (tmp != null) {
                employee.update(tmp.getPosition(), tmp.getSalary(), tmp.getAge());
                System.out.println("Updated " + employee);
            }
        }
    }

    public void delete() {
        int id = DataUtil.getInt("remove, find by id: ");
        Employee employee = findById(id);
        if (employee != null) {
            employees.remove(employee);
            indexMap.remove(employee.getId());
            System.out.println("Deleted " + employee);
        }
    }

    public void positions() {
        List<Position> positions = new ArrayList<>();
        for (Employee employee: employees) {
            positions.add(employee.getPosition());
        }
        System.out.println(new HashSet<>(positions));
    }

    public void sort() {
        // create map (index)
        Map<Integer, Employee> sortMap = new TreeMap<>();
        for (Employee employee : employees) {
            sortMap.put(employee.getAge(), employee);
        }
        // created sorted List
        List<Employee> sortList = new ArrayList<>();
        for (Integer key : sortMap.keySet()) {
            sortList.add(sortMap.get(key));
        }
        DataUtil.print(sortList);
    }

}
