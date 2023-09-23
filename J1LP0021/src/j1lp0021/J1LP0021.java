package j1lp0021;

import controller.Manager;
import view.MenuView;
import view.MenuModel;
import java.util.ArrayList;

public class J1LP0021 {

    public static void main(String[] args) {
        MenuModel model = new MenuModel();
        MenuView view = new MenuView(model);
        Manager manager = new Manager(model, view);
        manager.menu();
    }

}
