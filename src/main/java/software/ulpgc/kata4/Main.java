package software.ulpgc.kata4;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        PokemonLoader loader = SQLitePokemonLoader.with("PokemonStats.db");
        List<Pokemon> pokemon = loader.loadAll();
        List<Pokemon> dragons = loader.loadType("Dragon");
        /*for (Pokemon p : pokemon) {
            System.out.println(p.name());
        }*/

        for (Pokemon p : dragons) {
            System.out.println(p.name());
        }
    }
}
