package us.zengtest1.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchForm
        extends resources.forms.RegisterForm
{
    
    public void expand()
    {
        form.findElement(By.xpath("./descendant::*[@id=\"expand-search-fields\"]")).click();
    }
}
