package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.forms.AdminEditForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AdminmgrEditPage
        extends Page
{

    private AdminEditForm adminEditForm;

    public boolean checkAdminEditFormExists(){
        try{
            getAdminEditForm();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public AdminEditForm getAdminEditForm(){
        adminEditForm = new AdminEditForm();
        adminEditForm.setForm(driver.findElement(By.xpath("//*[@id=\"common_container\"]/div/div/form")));
        adminEditForm.setDriver(driver);
        return adminEditForm;
    }

}
