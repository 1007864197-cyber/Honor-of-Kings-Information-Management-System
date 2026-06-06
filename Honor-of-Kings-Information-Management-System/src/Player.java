import java.util.ArrayList;
import java.util.List;

public class Player extends Person {
    private int level;
    private String role;
    private List<Hero> heroes = new ArrayList<>();

    public Player(String id, String name, String email, int level, String role) {
        super(id, name, email);
        this.level = level;
        this.role = role;
    }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<Hero> getHeroes() { return heroes; }
    public void setHeroes(List<Hero> heroes) { this.heroes = heroes; }
    public void addHero(Hero h) { if (h != null) this.heroes.add(h); }
    public void removeHero(Hero h) { this.heroes.remove(h); }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        if (getName() != null && getName().contains(keyword)) return true;
        if (role != null && role.contains(keyword)) return true;
        for (Hero h : heroes) {
            if (h != null && h.matches(keyword)) return true;
        }
        return false;
    }
}
