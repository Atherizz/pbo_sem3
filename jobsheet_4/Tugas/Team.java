public class Team {
    private Player players[];
    private int score;

    Team(Player players[]) {
        this.players = players;
        this.score = 0;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score += 1;
    }


    public String info() {
        String info = "";
        info += "Team Score: " + this.score + "\n";
        info += "Players:\n";
        for (Player player : players) {
            info += "- " + player.getUsername() + "\n";
        }
        return info;
    }
}
