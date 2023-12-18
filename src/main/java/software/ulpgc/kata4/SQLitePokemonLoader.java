package software.ulpgc.kata4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.*;

public class SQLitePokemonLoader implements PokemonLoader {
    private final Connection connection;

    public SQLitePokemonLoader(Connection connection) {
        this.connection = connection;
    }

    public static PokemonLoader with(String databaseRoute) throws SQLException {
        return new SQLitePokemonLoader(getConnection(databaseRoute));
    }

    @Override
    public List<Pokemon> loadAll() {
        try {
            return load(queryAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Pokemon> load(ResultSet resultSet) throws SQLException {
        List<Pokemon> pokemon = new ArrayList<>();
        while (resultSet.next()) {
            pokemon.add(pokemonFrom(resultSet));
        }
        return pokemon;
    }

    private Pokemon pokemonFrom(ResultSet resultSet) throws SQLException {
        return new Pokemon(
                resultSet.getInt("#"),
                resultSet.getString("Name"),
                resultSet.getString("Type1"),
                resultSet.getString("Type2"),
                resultSet.getInt("Total"),
                resultSet.getInt("HP"),
                resultSet.getInt("Attack"),
                resultSet.getInt("Defense"),
                resultSet.getInt("Sp.Atk"),
                resultSet.getInt("Sp.Def"),
                resultSet.getInt("Speed"),
                resultSet.getInt("generation"),
                resultSet.getString("Legendary")
                );
    }

    private static String QUERY_ALL = "SELECT * FROM Pokemon";
    private ResultSet queryAll() throws SQLException {
        return connection.createStatement().executeQuery(QUERY_ALL);
    }

    private String queryType;
    @Override
    public List<Pokemon> loadFromType(String type) {
        queryType = "SELECT * FROM Pokemon WHERE Type1='%s' OR Type2='%s'".formatted(type, type);
        try {
            return load(queryType());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet queryType() throws SQLException {
        return connection.createStatement().executeQuery(queryType);
    }

    @Override
    public List<Pokemon> loadFromSQLQuery(String query) {
        try {
            return load(querySQL(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet querySQL(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }
}
