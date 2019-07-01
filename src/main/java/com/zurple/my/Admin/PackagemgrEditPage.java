package com.zurple.my.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.zurple.my.Page;
import com.zurple.my.resources.forms.PackageEditForm;

public class PackagemgrEditPage
        extends Page
{
    private PackageEditForm packageEditForm;
    private Integer packageId;

    public PackagemgrEditPage(){
        url = "/packagemgr/edit/package_id/";
    }

    public boolean checkPackageEditFormExists(){
        try{
            getPackageEditForm();
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public PackageEditForm getPackageEditForm(){
        packageEditForm = new PackageEditForm();
        packageEditForm.setForm(driver.findElement(By.xpath("//*[@id=\"common_container\"]/div/div/form")));
        packageEditForm.setDriver(driver);
        return packageEditForm;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getPackageId();
    }

}
