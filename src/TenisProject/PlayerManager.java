package TenisProject;

import java.util.ArrayList;
import java.util.Scanner;

class PlayerManager {
    private ArrayList<Player> players;

    public PlayerManager() {
        players = new ArrayList<>();
    }

    public void addPlayer(String name, String gender) {
        players.add(new Player(name, gender));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void inputPlayers() {
        Scanner scanner = new Scanner(System.in);
        String name, gender;
        System.out.println("선수 정보를 입력하세요.");

        while (true) {
            System.out.print("선수 이름 (종료하려면 '끝' 입력): ");
            name = scanner.nextLine();
            if (name.equalsIgnoreCase("끝")) break;

            System.out.print("선수 성별 (남자/여자): ");
            gender = scanner.nextLine();

            if (!gender.equals("남자") && !gender.equals("여자")) {
                System.out.println("잘못된 성별입니다. '남자' 또는 '여자'를 입력해주세요.");
                continue;
            }

            addPlayer(name, gender);
            System.out.println("선수가 추가되었습니다.");
        }
    }
}
