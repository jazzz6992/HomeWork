package l19;

import java.util.Iterator;

public interface MyCollectionContract<Department, Staff> extends Iterable<Staff> {
    void setDirector(Staff director);

    Staff getDirector();

    void removeDirector();

    void addDepartment(Department department);

    void removeDepartment(Department department);

    void addStaff(Staff staff, Department department);

    void removeStaff(Staff staff, Department department);

    void moveStaff(Staff staff, Department from, Department to);
}
