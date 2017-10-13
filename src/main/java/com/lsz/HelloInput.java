package com.lsz;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.input.event.*;
import com.jme3.util.SortUtil;

/**
 * Created by Administrator on 2017-10-12.
 */
public class HelloInput extends SimpleApplication {
    //jme消息为字符串
    private  final String FIRE="fire";
    public void simpleInitApp() {
        // 检测输入设备
        System.out.printf("Mouse: %b\nKeyboard: %b\nJoystick: %b\nTouch: %b\n",
                mouseInput != null, keyInput != null, joyInput != null, touchInput != null);
        //绑定消息与事件
//        inputManager.addMapping(FIRE, new KeyTrigger(KeyInput.KEY_SPACE),new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//        //绑定消息与监听器，消息一旦发出来则调用监听器的处理代码
//        inputManager.addListener(new MyActionListen(),FIRE);
        inputManager.addRawInputListener(new MyRawInputListen());
    }
    class MyActionListen implements ActionListener{
        private int i=0;
        public void onAction(String name, boolean isPressed, float tpf) {
            if(FIRE.equals(name)&&isPressed){
                System.out.println("开火射击bang----"+(i++));
            }
        }
    }
    class MyRawInputListen implements RawInputListener{

        public void beginInput() {

        }

        public void endInput() {

        }

        public void onJoyAxisEvent(JoyAxisEvent evt) {

        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {

        }

        public void onMouseMotionEvent(MouseMotionEvent evt) {
            System.out.println(evt.getX()+":"+evt.getY());
        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            if(evt.isPressed()&&evt.getButtonIndex()==MouseInput.BUTTON_LEFT){
                System.out.println("按下左键");
            }

        }

        public void onKeyEvent(KeyInputEvent evt) {
            if(evt.isPressed()&&evt.getKeyCode()==KeyInput.KEY_SPACE){
                System.out.println("按下空格键");
            }
        }

        public void onTouchEvent(TouchEvent evt) {

        }
    }
    public static void main(String[] args) {
        HelloInput app=new HelloInput();
        app.start();
    }
}
