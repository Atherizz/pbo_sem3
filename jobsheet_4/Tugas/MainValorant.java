

public class MainValorant {
    public static void main(String[] args) {
        Weapon vandal = new Weapon("Vandal", 40, 20);
        Weapon phantom = new Weapon("Phantom", 35, 20);
        Weapon operator = new Weapon("Operator", 200, 5);
        Weapon spectre = new Weapon("Spectre", 30, 25);

        DuelistAgent jett = new DuelistAgent("Jett", 100, 50, "Tailwind");
        jett.setWeapon(vandal);

        DuelistAgent phoenix = new DuelistAgent("Phoenix", 100, 50, "Curveball");
        phoenix.setWeapon(phantom);

        ControllerAgent brimstone = new ControllerAgent("Brimstone", 100, 75, "Sky Smoke");
        brimstone.setWeapon(operator);

        ControllerAgent omen = new ControllerAgent("Omen", 100, 50, "Dark Cover");
        omen.setWeapon(spectre);

        Player p1 = new Player("Savero");   p1.setSelectedAgent(jett);
        Player p2 = new Player("Athallah"); p2.setSelectedAgent(phoenix);
        Player p3 = new Player("Budi");     p3.setSelectedAgent(brimstone);
        Player p4 = new Player("Dimas");    p4.setSelectedAgent(omen);

        Team teamA = new Team(new Player[]{p1, p2});
        Team teamB = new Team(new Player[]{p3, p4});
        p1.setTeam(teamA); p2.setTeam(teamA); p3.setTeam(teamB); p4.setTeam(teamB);

        Match match = new Match(teamA, teamB, 5);

        System.out.println("=== MATCH START  ===");
        System.out.println("Target Kill: " + match.getTargetKill());
        System.out.println("\n-- Team A --\n" + teamA.info());
        System.out.println("-- Team B --\n" + teamB.info());

        p1.shoot(p3, 5);
        System.out.println();
        System.out.println("Team A Score : " + teamA.getScore());

        p3.shoot(p4, 1);
        System.out.println("Team B Score : " + teamB.getScore());


    }
}
