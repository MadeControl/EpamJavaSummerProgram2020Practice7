package com.epam.rd.java.basic.practice7;

public class Man {

    private String name;
    private String surname;
    private long age;
    private Money money;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String value) {
        this.surname = value;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long value) {
        this.age = value;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money value) {
        this.money = value;
    }
}
