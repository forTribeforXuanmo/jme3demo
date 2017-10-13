package com.lsz;

import com.jme3.app.SimpleApplication;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

/**
 * Created by Administrator on 2017-10-13.
 */
public class HelloLemur2 extends SimpleApplication {
    public static void main(String[] args) {
        HelloLemur2 app=new HelloLemur2();
        app.start();
    }
    public void simpleInitApp() {

        GuiGlobals.initialize(this);

        BaseStyles.loadGlassStyle();

        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        Container myWindow=new Container();
        guiNode.attachChild(myWindow);
    }
}
