package resources.classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

public class SearchResult
{

    private Integer id;
    private String title;
    private String img_link;
    private String url;
    private WebElement element;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImgLink()
    {
        return img_link;
    }

    public void setImgLink(String img_link)
    {
        this.img_link = img_link;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
        Pattern pattern = Pattern.compile("(\\d*)$");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            setId(Integer.parseInt(matcher.group(1)));
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public WebElement getElement()
    {
        return element;
    }

    public void setElement(WebElement element)
    {
        this.element = element;
    }

	public void setOnlyUrl(String pUrl) {
		this.url = pUrl;
		
	}

}
