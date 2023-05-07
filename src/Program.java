import java.io.IOException;
import java.util.Scanner;

public class Program {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        System.out.print("\n1. Admin \n2. User\nSistemə necə daxil olmaq istəyirsiniz? ");

        int userType = Methods.getNum();
        System.out.println(userType);

        switch (userType){
            case 1:{
                Admin.loadAdminGate();
                break;
            }
            case 2:{
                User.loadUserUI();
                break;
            }
            case 0: return;
            default:main(null);
        }

    }
}
