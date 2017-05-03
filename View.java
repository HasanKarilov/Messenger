package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hanaria on 5/3/17.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    // это будет панель с двумя вкладками.
    private JTabbedPane tabbedPane = new JTabbedPane();
    // это будет компонент для визуального редактирования html.
    private JTextPane htmlTextPane = new JTextPane();
    // это будет компонент для редактирования html в виде текста, он будет отображать код html (теги и их содержимое).
    private JEditorPane plainTextPane =  new JEditorPane();
    private FrameListener frameListener;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void init(){
        initGui();
        frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);

    }
    public void exit(){
        this.controller.exit();
    }

    public void initMenuBar(){

    }
    public void initEditor(){

    }
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
}
