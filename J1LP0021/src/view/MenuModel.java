package view;




public class MenuModel {
    private String[] menuItems = {
        "Create",
        "Find and Sort",
        "Update/Delete",
        "Report",
        "Exit"
    };

    private int selectedMenuItem;

    public String[] getMenuItems() {
        return menuItems;
    }

    public void setSelectedMenuItem(int selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
    }

    public int getSelectedMenuItem() {
        return selectedMenuItem;
    }
}
