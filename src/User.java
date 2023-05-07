import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User {
    public static void loadUserUI() throws IOException {
        int dictNum = Methods.browseDict();
        String dict = Methods.getDict(dictNum);
        logInDict(dict);
    }
    static void logInDict(String dict ) throws IOException {
        System.out.print("Sozu daxil edin:");
        String word = Methods.sc.nextLine();
        System.out.println("Tercumesi:" + search(word,dict));
        operationSelector(dict);
    }
    private static void operationSelector(String dict) throws IOException {
        System.out.print("\n1.Yeni söz axtar\n2.Lüğəti dəyiş\n3.İstifadəçini dəyiş\nSeçiminiz: ");
        int operationNum = Methods.getNum();
        switch (operationNum){
            case 1:logInDict(dict);
            case 2:loadUserUI();
            case 3:Program.main(null);
            default:{
                System.out.println("1-3 aralığında ədəd daxil etməlisiniz. Yenidən cəhd edin");
                operationSelector(dict);
            }
        }
    }
    public static String search(String word,String dict) throws IOException {
        String result;
        Map<String,String> dictMap = new HashMap<>();
        File file = new File(dict+".txt");
        if(file.exists()){
            try(BufferedReader br = new BufferedReader( new FileReader(file))){
                String line;
                while((line= br.readLine())!=null){
                    String [] arr = line.split("-");
                    dictMap.put(arr[0],arr[1]);
                    dictMap.put(arr[1],arr[0]);
                }
            }
        }
        else return "Axtardiginiz luget movcud deyil";

        if(dictMap.isEmpty())
            return "Luget boshdur";
        else{
            result = dictMap.getOrDefault(word, "netice tapilmadi");
        }
        return result;

    }
}
