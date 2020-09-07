package com.epam.rd.java.basic.practice7;

import java.util.ArrayList;
import java.util.List;

public class People {

    private List<Man> manList;

    public People() {
        this.manList = new ArrayList<>();
    }

    public List<Man> getManList() {
        return manList;
    }

    public void setManList(List<Man> manList) {
        this.manList = manList;
    }
}
