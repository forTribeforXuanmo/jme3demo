package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;

/**
 * Created by Administrator on 2017-10-13.
 *
 * 1.先隐藏鼠标，设置鼠标的图片为透明
 *
 */
public class FakeCursor2 extends SimpleApplication {
    public static void main(String[] args) {
        FakeCursor2 app=new FakeCursor2();
        app.start();
    }
    private Picture cursor;
    private Vector3f position=new Vector3f();
    private boolean isPressed=false;
    private float width;
    private float height;
    public void simpleInitApp() {
        width=cam.getWidth();
        height=cam.getHeight();
        flyCam.setDragToRotate(true);

        hideCursor();

        cursor=fakeCursor();
        guiNode.attachChild(cursor);
        inputManager.addRawInputListener(inputListener);
    }
    private RawInputListener inputListener=new RawInputListener() {
        private float x;
        private float y;
        public void beginInput() {

        }

        public void endInput() {

        }

        public void onJoyAxisEvent(JoyAxisEvent evt) {

        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {

        }

        public void onMouseMotionEvent(MouseMotionEvent evt) {
            if(isPressed){
                return;
            }
            x=evt.getX();
            y=evt.getY();

            x= FastMath.clamp(x,0,cam.getWidth());
            y=FastMath.clamp(y,0,cam.getHeight());

            position.x=x;
            position.y=y-32;

            cursor.setLocalTranslation(position);

        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {

        }

        public void onKeyEvent(KeyInputEvent evt) {

        }

        public void onTouchEvent(TouchEvent evt) {

        }
    };

    private Picture fakeCursor() {
        Picture cursor=new Picture("cur");
        cursor.setWidth(32);
        cursor.setHeight(32);
        cursor.setImage(assetManager,"Interface/Gui/Cursor/MyCursor.tga",true);

        position=new Vector3f(width/2,height/2-32,Float.MAX_VALUE);
        cursor.setLocalTranslation(position);
        return cursor;
    }

    private void hideCursor() {
        JmeCursor jmeCursor= (JmeCursor) assetManager.loadAsset("Interface/Gui/Cursor/invisible.cur");
        inputManager.setMouseCursor(jmeCursor);
    }
}
