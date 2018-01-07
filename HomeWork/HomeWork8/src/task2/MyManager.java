package task2;

import java.io.*;
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

    public boolean removeStudent(Student s) {
        return students != null && students.remove(s);
    }

    @Override
    public boolean saveStudents(File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(students);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean loadStudents(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            List<Student> students = (List<Student>) ois.readObject();
            ois.close();
            setStudents(students);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
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
