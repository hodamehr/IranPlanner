package com.iranplanner.tourism.iranplanner.adapter;

/**
 * Created by h.vahidimehr on 29/05/2017.
 */

public class happen {
    private OnClickArrow monClickArrow;

    public void setOnClickArrow(OnClickArrow onClickArrow){
        this.monClickArrow=onClickArrow;
    }

    private void reportSTChange(){
        if (monClickArrow!=null){
            monClickArrow.click();
        }
    }
}
