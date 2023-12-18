package software.ulpgc.kata4;

import java.sql.SQLException;
import java.util.List;
import software.ulpgc.kata3.*;

public class Main {
    private static List<Pokemon> pokemon;
    public static void main(String[] args) {
        try {
            PokemonLoader loader = SQLitePokemonLoader.with("jdbc:sqlite:C:\\Users\\Pablo\\IdeaProjects\\Databases\\PokemonStats.db");
            pokemon = loader.loadAll();
            MainFrame frame = new MainFrame();
            frame.getDisplay().show("Pokemon stats histogram", "Stats", "Frequency", pokemonLoad());
            frame.setVisible(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Histogram pokemonLoad() {
        return new Histogram() {
            @Override
            public int bins() {
                return 15;
            }

            @Override
            public double[] values() {
                return pokemon.stream().
                        mapToDouble(Pokemon::total).
                        toArray();
            }
        };
    }
}
