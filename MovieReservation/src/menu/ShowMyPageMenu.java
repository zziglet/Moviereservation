package menu;

public class ShowMyPageMenu implements ShowMenu{

    int num = Main.sc.nextInt();

    @Override
    public void showMenu() {
        num = Main.sc.nextInt();
    }
}
