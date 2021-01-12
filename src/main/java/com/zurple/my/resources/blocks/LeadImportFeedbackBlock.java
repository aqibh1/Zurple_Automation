package com.zurple.my.resources.blocks;

public class LeadImportFeedbackBlock
        extends resources.blocks.AbstractBlock
{

    private String message="";

    public String getMessage(){
        message = block.getText();
        return message;
    }

    public Boolean isVisible(){
        return block.isDisplayed();
    }


}
