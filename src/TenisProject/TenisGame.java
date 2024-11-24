package TenisProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TenisGame {

    public static void main(String[] args) {
        PlayerManager playerManager = new PlayerManager();
        playerManager.inputPlayers();
        ArrayList<Player> players = playerManager.getPlayers();
        ArrayList<Player> team1 = new ArrayList<>();
        ArrayList<Player> team2 = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int startMenu = 0;
        while (true) {
            Print.printMain();
            startMenu = scanner.nextInt();

            if (startMenu == 1) break; // 게임 시작
            else if (startMenu == 2) System.exit(0); // 게임 종료
        }

        boolean flag = false;
        String input = "";
        do {
            if (flag) System.out.println("잘못 선택하셨습니다.");
            Print.printRuleSelect();
            System.out.print("\t\t   1. 단식 / 2. 복식\n선택 : ");
            input = scanner.next();
        } while (flag = !(input.matches("[1-2]")));
        int rule = Integer.valueOf(input);

        int SetValue = TeamSelection.teamDataInput(rule, players, team1, team2);
        int winSet = (SetValue / 2) + 1;

        MatchPoint t1 = new MatchPoint();
        MatchPoint t2 = new MatchPoint();

        // 여기에 경기 로직 추가
        Random rnd = new Random();

        flag = false;
        do {
            if (flag) System.out.println("잘못 선택하셨습니다.");
            Print.printRuleSelect();
            System.out.print("   1. 타이브레이크 적용 / 2. 타이브레이크 미적용\n선택 : ");
            input = scanner.next();
        } while (flag = !(input.matches("[1-2]")));
        boolean tie = input.equals("1");

        while (t1.getSet() < winSet && t2.getSet() < winSet) {
            t1.setScore(0);
            t2.setScore(0);
            t1.setGame(0);
            t2.setGame(0);
            t1.setDeuceScore(0);
            t2.setDeuceScore(0);

            while (t1.getGame() < 6 && t2.getGame() < 6) {
                t1.setScore(0);
                t2.setScore(0);
                t1.setDeuceScore(0);
                t2.setDeuceScore(0);
                while (t1.getScore() < 4 && t2.getScore() < 4) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t1.setDeuceScore(0);
                    t2.setDeuceScore(0);

                    int n = rnd.nextInt(2);
                    if (n == 0) t1.plusScore();
                    else t2.plusScore();
                    Print.printScoreBoard(team1, team2, t1, t2);

                    if (t1.getScore() == 4) {
                        t1.plusGame();
                        Print.printScoreBoard(team1, team2, t1, t2);
                        if (t1.getGame() == 6) {
                            t1.plusSet();
                            Print.printScoreBoard(team1, team2, t1, t2);
                        }
                        if (t1.getSet() == winSet) {
                            System.out.println("team1 승리");
                        }
                    } else if (t2.getScore() == 4) {
                        t2.plusGame();
                        Print.printScoreBoard(team1, team2, t1, t2);
                        if (t2.getGame() == 6) {
                            t2.plusSet();
                            Print.printScoreBoard(team1, team2, t1, t2);
                        }
                        if (t2.getSet() == winSet) {
                            System.out.println("team2 승리");
                        }
                    }

                    if (t1.getScore() == 3 && t2.getScore() == 3) {
                        while (Math.abs(t1.getDeuceScore() - t2.getDeuceScore()) < 2) {
                            n = rnd.nextInt(2);
                            if (n == 0) t1.plusDeuceScore();
                            else t2.plusDeuceScore();
                            Print.printScoreBoard(team1, team2, t1, t2);
                            if (t1.getDeuceScore() == t2.getDeuceScore()) {
                                t1.setDeuceScore(0);
                                t2.setDeuceScore(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                            }
                            if (t1.getDeuceScore() - t2.getDeuceScore() == 2) {
                                t1.plusGame();
                                t1.setScore(0);
                                t2.setScore(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                            } else if (t2.getDeuceScore() - t1.getDeuceScore() == 2) {
                                t2.plusGame();
                                t1.setScore(0);
                                t2.setScore(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                            }
                        }
                    }

                    if (t1.getGame() == 5 && t2.getGame() == 5) {
                        while (Math.abs(t1.getGame() - t2.getGame()) < 2) {
                            t1.setDeuceScore(0);
                            t2.setDeuceScore(0);
                            n = rnd.nextInt(2);
                            if (n == 0) t1.plusScore();
                            else t2.plusScore();
                            Print.printScoreBoard(team1, team2, t1, t2);

                            if (t1.getScore() == 4) {
                                t1.plusGame();
                                t1.setScore(0);
                                t2.setScore(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                            } else if (t2.getScore() == 4) {
                                t2.plusGame();
                                t1.setScore(0);
                                t2.setScore(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                            }

                            if (t1.getGame() - t2.getGame() == 2) {
                                t1.plusSet();
                                t1.setScore(0);
                                t2.setScore(0);
                                t1.setGame(0);
                                t2.setGame(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                                break;
                            } else if (t2.getGame() - t1.getGame() == 2) {
                                t2.plusSet();
                                t1.setScore(0);
                                t2.setScore(0);
                                t1.setGame(0);
                                t2.setGame(0);
                                Print.printScoreBoard(team1, team2, t1, t2);
                                break;
                            }
                        }
                    }

                    if (tie) {
                        if (t1.getGame() == 6 && t2.getGame() == 6) {
                            t1.setDeuceScore(0);
                            t2.setDeuceScore(0);
                            Print.printScoreBoard(team1, team2, t1, t2);
                            while (t1.getDeuceScore() < 7 && t2.getDeuceScore() < 7) {
                                n = rnd.nextInt(2);
                                if (n == 0) t1.plusDeuceScore();
                                else t2.plusDeuceScore();
                                Print.printScoreBoard(team1, team2, t1, t2);
                                if (t1.getDeuceScore() == 7) {
                                    t1.plusSet();
                                    t1.setScore(0);
                                    t2.setScore(0);
                                    t1.setGame(0);
                                    t2.setGame(0);
                                    Print.printScoreBoard(team1, team2, t1, t2);
                                } else if (t2.getDeuceScore() == 7) {
                                    t2.plusSet();
                                    t1.setScore(0);
                                    t2.setScore(0);
                                    t1.setGame(0);
                                    t2.setGame(0);
                                    Print.printScoreBoard(team1, team2, t1, t2);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
