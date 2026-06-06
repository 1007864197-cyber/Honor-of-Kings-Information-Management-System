import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataManager {
    private List<Player> players = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();
    private List<Hero> heroes = new ArrayList<>();
    private List<Equipment> equipments = new ArrayList<>();
    private List<MatchRecord> matchRecords = new ArrayList<>();

    // username -> password
    private Map<String, String> credentials = new HashMap<>();
    // username -> Person
    private Map<String, Person> userMap = new HashMap<>();

    public DataManager() {
        initialize();
    }

    private void initialize() {
        createEquipments(20);
        createHeroes(15);
        createPlayers(15); // 3 teams * 5 players = 15 to satisfy team requirement
        createAdmins();
        createTeams(3, 5);
        createMatchRecords(10);
    }

    private void createEquipments(int count) {
        for (int i = 1; i <= count; i++) {
            Equipment e = new Equipment("EQ" + i, "Equip-" + i, "Slot-" + ((i % 4) + 1), "描述：装备" + i);
            equipments.add(e);
        }
    }

    private void createHeroes(int count) {
        HeroType[] types = HeroType.values();
        Random r = new Random(42);
        for (int i = 1; i <= count; i++) {
            Hero h = new Hero("H" + i, "Hero-" + i, types[r.nextInt(types.length)]);
            // 每个英雄至少装备 2 件
            int idx1 = (i * 2 - 2) % equipments.size();
            int idx2 = (i * 2 - 1) % equipments.size();
            h.addEquipment(equipments.get(idx1));
            h.addEquipment(equipments.get(idx2));
            heroes.add(h);
        }
    }

    private void createPlayers(int count) {
        Random r = new Random(7);
        for (int i = 1; i <= count; i++) {
            String id = "P" + i;
            String name = "Player" + i;
            String email = "player" + i + "@example.com";
            Player p = new Player(id, name, email, r.nextInt(100), "Role" + ((i % 5) + 1));

            // 每名玩家至少拥有 3 个英雄（循环分配引用）
            for (int k = 0; k < 3; k++) {
                Hero h = heroes.get((i + k - 1) % heroes.size());
                p.addHero(h);
            }

            players.add(p);
            String username = "player" + i;
            String password = "pass" + i;
            credentials.put(username, password);
            userMap.put(username, p);
        }
    }

    private void createAdmins() {
        Admin admin = new Admin("A1", "Administrator", "admin@example.com", "ops");
        admins.add(admin);
        credentials.put("admin", "adminpass");
        userMap.put("admin", admin);
    }

    private void createTeams(int teamCount, int membersPerTeam) {
        int idx = 0;
        for (int t = 1; t <= teamCount; t++) {
            Team team = new Team("T" + t, "Team-" + t);
            for (int m = 0; m < membersPerTeam && idx < players.size(); m++) {
                Player p = players.get(idx++);
                team.addMember(p);
            }
            teams.add(team);
        }
    }

    private void createMatchRecords(int count) {
        Random r = new Random(11);
        for (int i = 1; i <= count; i++) {
            Team a = teams.get(r.nextInt(teams.size()));
            Team b = teams.get((r.nextInt(teams.size())) % teams.size());
            if (a == b && teams.size() > 1) {
                b = teams.get((teams.indexOf(a) + 1) % teams.size());
            }
            MatchResult res = MatchResult.values()[r.nextInt(MatchResult.values().length)];
            List<Player> parts = new ArrayList<>();
            parts.addAll(a.getMembers());
            parts.addAll(b.getMembers());
            MatchRecord mr = new MatchRecord("M" + i, LocalDateTime.now().minusDays(i), res, parts, a, b);
            matchRecords.add(mr);
        }
    }

    // 验证凭据
    public boolean verifyCredentials(String username, String password) {
        if (username == null || password == null) return false;
        String p = credentials.get(username);
        return p != null && p.equals(password);
    }

    public Person getUserByUsername(String username) {
        return userMap.get(username);
    }

    // 只暴露只读视图的 getters
    public List<Player> getPlayers() { return players; }
    public List<Admin> getAdmins() { return admins; }
    public List<Team> getTeams() { return teams; }
    public List<Hero> getHeroes() { return heroes; }
    public List<Equipment> getEquipments() { return equipments; }
    public List<MatchRecord> getMatchRecords() { return matchRecords; }
}
