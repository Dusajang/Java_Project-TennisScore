package TenisProject;

import java.util.ArrayList;
import java.util.Scanner;

class TeamSelection{

	public static int teamDataInput(int rule, 
			ArrayList<Player> players, 
			ArrayList<Player> team1, ArrayList<Player> team2) {

		Scanner scanner = new Scanner(System.in);

		String gender = "";
		String input = "";

		if(rule == 1) {	//단식 선택
			boolean flag = false;
			do {
				if(flag == true) System.out.println("잘못 선택하셨습니다.");
				Print.printRuleSelect();
				System.out.print("\t\t1. 남성 단식 / 2. 여성 단식\n선택 : ");
				input = scanner.next();
			} while (flag = !((input+"").matches("[1-2]")));
			rule = Integer.valueOf(input);

			gender = (rule == 1) ? "남자" : "여자";
			Print.printPlayerNumberCheck(gender, players.size(), 2);

			System.out.println("\n\n\n\n1 팀원 선택");
			teamSelect(players, gender, team1);
			System.out.println("\n\n\n\n2 팀원 선택" );
			teamSelect(players, gender, team2);

		}else if(rule == 2){
			boolean flag = false;
			do {
				if(flag == true) System.out.println("잘못 선택하셨습니다.");
				Print.printRuleSelect();
				System.out.print("\t1. 남성 복식 / 2. 여성 복식 / 3. 혼합 복식\n선택 : ");
				input = scanner.next();
			} while (flag = !((input+"").matches("[1-3]")));
			rule = Integer.valueOf(input);

			if(rule == 1 || rule == 2) {
				gender = (rule == 1) ? "남자" : "여자";


				Print.printPlayerNumberCheck(gender, players.size(), 4);
				System.out.println("\n\n\n\n1 팀원 선택");
				teamSelect(players, gender, team1);
				teamSelect(players, gender, team1);

				System.out.println("\n\n\n\n\n2 팀원 선택");
				teamSelect(players, gender, team2);
				teamSelect(players, gender, team2);

			}else if(rule == 3) {
				gender = "남자";

				Print.printPlayerNumberCheck(gender, players.size(), 2);
				System.out.println("\n\n\n\n1 팀 남성 선택");
				teamSelect(players, gender, team1);
				System.out.println("\n\n\n\n\n2팀 남성 선택");
				teamSelect(players, gender, team2);

				System.out.println();

				gender = "여자";

				Print.printPlayerNumberCheck(gender, players.size(), 2);
				System.out.println("\n\n\n\n1 팀 여성 선택");
				teamSelect(players, gender, team1);
				System.out.println("\n\n\n\n\n2 팀 여성 선택");
				teamSelect(players, gender, team2);
			}
		}

		System.out.println("1팀 : " + team1);
		System.out.println("2팀 : " + team2);

		//남성단식, 남성복식일때 5세트, 여성 참가시 3세트
		return (rule == 1) ? 5 : 3;
	}



	private static Player selectList(ArrayList<Player> players, int select) {
		return players.remove(select-1);
	}

	private static void teamSelect(ArrayList<Player> players, String gender, ArrayList<Player> team) {
		Scanner scanner = new Scanner(System.in);

		String input = "";
		boolean flag = false;
		int playersSize = players.size();
		String regex = String.format("[1-%d]", playersSize);
		do {
			if(flag == true) System.out.println("잘못 선택하셨습니다.");
			int idx = 1;
			System.out.println("============");
			for (int i = 0; i < players.size(); i++) {
				if(players.get(i).getGender().equals(gender)) {
					System.out.printf("%d : %s\n", idx++, players.get(i));
				}
			}
			System.out.println("============");
			System.out.print("팀원 선택 : ");
			input = scanner.next();
		} while (flag = !((input+"").matches(regex)));

		int select = Integer.valueOf(input);
		team.add(selectList(players, select));
	}
}