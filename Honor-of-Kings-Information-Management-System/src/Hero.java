import java.util.ArrayList;
import java.util.List;

public class Hero implements Searchable {
    private String id;
    private String name;
    private HeroType type;
    private List<Equipment> equipments = new ArrayList<>();

    public Hero(String id, String name, HeroType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HeroType getType() { return type; }
    public void setType(HeroType type) { this.type = type; }
    public List<Equipment> getEquipments() { return equipments; }
    public void setEquipments(List<Equipment> equipments) { this.equipments = equipments; }

    public void addEquipment(Equipment e) { this.equipments.add(e); }
    public void removeEquipment(Equipment e) { this.equipments.remove(e); }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        if (name != null && name.contains(keyword)) return true;
        for (Equipment e : equipments) {
            if (e != null && e.matches(keyword)) return true;
        }
        return false;
    }
}
