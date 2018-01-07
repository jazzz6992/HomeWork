package task1.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Human {
    private int id;
    private int age;
    private boolean isDegree;
    @JsonProperty(value = "name")
    private String firstName;
    private String surname;

    public Human() {
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", age=" + age +
                ", isDegree=" + isDegree +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (id != human.id) return false;
        if (age != human.age) return false;
        if (isDegree != human.isDegree) return false;
        if (firstName != null ? !firstName.equals(human.firstName) : human.firstName != null) return false;
        return surname != null ? surname.equals(human.surname) : human.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        result = 31 * result + (isDegree ? 1 : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDegree() {
        return isDegree;
    }

    @JsonProperty(value = "isDegree")
    public void setDegree(boolean isDegree) {
        this.isDegree = isDegree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
