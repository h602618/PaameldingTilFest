package no.dat108.oblig4.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    private Integer phone;
    private String first_name;
    private String last_name;
    private String password;
    private String gender;


    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return String.format("%s %s", first_name, last_name);
    }

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
