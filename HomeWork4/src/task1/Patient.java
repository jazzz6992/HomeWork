package task1;

public class Patient {
    public enum BloodType {
        FIRST_PLUS, FIRST_MINUS, SECOND_PLUS, SECOND_MINUS, THIRD_PLUS, THIRD_MINUS, FOURTH_PLUS, FOURTH_MINUS
    }

    private String fullName;
    private BloodType bloodType;
    private int age;
    private boolean sex;
    private String diagnosis;

    public Patient(String fullName, BloodType bloodType, int age, boolean sex, String diagnosis) {
        this.fullName = fullName;
        this.bloodType = bloodType;
        this.age = age;
        this.sex = sex;
        this.diagnosis = diagnosis;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Пациент " + fullName + " - Возраст = " + age;
    }
}
