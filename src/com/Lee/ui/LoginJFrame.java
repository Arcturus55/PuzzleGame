package com.Lee.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.Lee.user.UserDAO.findUser;
import static com.Lee.utils.Utils.getCode;
import static com.Lee.utils.Utils.showJDialog;

public class LoginJFrame extends JFrame implements MouseListener {

    JButton loginButton = new JButton();
    JButton registerButton = new JButton();

    JTextField nameIn = new JTextField();
    JPasswordField codeIn = new JPasswordField();
    JTextField confirmIn = new JTextField();

    String confirmText = getCode();
    JLabel confirmShow = new JLabel(confirmText);

    public LoginJFrame() {
        initJFrame();

        initImage();

        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版-登录界面");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initImage() {
        this.getContentPane().removeAll();
        JLabel userName = new JLabel(new ImageIcon("image/login/用户名.png"));
        userName.setBounds(80, 150, 47, 17);
        this.getContentPane().add(userName);

        nameIn.setBounds(160, 150, 200, 30);
        this.getContentPane().add(nameIn);

        JLabel userCode = new JLabel(new ImageIcon("image/login/密码.png"));
        userCode.setBounds(80, 200, 32, 16);
        this.getContentPane().add(userCode);

        codeIn.setBounds(160, 200, 200, 30);
        this.getContentPane().add(codeIn);

        JLabel confirmCode = new JLabel(new ImageIcon("image/login/验证码.png"));
        confirmCode.setBounds(80, 250, 56, 21);
        this.getContentPane().add(confirmCode);

        confirmIn.setBounds(160, 250, 100, 30);
        this.getContentPane().add(confirmIn);

        confirmShow.setBounds(280, 250, 100, 20);
        confirmShow.addMouseListener(this);
        this.getContentPane().add(confirmShow);

        loginButton.setBounds(90, 300, 128, 47);
        loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(this);
        this.getContentPane().add(loginButton);

        registerButton.setBounds(260, 300, 128, 47);
        registerButton.setIcon(new ImageIcon("image/login/注册按钮.png"));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object label = e.getSource();
        if (label == confirmShow) {
            confirmText = getCode();
            confirmShow = new JLabel(confirmText);
            initImage();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object button = e.getSource();
        if (button == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/登录按下.png"));
            this.getContentPane().repaint();
        } else if (button == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/注册按下.png"));
            this.getContentPane().repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object button = e.getSource();
        if (button == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
            this.getContentPane().repaint();
            String nameGet = nameIn.getText();
            String codeGet = new String(codeIn.getPassword());
            String confirmGet = confirmIn.getText();
            if (!confirmGet.equals(confirmText)) {
                showJDialog("验证码错误！");
            } else {
                if(nameGet.equals("") || codeGet.equals("")) {
                    showJDialog("用户名及密码不能为空！");
                } else {
                    if (findUser(nameGet, codeGet)) {
                        System.out.println("登陆成功。");
                        this.setVisible(false);
                        new GameJFrame();
                    } else {
                        showJDialog("用户名或密码错误！");
                    }
                }
            }
        } else if (button == registerButton) {
            registerButton.setIcon(new ImageIcon("image/login/注册按钮.png"));
            this.getContentPane().repaint();
            this.setVisible(false);
            new RegisterJFrame();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

