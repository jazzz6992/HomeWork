package l19;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class MyCollection<Department, Staff> implements MyCollectionContract<Department, Staff> {

    private Staff director;
    private Map<Department, List<Staff>> staff = new HashMap<>();

    @Override
    public void setDirector(Staff director) {
        this.director = director;
    }

    @Override
    public Staff getDirector() {
        return director;
    }

    @Override
    public void removeDirector() {
        director = null;
    }

    @Override
    public void addDepartment(Department department) {
        staff.put(department, new ArrayList<Staff>());

    }

    @Override
    public void removeDepartment(Department department) {
        staff.remove(department);

    }

    @Override
    public void addStaff(Staff staff, Department department) {
        this.staff.get(department).add(staff);

    }

    @Override
    public void removeStaff(Staff staff, Department department) {
        this.staff.get(department).remove(staff);

    }

    @Override
    public void moveStaff(Staff staff, Department departmentOut, Department departmentIn) {
        removeStaff(staff, departmentOut);
        addStaff(staff, departmentIn);

    }

    @NotNull
    @Override
    public Iterator<Staff> iterator() {
        return new Iterator<Staff>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Staff next() {
                return null;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Staff> action) {

    }

    @Override
    public Spliterator<Staff> spliterator() {
        return null;
    }
}