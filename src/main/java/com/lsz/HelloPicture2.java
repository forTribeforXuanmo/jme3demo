package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.shadow.PointLightShadowFilter;
import com.jme3.shadow.PointLightShadowRenderer;
import com.jme3.ui.Picture;

/**
 * Created by Administrator on 2017-10-13.
 */
public class HelloPicture2 extends SimpleApplication {
    PointLight lamp_light;
    public static void main(String[] args) {
        HelloPicture2 app=new HelloPicture2();
        app.start();
    }

    public void simpleInitApp() {
        // 初始化摄像机位置
        cam.setLocation(new Vector3f(9.443982f, 13.542627f, 8.93058f));
        cam.setRotation(new Quaternion(-0.015316938f, 0.9377411f, -0.34448296f, -0.041695934f));

        flyCam.setMoveSpeed(10);

        //
        addObjects();
        addLight();
        addPicture();
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
    }

    private void addPicture() {
        Picture pic=new Picture("picture");
        pic.setImage(assetManager, "Interface/Gui/pic_with_alpha.png",true);
        pic.setHeight(cam.getHeight());
        pic.setWidth(cam.getWidth());
        pic.setLocalTranslation(0,0,-1);
        guiNode.attachChild(pic);
    }

    @Override
    public void simpleUpdate(float tpf) {
        lamp_light.setPosition(cam.getDirection());
        Spatial cube = rootNode.getChild("cube");
        cube.rotate(0,FastMath.PI*tpf,0);
        Vector3f localTranslation = cube.getLocalTranslation();
        //cube.move(localTranslation.getX()+0.5f,localTranslation.getY(),localTranslation.getZ());
        //cube.forceRefresh(true,true,true);
    }

    private void addLight() {
        DirectionalLight sun=new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        sun.setColor(ColorRGBA.White.mult(0.8f));

        AmbientLight ambientLight=new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(0.5f));


        lamp_light = new PointLight();
        lamp_light.setColor(ColorRGBA.White.mult(1.5f));
        lamp_light.setRadius(15f);
        //lamp_light.setPosition(cam.getDirection());
        rootNode.addLight(lamp_light);
        //产生阴影
        final int SHADOWMAP_SIZE=64;
        PointLightShadowFilter dlsf = new PointLightShadowFilter(assetManager, SHADOWMAP_SIZE);
        dlsf.setLight(lamp_light);
        dlsf.setEnabled(true);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(dlsf);
        fpp.setNumSamples(4);  //4被抗锯齿
        viewPort.addProcessor(fpp);
       // rootNode.addLight(sun);
        rootNode.addLight(ambientLight);

    }

    private void addObjects(){
        Material mat=new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");

        Geometry geom=new Geometry("Floor",new Quad(100,100));

        geom.setMaterial(mat);
        geom.setShadowMode(RenderQueue.ShadowMode.Receive);//承载阴影
        geom.rotate(-FastMath.HALF_PI,0,0);

        rootNode.attachChild(geom);

        for(int y=0;y<10;y+=3){
            for(int x=0;x<10;x+=3){
            geom=new Geometry("cube",new Box(0.5f,0.5f,0.5f));
            geom.setMaterial(mat);
            geom.setShadowMode(RenderQueue.ShadowMode.Cast);
            geom.move(x+4,0.5f,-y-4);
            rootNode.attachChild(geom);
            }
        }
    }

}
