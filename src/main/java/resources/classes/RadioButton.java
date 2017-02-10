package resources.classes;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RadioButton
{

    private WebElement element;
    private String label;

    public WebElement getElement()
    {
        return element;
    }

    public void setElement(WebElement element)
    {
        this.element = element;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

}
