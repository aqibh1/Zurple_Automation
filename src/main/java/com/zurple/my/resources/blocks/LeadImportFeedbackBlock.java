package com.zurple.my.resources.blocks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import resources.classes.Lead;

public class LeadImportFeedbackBlock
        extends resources.blocks.AbstractBlock
{

    private String message="";

    public String getMessage(){
        message = block.getText();
        return message;
    }

    public Boolean isVisible(){
        return block.isDisplayed();
    }


}
