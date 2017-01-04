package com.zurple.resources.blocks;

import com.zurple.resources.interfaces.HasHeader;
import org.openqa.selenium.By;

public class RemindersBlock
        extends resources.blocks.AbstractBlock implements HasHeader
{
    public String getHeader(){
        return block.findElement(By.xpath("./div[1]/h2")).getText();
    }
}
