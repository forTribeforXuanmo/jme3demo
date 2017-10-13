package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;
import truetypefont.TrueTypeLoader;

/**
 * Created by Administrator on 2017-10-13.
 */
public class HelloTTF2 extends SimpleApplication {
    public static void main(String[] args) {
        HelloTTF2 APP=new HelloTTF2();
        APP.start();
    }
    private final int FONT_SIZE=64;
    public void simpleInitApp() {
        //注册ttf字体资源加载器
        assetManager.registerLoader(TrueTypeLoader.class,"ttf");
        //创建字体
        TrueTypeKey ttk=new TrueTypeKey("Interface/Fonts/SIMKAI.TTF",0,FONT_SIZE);
        TrueTypeFont font= (TrueTypeFont) assetManager.loadAsset(ttk);
        String[] poem={"12345","上山打老虎"};
        float x=0.5f*(cam.getWidth()-FONT_SIZE*5);
        float y=0.5f*(cam.getHeight()+FONT_SIZE*2);
        for (int i = 0; i <poem.length ; i++) {
            Geometry text=font.getBitmapGeom(poem[i],0, ColorRGBA.Red);
            text.setLocalTranslation(x,y-i*FONT_SIZE,0);
            System.out.println("x:"+x+"  y:"+(y-i*FONT_SIZE));
            guiNode.attachChild(text);
        }

    }
}
