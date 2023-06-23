import com.Lee.ui.*;
import com.Lee.user.UserDAO;

public class GameApp {

    public static void main(String[] args) {
        // 程序的启动入口
        UserDAO.initFile();
        UserDAO.readInUsers();
        new LoginJFrame();
    }
}
