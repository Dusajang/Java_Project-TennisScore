package TenisProject;

class MatchPoint {
	private int score;   //기본값 0
	private int game;   //기본값 0
	private int set;   //기본값 0
	private int deuceScore;

	//이겼을 때 점수, 게임, 세트 증가시켜주는 메서드들
	public void plusScore() {
		this.score++;
	}

	public void plusGame() {
		this.game++;
	}

	public void plusSet() {
		this.set++;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public int getSet() {
		return set;
	}
	public void setSet(int set) {
		this.set = set;
	}
	public int getDeuceScore() {
		return deuceScore;
	}
	public void setDeuceScore(int deuceScore) {
		this.deuceScore = deuceScore;
	}
	public void plusDeuceScore() {
		this.deuceScore++;
	}
}