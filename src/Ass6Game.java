// 315112672 Maayan Bahar

import java.util.LinkedList;
import java.util.List;

/**
 * The game.
 */
public class Ass6Game {
    /**
     * @param args The arguments. Represents all the levels to be run. Any invalid arg is ignroed
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"1", "2", "3", "4"};
        }

        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow game = new GameFlow(animationRunner);
        List<LevelInformation> levels = new LinkedList<LevelInformation>();

        for (String arg: args) {
            try {
                switch (Integer.parseInt(arg)) {
                    case 1:
                        levels.add(new LevelOne());
                        break;
                    case 2:
                        levels.add(new LevelTwo(game));
                        break;
                    case 3:
                        levels.add(new LevelThree(game));
                        break;
                    case 4:
                        levels.add(new LevelFour(game));
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException ignored) { }
        }

        game.runLevels(levels);
    }
}

