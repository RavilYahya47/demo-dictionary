import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Methods {
    static Scanner sc = new Scanner(System.in);
    public static int getNum(){

        boolean isTrue  = false;
        int num = Integer.MIN_VALUE;

        while (!isTrue){
            try {
                num = Integer.parseInt(sc.nextLine());
                isTrue = true;
            }catch (Exception e){
                System.out.println("Ededi duzgun formada daxil edin!");
            }
        }

        return num;
    }
    public static int getNumFromRamge(int a,int b){

        boolean isTrue  = false;
        int num = Integer.MIN_VALUE;

        while (!isTrue){
            try {
                num = Integer.parseInt(sc.nextLine());
                isTrue = true;
            }catch (Exception e){
                System.out.println("Ededi duzgun formada daxil edin!");
            }
        }

        return num;
    }
    public static boolean checkPassword(String user,String password) throws IOException {

        try(BufferedReader bf = new BufferedReader(new FileReader( "Users.txt"))){
            String line;
            while((line=bf.readLine())!=null){
                String [] arr = line.split(" ");
                if(user.equalsIgnoreCase(arr[0]) && password.equals(arr[1]))return true;
            }
        }
        return false;
    }
    public static int browseDict() throws IOException {

        int count =0;
        try(BufferedReader bw = new BufferedReader( new FileReader("Dicts.txt"))){
            String dict =null ;
            boolean isEmpty = false;
            while((dict= bw.readLine())!=null){
                count++;
                isEmpty = true;
                System.out.println(count + "." + dict);
            }
            if(!isEmpty){
                System.out.println("Sistemde luget yoxdur!");
                Admin.loadAdminOperations();
            }
        }
        return count;
    }
    public static String getDict(int dictMaxRange) throws IOException {
        System.out.print("Lugeti sechin:");
        int dictNum = Methods.getNum();
        int count = 0;
        String dict;
        try(BufferedReader br = new BufferedReader(new FileReader("Dicts.txt"))){
            while ((dict=br.readLine())!=null){
                count++;
                if(count==dictNum)return dict;
            }
        }
        return dict;
    }
}
