package com.zurple.my;

import com.zurple.my.resources.blocks.MassEmailSuccessfullySentAlertBlock;
import com.zurple.my.resources.forms.MassEmailForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class MarketingMessemailPage
        extends Page
{

    private MassEmailForm massEmailForm;
    private MassEmailSuccessfullySentAlertBlock massEmailSuccessfullySentAlertBlock;

    public MarketingMessemailPage(){
        url = "/marketing/massemail";
    }

    public boolean checkMassEmailFormExists(){
        try{
            getMassEmailForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public boolean checkMassEmailSuccessfullySentAlertBlockExists(){
        try{
            getMassEmailSuccessfullySentAlertBlock();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public MassEmailForm getMassEmailForm(){
        massEmailForm = new MassEmailForm();
        massEmailForm.setDriver(driver);
        massEmailForm.setForm(driver.findElement(By.xpath("//*[@id=\"form\"]")));
        massEmailForm.setSubmitButton(driver.findElement(By.xpath("//*[@id=\"send_button\"]")));
        return massEmailForm;
    }

    public MassEmailSuccessfullySentAlertBlock getMassEmailSuccessfullySentAlertBlock(){
        MassEmailSuccessfullySentAlertBlock massEmailSuccessfullySentAlertBlock = new MassEmailSuccessfullySentAlertBlock();
        return massEmailSuccessfullySentAlertBlock;
    }
}
