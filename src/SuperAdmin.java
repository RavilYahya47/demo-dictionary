import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperAdmin {
    public static void loadSuperAdmin() throws IOException {
        System.out.println("\nSuper admin kimi daxil oldunuz");
        System.out.println("""
                1. Admin əlavə et
                2. Admin sil
                3. Lüğət sil
                0. Istifadəçini dəyiş
                """);
        System.out.print("Seçiminizi edin:");
        int operationNum = Methods.getNum();

        switch (operationNum){
            case 0 -> Program.main(null);
            case 1 -> createNewAdmin();
            case 2 -> deleteAdmin();
            case 3 -> deleteDict();
        }
    }
    private static void deleteDict() throws IOException {
        int maxNum = Methods.browseDict();
        System.out.print("\nSilmək istədiyiniz lugetin nomresini daxil edin:");
        int num = Methods.getNumFromRamge(1,maxNum);
        String line;
        List<String> dicts = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("Dicts.txt"))){
            int curnum = 0;
            while((line= br.readLine())!=null) {
                if (++curnum != num)
                    dicts.add(line);
                else{
                    new File (line+".txt").delete();
                }
            }
        }
        FileWriter fw  =new FileWriter( "Dicts.txt");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Dicts.txt",true))){
            for(String dict:dicts){
                bufferedWriter.write(dict + "\n");
            }
        }
        System.out.println("Luget ugurla silindi");
        loadSuperAdmin();
    }
    private static void deleteAdmin() throws IOException {
        showAdmins();
        System.out.println("\nSilmek istediyiniz adminin loginini daxil edin");

        String login = Program.sc.nextLine().trim();
        Map<String,String> users = new HashMap<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader("Users.txt"))){
            while((line= br.readLine())!=null){
                String [] arr = line.split(" ");
                users.put(arr[0],arr[1]);
            }
        }
        if(users.containsKey(login))
        {
            users.remove(login);
            updateUsersDatabase(users);
            System.out.println("Admin bazadan silindi!\n");
            loadSuperAdmin();
        }
        else{
            System.out.println("\nSecdiyiniz admin bazada yoxdur. Yeniden cehd edin\n");
            deleteAdmin();
        }

    }
    private static void createNewAdmin() throws IOException {
        System.out.print("\nYeni login:");
        String login = Program.sc.nextLine().trim();
        System.out.print("Yeni parol:");
        String password = Program.sc.nextLine().trim();

        Map<String,String> users = new HashMap<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader("Users.txt"))){
            while((line= br.readLine())!=null){
                String [] arr = line.split(" ");
                users.put(arr[0],arr[1]);
            }
        }

        if(users.containsKey(login))
        {
            System.out.println("Daxil etdiyiniz login artıq mövcuddur. Yeni login təyin edin\n");
            createNewAdmin();
        }
        else {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt",true))){
                bw.write(login + " " + password + "\n");
                System.out.println("Yeni admin uğurla bazaya əlavə edildi");
            }
            loadSuperAdmin();
        }

    }
    private static void showAdmins() throws IOException {
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader("Users.txt"))){
            while((line= br.readLine())!=null){
                String [] arr = line.split(" ");
                System.out.println("Username: " + arr[0]);
            }
        }
    }
    private static void updateUsersDatabase(Map<String,String> users) throws IOException {
        FileWriter fw =new FileWriter( new File("Users.txt"));

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt",true))){
            for(Map.Entry<String,String> entry:users.entrySet()){
                bw.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        }
    }
}
