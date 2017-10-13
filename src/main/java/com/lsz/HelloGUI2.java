package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

/**
 * Created by Administrator on 2017-10-13.
 */
public class HelloGUI2 extends SimpleApplication{
    public static void main(String[] args) {
        HelloGUI2 app=new HelloGUI2();
        app.start();
    }
    public void simpleInitApp() {
        flyCam.setMoveSpeed(3);
        viewPort.setBackgroundColor(ColorRGBA.Brown);
        creatArrow(new Vector3f(500,0,0),ColorRGBA.Yellow);
        creatArrow(new Vector3f(0,500,0),ColorRGBA.Red);
        creatArrow(new Vector3f(0,0,500),ColorRGBA.Blue);
        //平行投影，直接帖子相机上
        Spatial pic=getPicture();
        guiNode.attachChild(pic);
        //透视投影
        //放入模型
        addAsh();

        //添加光源
        addLight();

    }

    private void addLight() {
        DirectionalLight sun=new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        AmbientLight ambientLight=new AmbientLight();
        ColorRGBA lightColor=new ColorRGBA();
        sun.setColor(lightColor.mult(0.6f));
        ambientLight.setColor(lightColor.mult(0.4f));
        rootNode.addLight(sun);
        rootNode.addLight(ambientLight);
    }

    public void addAsh() {
        Spatial model=assetManager.loadModel("Models/Ashe/b_ashe_b.obj");
        model.scale(0.03f);
        model.center();
        rootNode.attachChild(model);
    }

    public void creatArrow(Vector3f vec3f, ColorRGBA color){
        Material mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color",color);
        Geometry geom=new Geometry("arrow",new Arrow(vec3f));
        geom.setMaterial(mat);
        guiNode.attachChild(geom);
    }

    public Spatial getPicture(){
        float width=cam.getWidth();
        float height=cam.getHeight();

        Quad quad=new Quad(width,height);
        Geometry geom=new Geometry("picture",quad);
        geom.setLocalTranslation(0,0,-1);
        Texture tex=assetManager.loadTexture("Interface/Gui/pic_with_alpha.png");
        Material mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",tex);
        geom.setMaterial(mat);
        return geom;
    }
}
