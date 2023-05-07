import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    public static void loadAdminGate() throws IOException {
        boolean gate = false;

        while (!gate) {
            System.out.print("Login:");
            String login = Program.sc.nextLine().trim();
            System.out.print("Password:");
            String password = Program.sc.nextLine().trim();
            if(login.equals("ravil") && password.equals("7777")) SuperAdmin.loadSuperAdmin();
            gate = Methods.checkPassword(login, password);
            if (!gate)
                System.out.println("\nLogin ve ya parol yalnishdir. Yeniden cehd edin!\n");
            else{
                loadAdminOperations();
                System.out.println("Uğurla daxil oldunuz.");}
        }

        }
    public static void loadAdminOperations() throws IOException {
        System.out.println("""
                
                1. Lugete daxil ol
                2. Lugetlere bax
                3. Lugete yarat
                0. Istifadecini deyis
                """);
        System.out.print("Seçiminizi edin:");
        int operation = Methods.getNum();
        switch (operation) {
            case 3 -> createDictionary();
            case 2 -> {Methods.browseDict();loadAdminOperations();}
            case 1 -> {
                int dictNum = Methods.browseDict();
                String dict = Methods.getDict(dictNum);
                Admin.loadDict(dict);
            }
            case 0 -> Program.main(null);
            default -> {
                System.out.println("0-3 aralığında ədəd daxil edin!");
                loadAdminOperations();
            }
        }
    }
    private static void loadDict(String dict) throws IOException {

        System.out.println("Lüğətdə aşağıdakı əməliyyatları yerinə yetirə bilərsiniz");
        System.out.println("""
                
                1.Lüğətə söz əlavə etmək
                2.Lüğətdən söz silmək
                3.Lügətdəki söz üzərində dəyiklik etmək
                4.Lüğəti çap etmək
                0.Əsas menyuya geri dönmək
                """);
        System.out.print("Seçiminiz:");
        int operationNum = Methods.getNum();
        switch (operationNum){
            case 1 -> addWordToDict(dict);
            case 2 -> deleteWordFromDict(dict);
            case 3 -> updateWordInDict(dict);
            case 4 -> showDictionary(dict);
            case 0 -> loadAdminOperations();
        }

    }
    private static void updateWordInDict(String dict) throws IOException {
        System.out.print("\nUpdate etmek istədiyini sözü yazın:");
        String word = Methods.sc.nextLine().trim();

        Map<String,String> dictionary = getDictionary(dict);

        if(dictionary.containsKey(word)){
            dictionary.remove(word);
            System.out.println("\nYeni sözləri daxil edin\n");
            String [] newWord = getWordFromInput();
            dictionary.put(newWord[0],newWord[1]);
            updateDictionary(dict,dictionary);
        }
        else System.out.println("Daxil etdiyiniz söz lüğətdə mövcud deyil!");
        loadDict(dict);
    }
    static Map<String,String> getDictionary(String dict) throws IOException {
        Map<String,String> dictionary = new HashMap<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(dict+".txt"))){
            while ((line= br.readLine())!=null){
                String [] tuple = line.split("-");
                dictionary.put(tuple[0],tuple[1]);
            }
        }
        return dictionary;
    }
    private static void deleteWordFromDict(String dict) throws IOException {
        System.out.print("Silmək istədiyini sözü yazın:");
        String word = Methods.sc.nextLine().trim();

        Map<String,String> dictionary = getDictionary(dict);

        if(dictionary.containsKey(word)){
            dictionary.remove(word);
            System.out.println("Söz lüğətdən silindi");
            updateDictionary(dict,dictionary);
        }
        else System.out.println("Daxil etdiyiniz söz lüğətdə mövcud deyil!");
        loadDict(dict);
    }
    static String [] getWordFromInput(){
        String[] result = new String[2];
        System.out.print("Sözü daxil edin:");
        result[0] = Methods.sc.nextLine().trim();
        System.out.print("Sözün tərcüməsini daxil edin:");
        result[1] = Methods.sc.nextLine().trim();
        return result;
    }
    private static void addWordToDict(String dict) throws IOException {
        String [] word = getWordFromInput();
        if (new File(dict + ".txt").exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(dict + ".txt", true))) {
                bw.write(word[0] + "-" + word[1] + "\n");
            }
        }
        else {
            System.out.println("Bu adda luget movcud deyil!");
        }
        loadDict(dict);
    }
    static void createDictionary() throws IOException {
        System.out.print("Lugetin ilk dilini daxil edin:");
        String firstLang = Methods.sc.nextLine().trim();
        System.out.print("Lugetin ikinci dilini daxil edin:");
        String secondLang = Methods.sc.nextLine().trim();
        try (BufferedWriter br = new BufferedWriter(new FileWriter("Dicts.txt", true))) {
            br.write(firstLang+ "-" + secondLang + "\n");
        }
        File file = new File(firstLang + "-" + secondLang + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        loadAdminOperations();
    }
    static void updateDictionary(String dict, Map<String,String> data) throws IOException {
        File file = new File(dict+".txt");
        if(!file.exists()) System.out.println("Lüğət mövcud deyil");
        else{
            FileWriter fr = new FileWriter(file);
            try(BufferedWriter br = new BufferedWriter(new FileWriter(file,true))){
                for(Map.Entry<String,String> entry:data.entrySet()){
                    br.write(entry.getKey() + "-" + entry.getValue() + "\n");
                }
            }
            System.out.println("Lüğət uğurla yeniləndi!");
        }
    }
    static  void showDictionary(String dict) throws IOException {
        Map<String,String> dictionary = getDictionary(dict);
        for(Map.Entry<String,String> entry: dictionary.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("\n");
        loadDict(dict);
    }



}

