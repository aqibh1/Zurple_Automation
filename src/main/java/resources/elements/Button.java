package resources.elements;

import org.openqa.selenium.WebElement;

public class Button
{

    private WebElement element;

    public String getLabel()
    {
        return element.getText();
    }

    public WebElement getElement()
    {
        return element;
    }

    public void setElement(WebElement element)
    {
        this.element = element;
    }
}
