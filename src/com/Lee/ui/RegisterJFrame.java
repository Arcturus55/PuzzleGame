package com.Lee.ui;

import com.Lee.user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.Lee.user.UserDAO.users;
import static com.Lee.utils.Utils.showJDialog;
import static com.Lee.user.UserDAO.addUser;

public class RegisterJFrame extends JFrame implements MouseListener {
    JButton registerButton = new JButton();
    JButton resetButton = new JButton();

    JTextField nameIn = new JTextField();
    JPasswordField codeIn = new JPasswordField();
    JPasswordField codeInAgain = new JPasswordField();


    public RegisterJFrame() {
        initJFrame();

        initImage();
        this.setVisible(true);
    }


    private void initImage() {
        this.getContentPane().removeAll();
        JLabel userName = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        userName.setBounds(60, 150, 79, 17);
        this.getContentPane().add(userName);

        nameIn.setBounds(160, 150, 200, 30);
        this.getContentPane().add(nameIn);

        JLabel userCode = new JLabel(new ImageIcon("image/register/注册密码.png"));
        userCode.setBounds(60, 200, 64, 16);
        this.getContentPane().add(userCode);

        codeIn.setBounds(160, 200, 200, 30);
        this.getContentPane().add(codeIn);

        JLabel confirmCode = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        confirmCode.setBounds(60, 250, 96, 17);
        this.getContentPane().add(confirmCode);

        codeInAgain.setBounds(160, 250, 200, 30);
        this.getContentPane().add(codeInAgain);

        resetButton.setBounds(90, 300, 128, 47);
        resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.addMouseListener(this);
        this.getContentPane().add(resetButton);

        registerButton.setBounds(260, 300, 128, 47);
        registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        JLabel background = new JLabel(new ImageIcon("image/register/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版-注册界面");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object button = e.getSource();
        if (button == resetButton) {
            resetButton.setIcon(new ImageIcon("image/register/重置按下.png"));
            this.getContentPane().repaint();
        } else if (button == registerButton) {
            registerButton.setIcon(new ImageIcon("image/register/注册按下.png"));
            this.getContentPane().repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object button = e.getSource();
        if (button == resetButton) {
            resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));
            this.getContentPane().repaint();
            nameIn.setText("");
            codeIn.setText("");
            codeInAgain.setText("");
        } else if (button == registerButton) {
            registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
            this.getContentPane().repaint();
            String nameGet = nameIn.getText();
            String codeGet = new String(codeIn.getPassword());
            String codeGetAgain = new String(codeInAgain.getPassword());
            if (nameGet.equals("") || codeGet.equals("") || codeGetAgain.equals("")) {
                showJDialog("用户名、密码和再次输入的密码不得为空！");
            } else {
                if (!codeGet.equals(codeGetAgain)) {
                    showJDialog("两次密码输入的不同！");
                } else {
                    for(User user : users) {
                        if(user.getName().equals(nameGet)) {
                            showJDialog("用户名已存在！");
                            System.out.println(users);
                            return;
                        }
                    }
                    System.out.println("添加新用户。");
                    addUser(nameGet, codeGet);
                    this.setVisible(false);
                    new LoginJFrame();
                }
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
