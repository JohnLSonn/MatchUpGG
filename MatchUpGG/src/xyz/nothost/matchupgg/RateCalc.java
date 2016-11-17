package xyz.nothost.matchupgg;

public class RateCalc {

	private double win_rate;
	private double mate_win_rate;
	private double vs_win_rate;

	public void setWinRate(double rate){
		this.win_rate = rate;
	}
	public double getWinRate(){
		return this.win_rate;
	}
	public void setMateWinRate(double rate){
		this.mate_win_rate = rate;
	}
	public double getMateWinRate(){
		return this.mate_win_rate;
	}
	public void setVsWinRate(double rate){
		this.vs_win_rate = rate;
	}
	public double getVsWinRate(){
		return this.vs_win_rate;
	}

	private void updateRateDB(){

//		WinRate = WinCount/GameCount*100;
//		MateWinRate = MateWinCount/MateGameCount*100;
//		VsWinRate = VsWinCount/VsGameCount*100;

		int win_count = 10;
		int game_count = 20;
		int mate_win_count = 4;
		int mate_game_count = 13;
		int vs_win_count = 5;
		int vs_game_count = 7;

		setWinRate((double) win_count / game_count * 100 );
		setMateWinRate((double) mate_win_count / mate_game_count * 100 );
		setVsWinRate((double) vs_win_count / vs_game_count * 100 );

		// こんな感じで書くと見やすいかも。
	}

	public static void main(String args[]){

		RateCalc rc = new RateCalc();
		rc.updateRateDB();

		System.out.println("win_rate :" + rc.getWinRate());
		System.out.println("mate_win_rate :" + rc.getMateWinRate());
		System.out.println("vs_win_rate :" + rc.getVsWinRate());

	}
}
