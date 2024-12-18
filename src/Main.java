public class Main {
    public static void main(String[] args) {
        Parser parser = Parser.loadFile("res/scene.str");

        Scene scene = parser.nextScene();

        System.out.printf("+----------------+\n");
        for (int i = 0; i < scene.graphics.length; ++i) {
            System.out.printf("|");
            System.out.printf(scene.graphics[i]);
            System.out.printf("|\n");
        }
        System.out.printf("+----------------+\n");
    }
}
