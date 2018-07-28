package com.zurple.my.Admin;

import com.zurple.my.Page;
import com.zurple.my.resources.forms.AdminEditForm;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AdminmgrNewPage
        extends Page
{

    private AdminEditForm adminCreateForm;

    public AdminmgrNewPage(){
        url = "/adminmgr/new";
    }

    public boolean checkAdminCreateFormExists(){
        try{
            getAdminCreateForm();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public AdminEditForm getAdminCreateForm(){
        adminCreateForm = new AdminEditForm();
        adminCreateForm.setForm(driver.findElement(By.xpath("//*[@id=\"common_container\"]/div/div/form")));
        adminCreateForm.setDriver(driver);
        return adminCreateForm;
    }
}
