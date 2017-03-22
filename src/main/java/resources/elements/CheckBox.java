package resources.elements;

import org.openqa.selenium.WebElement;

public class CheckBox
{

    private String label;
    private Boolean value;
    private WebElement element;

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Boolean getValue()
    {
        return this.element.isSelected();
    }

    public void setValue(Boolean value)
    {
        this.value = value;
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
