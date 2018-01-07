package task1.entity;

import java.util.Date;
import java.util.List;

public class Root {
    private String name;
    private Gender gender;
    private Date date;
    private List<Human> people;

    public Root() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Human> getPeople() {
        return people;
    }

    public void setPeople(List<Human> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Root root = (Root) o;

        if (name != null ? !name.equals(root.name) : root.name != null) return false;
        if (gender != root.gender) return false;
        if (date != null ? !date.equals(root.date) : root.date != null) return false;
        return people != null ? people.equals(root.people) : root.people == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", date=" + date +
                ", people=" + people +
                '}';
    }
}
