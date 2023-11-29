package software.ulpgc.kata4;

public record Pokemon(
        int id,
        String name,
        String type1,
        String type2,
        int total,
        int HP,
        int attack,
        int defense,
        int specialAttack,
        int specialDefense,
        int speed,
        int generation,
        String legendary
) {
}
