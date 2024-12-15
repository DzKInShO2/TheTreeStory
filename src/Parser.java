import java.io.File;
import java.util.Scanner;

/*
 * Kelas ini digunakan untuk membuka file dari
 * luar menjadi object kelas Scene
 */
public class Parser {
    public String filePath      = null;
    private Scanner fileScanner = null;

    public static Parser loadFile(String filePath) {
        File file = new File(filePath);
        fileScanner = new Scanner(file);
    }

    public Scene nextScene() {
        int line = 0;

        int sceneId = 0;
        String[] sceneGraphics = new String[16];
        String sceneTitle = "";
        String sceneOpening = "";
        String sceneQuestion = "";
        String[] sceneChoices = new String[2];

        while (line < 22 &&
                fileScanner.hasNextLine()) {
            if (line == 0) {
                sceneId = int.parse(fileScanner.nextLine());
            } else if (line > 0 && line < 17) {
                sceneGraphics[line - 1] = fileScanner.nextLine();
            } else if (line == 17) {
                sceneTitle = fileScanner.nextLine();
            } else if (line == 18) {
                sceneOpening = fileScanner.nextLine();
            } else if (line == 19) {
                sceneQuestion = fileScanner.nextLine();
            } else if (line > 19) {
                sceneChoices[line - 20] = fileScanner.nextLine();
            }

            line++;
        }

        return new Scene(sceneTitle, sceneOpening, sceneQuestion, sceneChoices, sceneId);
    }
}
