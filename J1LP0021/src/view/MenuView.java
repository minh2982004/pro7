package view;




import java.util.Scanner;

public class MenuView {
    private MenuModel model;
    private Scanner scanner;

    public MenuView(MenuModel model) {
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    public void Menu() {
        String[] menuItems = model.getMenuItems();

        
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    public int UserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}
