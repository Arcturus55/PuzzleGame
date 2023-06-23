package com.Lee.user;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO {
    public static ArrayList<User> users = new ArrayList<>();
    private static final String userFileDir = "userDataBase/userInfo.txt";

    public static boolean findUser(String name, String code) {
        for(User user : users) {
            if(user.getName().equals(name) && user.getPassword().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static void initFile() {
        File file = new File(userFileDir);
        if(!file.exists()) {
            try {
                boolean valid = file.createNewFile();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addUser(String name, String code)  {
        User userAdded = new User(name, code);
        users.add(userAdded);
        try {
            File file = new File(userFileDir);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());
            String info = name + " " + code + "\n";
            raf.writeBytes(info);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readInUsers() {
        users = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileInputStream(userFileDir));
            String info;
            while(input.hasNextLine()) {
                info = input.nextLine();
                String[] infos = info.split(" ");
                users.add(new User(infos[0], infos[1]));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
