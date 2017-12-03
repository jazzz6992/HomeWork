package task2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyManager implements StudentManager {
    private List<Student> students;

    public MyManager() {
        students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean addStudent(String format) {
        String firstName;
        String lastName;
        Date birthday;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            firstName = ConsoleHelper.getFirstName();
            lastName = ConsoleHelper.getLastName();
            birthday = sdf.parse(ConsoleHelper.getBirthDate(format));
            students.add(new Student(firstName, lastName, birthday));
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода");
            return false;
        } catch (ParseException e) {
            System.out.println("Скорее всего, вы указали дату в неправильном формате");
            return false;
        }
    }

    public Date getAvarageDate() {
        if (students.size() > 0) {
            long avarageTimeMillis = 0;
            for (Student s :
                    students) {
                avarageTimeMillis += s.getBirthday().getTime();
            }
            avarageTimeMillis /= students.size();
            return new Date(avarageTimeMillis);
        } else {
            return null;
        }
    }

    public GregorianCalendar getAvarageLifetime() {
        Date av = getAvarageDate();
        Date d = new Date(new Date().getTime() - av.getTime());
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
        c.setTime(d);
        c.add(GregorianCalendar.YEAR, -1970);
        return c;
    }
}
