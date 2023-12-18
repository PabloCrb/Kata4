package software.ulpgc.kata4;

import java.util.List;

public interface PokemonLoader {
    List<Pokemon> loadAll();
    List<Pokemon> loadFromType(String type);
    List<Pokemon> loadFromSQLQuery(String query);
}
