package task2;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface StudentManager {
    List<Student> getStudents();

    void setStudents(List<Student> students);

    boolean addStudent(String format);

    Date getAvarageDate();

    GregorianCalendar getAvarageLifetime();
}
