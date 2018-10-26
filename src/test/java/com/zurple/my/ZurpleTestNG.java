package com.zurple.my;

import org.testng.TestNG;

public class ZurpleTestNG extends TestNG {

    public String getSecondLevelTestTitle() {
        return secondLevelTestTitle;
    }

    public void setSecondLevelTestTitle(String secondLevelTestTitle) {
        this.secondLevelTestTitle = secondLevelTestTitle;
    }

    private String secondLevelTestTitle;

}
