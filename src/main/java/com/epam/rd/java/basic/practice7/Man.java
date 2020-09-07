package com.epam.rd.java.basic.practice7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "surname",
    "age",
    "money"
})
@XmlRootElement(name = "com.epam.rd.java.basic.practice7.Man",
        namespace = "https://rep.lab.epam.com/api_client/tytykvou-task7")
public class Man {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Surname", required = true)
    protected String surname;
    @XmlElement(name = "Age")
    protected long age;
    @XmlElement(name = "Money", required = true)
    protected Money money;

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
