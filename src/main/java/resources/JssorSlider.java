package resources;

import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class JssorSlider
{

    protected WebElement slider;

    public void setSlider(WebElement object){
        slider = object;
    }

    public int getCurrentActiveSlide(){

        try{
            int index = 0;

            List<WebElement> allSlides = slider.findElements(
                    By.xpath("//*[@id=\"slider_container\"]/div/div/div[3]/div"));

            for (WebElement slide: allSlides) {
                if(
                    slide.getAttribute("style").contains("left: 0px") &&
                    !slide.getAttribute("style").contains("display: none")
                ){
                    return index;
                }

                index++;
            }
            throw new Exception("Can't find active slide");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }

    }

}
