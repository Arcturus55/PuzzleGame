package com.Lee.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    private final int[] correct = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    private final int[][] correct_data = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    private final int[][] data = new int[4][4];
    private int x;
    private int y;
    private String path = "image/animal/animal5/";
    private int step = 0;
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLogInItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("退出游戏");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem girls = new JMenuItem("美女");
    JMenuItem sports = new JMenuItem("运动");

    // 构造函数
    public GameJFrame() {
        //初始化窗口
        initJFrame();
        // 初始化菜单栏
        initJMenuBar();
        // 初始化图片顺序的数据
        initData();
        // 初始化图片
        initImage();
        // 显示窗口
        this.setVisible(true);
    }

    private void initData() {
        int[] arr = Arrays.copyOf(correct, correct.length);
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int idx = r.nextInt(arr.length);
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();
        if (Arrays.deepEquals(data, correct_data)) {
            System.out.println("胜利！");
            JLabel win = new JLabel(new ImageIcon("image/win.png"));
            win.setBounds(203, 283, 197, 73);
            this.getContentPane().add(win);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jLabel = new JLabel(new ImageIcon(
                        path + data[i][j] + ".jpg"
                ));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);
        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);  // 取消默认的居中放置
        this.addKeyListener(this);
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu changeGraph = new JMenu("更换图片");
        JMenu functions = new JMenu("选项");
        functions.add(replayItem);
        functions.add(reLogInItem);
        functions.add(closeItem);
        changeGraph.add(girls);
        changeGraph.add(animals);
        changeGraph.add(sports);


        girls.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);
        replayItem.addActionListener(this);
        reLogInItem.addActionListener(this);
        closeItem.addActionListener(this);

        jMenuBar.add(functions);
        jMenuBar.add(changeGraph);


        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Arrays.deepEquals(data, correct_data)) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel totalGraph = new JLabel(new ImageIcon(path + "all.jpg"));
            totalGraph.setBounds(83, 134, 420, 420);
            this.getContentPane().add(totalGraph);
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (Arrays.deepEquals(data, correct_data)) {
            return;
        }
        int code = e.getKeyCode();
        // 左：37 上：38 右：39 下：40
        if (code == 37 && y < 3) {
            System.out.println("向左移动。");
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38 && x < 3) {
            System.out.println("向上移动。");
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39 && y > 0) {
            System.out.println("向右移动。");
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40 && x > 0) {
            System.out.println("向下移动。");
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 65) {
            System.out.println("显示完整图片。");
            initImage();
        } else if (code == 87) {
            System.out.println("一键胜利。");
            for(int i = 0; i < correct_data.length; i++) {
                data[i] = Arrays.copyOf(correct_data[i], correct_data[i].length);
            }
            x = 3;
            y = 3;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新开始游戏。");
            initData();
            step = 0;
            initImage();
        } else if (obj == reLogInItem) {
            System.out.println("重新登陆。");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏。");
            System.exit(0);
        } else if (obj == girls) {
            System.out.println("更换图片类型为美女。");
            Random rdGirl = new Random();
            int idx = rdGirl.nextInt(9) + 1;
            path = "image/girl/girl" + idx + "/";
            initData();
            initImage();
        } else if (obj == animals) {
            System.out.println("更换图片类型为动物。");
            Random rdGirl = new Random();
            int idx = rdGirl.nextInt(8) + 1;
            path = "image/animal/animal" + idx + "/";
            initData();
            initImage();
        } else if (obj == sports) {
            System.out.println("更换图片类型为运动。");
            Random rdGirl = new Random();
            int idx = rdGirl.nextInt(10) + 1;
            path = "image/sport/sport" + idx + "/";
            initData();
            initImage();
        }
    }

}
