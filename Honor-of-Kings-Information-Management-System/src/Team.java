import java.util.ArrayList;
import java.util.List;

public class Team implements Searchable {
    private String id;
    private String name;
    private List<Player> members = new ArrayList<>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Player> getMembers() { return members; }
    public void setMembers(List<Player> members) { this.members = members; }

    public void addMember(Player p) { this.members.add(p); }
    public void removeMember(Player p) { this.members.remove(p); }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        if (name != null && name.contains(keyword)) return true;
        for (Player p : members) {
            if (p != null && p.matches(keyword)) return true;
        }
        return false;
    }
}
