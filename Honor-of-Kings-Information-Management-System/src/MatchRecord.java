import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchRecord {
    private String id;
    private LocalDateTime matchDate;
    private MatchResult result;
    private List<Player> participants = new ArrayList<>();
    private Team teamA;
    private Team teamB;

    public MatchRecord(String id, LocalDateTime matchDate, MatchResult result, List<Player> participants, Team teamA, Team teamB) {
        this.id = id;
        this.matchDate = matchDate;
        this.result = result;
        this.participants = participants != null ? participants : new ArrayList<>();
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }
    public MatchResult getResult() { return result; }
    public void setResult(MatchResult result) { this.result = result; }
    public List<Player> getParticipants() { return participants; }
    public void setParticipants(List<Player> participants) { this.participants = participants; }
    public Team getTeamA() { return teamA; }
    public void setTeamA(Team teamA) { this.teamA = teamA; }
    public Team getTeamB() { return teamB; }
    public void setTeamB(Team teamB) { this.teamB = teamB; }
}
