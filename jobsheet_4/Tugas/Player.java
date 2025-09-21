public class Player {
    private String username;
    private Agent selectedAgent;
    private Team team;
    private int killCount;
    private int deathCount;

    Player(String username) {
        this.username = username;
        this.killCount = 0;
        this.deathCount = 0;
    }

    public String getUsername() {
        return username;
    }

    public void addKill() {
        this.killCount++;
        if (this.team != null) {
            this.team.addScore();
        }
    }

    public void addDeath() {
        this.deathCount++;
    }

    public void shoot(Player enemy, int hitCount) {
        if (this.selectedAgent == null) {
            System.out.println(this.username + " has no agent selected!");
            return;
        }
        if (enemy.getSelectedAgent() == null) {
            System.out.println(enemy.getUsername() + " has no agent to shoot!");
            return;
        }

        if (enemy.getTeam() == this.getTeam()) {
            System.out.println("Can't friendly fire!");
            return;
        }

        boolean isDied = this.selectedAgent.shoot(enemy.getSelectedAgent(), hitCount);

        System.out.println("\n" + this.username + " attacked " + enemy.getUsername() +
                " with " + hitCount + " hit(s).");


        if (isDied) {
            this.addKill();
            enemy.addDeath();
            System.out.println(enemy.getUsername() + " has been killed by " + this.username);
            System.out.println(this.username + " Kill Count: " + this.killCount);
        }
    }

    public Agent getSelectedAgent() {
        return selectedAgent;
    }

    public void setSelectedAgent(Agent selectedAgent) {
        this.selectedAgent = selectedAgent;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public String info() {
        String info = "";
        info += "Username: " + this.username + "\n";
        info += "Kill Count: " + this.killCount + "\n";
        info += "Death Count: " + this.deathCount + "\n";
        if (this.selectedAgent != null) {
            info += "Selected Agent: " + this.selectedAgent.getName() + "\n";
            info += "Health: " + this.selectedAgent.getHealth() + "\n";
            info += "Armor: " + this.selectedAgent.getArmor() + "\n";
        } else {
            info += "Selected Agent: None\n";
        }
        if (this.team != null) {
            info += "Team Score: " + this.team.getScore() + "\n";
            info += "Team Players:\n";
            for (Player player : team.getPlayers()) {
                info += "- " + player.getUsername() + "\n";
            }
        } else {
            info += "Team: None\n";
        }
        return info;
    }

}
