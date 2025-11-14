import java.io.*;
import java.util.*;

public class FileSplitter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            
            System.out.print("Masukkan path file: ");
            String filePath = scanner.nextLine();

            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File tidak ditemukan!");
                return;
            }

            StringBuilder content = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();

            String fullText = content.toString().trim();

            System.out.println("\nIsi file berhasil dibaca:");
            System.out.println("--------------------------------");
            System.out.println(fullText);
            System.out.println("--------------------------------");

            // --- 2. INPUT JUMLAH POTONGAN ---
            System.out.print("\nMasukkan jumlah bagian: ");
            int parts = scanner.nextInt();
            scanner.nextLine();

            if (parts <= 0) {
                System.out.println("Jumlah bagian tidak boleh 0 atau negatif!");
                return;
            }

            Queue<String> queue = new LinkedList<>();

            int length = fullText.length();
            int partSize = (int) Math.ceil((double) length / parts);

            int start = 0;

            for (int i = 0; i < parts; i++) {
                int end = Math.min(start + partSize, length);
                String piece = fullText.substring(start, end);
                queue.add(piece);   // Masukkan potongan ke Queue
                start = end;
            }

            // --- OUTPUT HASIL PEMOTONGAN ---
            System.out.println("\n=== HASIL PEMOTONGAN FILE ===");

            int index = 1;
            while (!queue.isEmpty()) {
                System.out.println("\nBagian " + (index++) + ":");
                System.out.println(queue.poll());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
