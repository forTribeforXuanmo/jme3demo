package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;


/**
 * Created by Administrator on 2017-10-11.
 */
public class HelloJME3 extends SimpleApplication {
    public void simpleInitApp() {
        Mesh box=new Box(1,1,1);
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        Geometry geom=new Geometry("Box");
        geom.setMesh(box);
        geom.setMaterial(mat);
        DirectionalLight sun=new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        // #5 将方块和光源都添加到场景图中
        rootNode.attachChild(geom);
        rootNode.addLight(sun);
    }
    public static void main(String[] args) {
        // 启动jME3程序
        HelloJME3 app = new HelloJME3();
        app.start();
    }

    public void produceTriangle(){
        Vector3f[] v=new Vector3f[6];
        v[0] = new Vector3f(2.5f, 4f, 0f);
        v[1] = new Vector3f(1f, 3.26f, 0f);
        v[2] = new Vector3f(1f, 1.74f, 0f);
        v[3] = new Vector3f(2.5f, 1f, 0f);
        v[4] = new Vector3f(4f, 1.74f, 0f);
        v[5] = new Vector3f(4f, 3.26f, 0f);


    }
}
