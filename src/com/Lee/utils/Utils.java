package com.Lee.utils;

import javax.swing.*;
import java.util.Random;

public class Utils {
    public static void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }
    public static String getCode() {
        StringBuilder s = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < 5; i++) {
            int type = rd.nextInt(3);
            char ch = ' ';
            switch (type) {
                case 0 -> ch = (char) (rd.nextInt(26) + 65);
                case 1 -> ch = (char) (rd.nextInt(26) + 97);
                case 2 -> ch = (char) (rd.nextInt(10) + 48);
            }
            s.append(ch);
        }
        return s.toString();
    }
}
