public class Match {
    private Team teamA;
    private Team teamB;
    private int targetKill;
    private boolean finished;
    private Team winner;

    Match(Team teamA, Team teamB, int targetKill) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.targetKill = targetKill;
        this.finished = false;
        this.winner = null;
    }

    public void checkWinner() {
        if (!finished) {
            if (teamA.getScore() >= targetKill) {
                this.winner = teamA;
                this.finished = true;
                System.out.println("Team A MENANG!");
            } else if (teamB.getScore() >= targetKill) {
                this.winner = teamB;
                this.finished = true;
                System.out.println("Team B MENANG!");
            }
        }
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public int getTargetKill() {
        return targetKill;
    }

    public boolean isFinished() {
        return finished;
    }

    public Team getWinner() {
        return winner;
    }

}
