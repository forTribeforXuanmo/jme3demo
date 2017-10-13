package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;

/**
 * Created by Administrator on 2017-10-12.
 */
public class HelloNode extends SimpleApplication{
    private Spatial spatial;

    @Override
    public void simpleUpdate(float tpf) {
        if(spatial!=null){
            spatial.rotate(0, FastMath.PI*tpf,FastMath.PI/3*tpf);
        }
    }

    public void simpleInitApp() {
        Geometry geomA=new Geometry("红色球",new Sphere(16,24,1));
        geomA.setMaterial(newLightingMaterial(ColorRGBA.Red));
        Geometry geomB=new Geometry("青色球",new Sphere(16,24,1));
        geomB.setMaterial(newLightingMaterial(ColorRGBA.Cyan));
        Geometry geomC=new Geometry("黄色球",new Sphere(16,24,1));
        geomC.setMaterial(newLightingMaterial(ColorRGBA.Yellow));
        Node node=new Node();
        node.attachChild(geomA);
        node.attachChild(geomB);
        node.attachChild(geomC);
        node.scale(0.5f);
        geomA.setLocalTranslation(-3,3,0);
        geomB.setLocalTranslation(3,3,0);
        geomC.setLocalTranslation(0,-3,3);
        rootNode.attachChild(node);
        addLight();
        this.spatial=node;
    }

    /**
     * 光
     */
    private void addLight() {
        //用定向光
        DirectionalLight sun=new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        //环境光
        AmbientLight ambient=new AmbientLight();
        ColorRGBA lightColor=new ColorRGBA();
        sun.setColor(lightColor.mult(0.8f));
        ambient.setColor(lightColor.mult(0.2f));

        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }

    /**
     * 创建感光材质
     * @param color
     * @return
     */
    private Material newLightingMaterial(ColorRGBA color) {
        Material mat=new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse",color);
        mat.setColor("Ambient",color);
        mat.setColor("Specular",ColorRGBA.White);
        mat.setFloat("Shininess",24);
        mat.setBoolean("UseMaterialColors",true);
        return mat;
    }

    public static void main(String[] args) {
        HelloNode app=new HelloNode();
        app.start();
    }
}






