package task3;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Чтение из файла через поток:\nвведите путь к файлу");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            InputStream is = new FileInputStream(reader.readLine());
            OutputStream os = System.out;
            byte[] buf = new byte[1024];
            int avail;
            while ((avail = is.available()) > 0) {
                is.read(buf, 0, avail);
                os.write(buf, 0, avail);
            }
            is.close();
            System.out.println("Чтение из файла через reader:\nвведите путь к файлу");
            reader = new BufferedReader(new FileReader(reader.readLine()));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Запись в файл через writer:\nвведите путь к файлу");
            File file2 = new File(reader.readLine());
            file2.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
            while (!(s = reader.readLine()).equals("")) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
