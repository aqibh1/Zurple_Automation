package com.zurple.resources.blocks;

import com.zurple.resources.forms.ReminderForm;
import com.zurple.resources.interfaces.HasHeader;
import org.openqa.selenium.By;

public class RemindersBlock
        extends resources.blocks.AbstractBlock implements HasHeader
{

    protected ReminderForm reminderForm;

    public String getHeader(){
        return block.findElement(By.xpath("./div[1]/h2")).getText();
    }

    public ReminderForm getReminderForm()
    {
        reminderForm = new ReminderForm();
        reminderForm.setForm(block.findElement(By.xpath("./descendant::form")));
        reminderForm.setDriver(getDriver());
        return reminderForm;
    }
}
