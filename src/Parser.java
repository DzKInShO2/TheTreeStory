import java.io.File;
import java.util.Scanner;
import java.math.BigInteger;

/*
 * Kelas ini digunakan untuk membuka file dari
 * luar menjadi object kelas Scene
 */
public class Parser {
    public String filePath      = null;
    private Scanner fileScanner = null;

    public Parser(File file) {
        try {
            this.fileScanner = new Scanner(file);
        } catch (Exception e) { }
    }

    public static Parser loadFile(String filePath) {
        File file = new File(filePath);
        return new Parser(file);
    }

    public Scene nextScene() {
        if (!fileScanner.hasNextLine()) return null;

        int sceneId = 0;
        String[] sceneGraphics = new String[16];
        String sceneTitle = "";
        String sceneOpening = "";
        String sceneQuestion = "";
        StringList sceneChoices = new StringList();

        int line = 0;
        while (line < 22 &&
                fileScanner.hasNextLine()) {
            if (line == 0) {
                sceneId = Integer.parseInt(fileScanner.nextLine());
            } else if (line > 0 && line < 17) {
                String[] col = fileScanner.nextLine().split(" ");
                String row = "";

                for (int i = 0; i < col.length; ++i) {
                    BigInteger hex = new BigInteger(col[i], 16);
                    BigInteger r = hex.shiftRight(24).and(new BigInteger("FF", 16));
                    BigInteger g = hex.shiftRight(16).and(new BigInteger("FF", 16));
                    BigInteger b = hex.shiftRight(8).and(new BigInteger("FF", 16));

                    row += String.format("\033[48;2;%d;%d;%dm  \033[0m", r, g, b);
                }
                sceneGraphics[line - 1] = row;
            } else if (line == 17) {
                sceneTitle = fileScanner.nextLine();
            } else if (line == 18) {
                sceneOpening = fileScanner.nextLine();
            } else if (line == 19) {
                sceneQuestion = fileScanner.nextLine();
            } else if (line > 19) {
                sceneChoices.append(fileScanner.nextLine());
            }

            line++;
        }

        return new Scene(sceneId, sceneTitle, sceneGraphics, 
                         sceneOpening, sceneQuestion, sceneChoices);
    }
}
