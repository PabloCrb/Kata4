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
    private String type;

    public SQLitePokemonLoader(Connection connection) {
        this.connection = connection;
    }

    public static PokemonLoader with(String database) throws SQLException {
        return new SQLitePokemonLoader(getConnection("jdbc:sqlite:C:\\Users\\Pablo\\IdeaProjects\\Databases\\" + database));
    }

    @Override
    public List<Pokemon> loadAll() {
        try {
            return load(QueryAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final String QUERY_ALL = "SELECT * FROM Pokemon";
    private ResultSet QueryAll() throws SQLException {
        return connection.createStatement().executeQuery(QUERY_ALL);
    }

    @Override
    public List<Pokemon> loadType(String type) {
        this.type = type;
        try {
            return load(QueryType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet QueryType() throws SQLException {
        String QUERYTYPE = "SELECT * FROM Pokemon WHERE Type1='" + type + "' OR Type2='" + type + "'";
        return connection.createStatement().executeQuery(QUERYTYPE);
    }

    private List<Pokemon> load(ResultSet resultSet) throws SQLException {
        List<Pokemon> pokemon = new ArrayList<>();
        while (resultSet.next()) {
            pokemon.add(new Pokemon(
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
                resultSet.getInt("Generation"),
                resultSet.getString("Legendary")
            ));
        }
        return pokemon;
    }
}
