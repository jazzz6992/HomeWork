package l19;

import org.jetbrains.annotations.NotNull;

import java.util.*;

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
        return new MyCollectionIterator();
    }

    public class MyCollectionIterator implements Iterator<Staff> {
        private Iterator<Map.Entry<Department, List<Staff>>> entryIterator;
        private Iterator<Staff> staffIterator = null;
        private Map.Entry<Department, List<Staff>> currentEntry = null;

        public MyCollectionIterator() {
            entryIterator = staff.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            if (staffIterator != null) {
                if (staffIterator.hasNext()) {
                    return true;
                }
            }
            if (entryIterator.hasNext()) {
                currentEntry = entryIterator.next();
                staffIterator = currentEntry.getValue().iterator();
                return hasNext();
            } else {
                return false;
            }
        }

        @Override
        public Staff next() {
            return staffIterator.next();
        }

        @Override
        public void remove() {
            staffIterator.remove();
        }
    }

    public static void main(String[] args) {
        MyCollection<String, Integer> myCollection = new MyCollection<>();
        for (int i = 0; i < 4; i++) {
            String dep = "dep" + i + 1;
            myCollection.addDepartment(dep);
            for (int j = 0; j < 4; j++) {
                myCollection.addStaff(Integer.valueOf(String.valueOf(i + 1) + String.valueOf(j + 1)), dep);
            }
        }
        Iterator<Integer> iterator = myCollection.iterator();
        if (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        if (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }

        System.out.println();
        for (Integer i :
                myCollection) {
            System.out.println(i);
        }
    }
}