/**
 * Created by xuch on 2015/12/29.
 */
import com.sun.javafx.collections.IntegerArraySyncer;
import com.sun.javafx.embed.HostDragStartListener;
import com.sun.javafx.runtime.SystemProperties;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Counter1 extends Applet {
    private int count = 0;
    private Button onOff = new Button("Toggle"), start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go() {
        while (true) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            if (runFlag) t.setText(Integer.toString(count++));
        }
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        Counter1 applet = new Counter1();
        Frame frame = new Frame("Counter1");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(applet, BorderLayout.CENTER);
        frame.setSize(300, 200);
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
}
