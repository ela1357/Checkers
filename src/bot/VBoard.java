package bot;

import java.util.ArrayList;

public class VBoard {
	public int whiteCtr;
	public int blackCtr;
	public int whiteKingCtr;
	public int blackKingCtr;
	Boolean side;											//1 - bia³e,	0 - czarne (maj¹ ruch)
	Boolean canBeat = false;
	
	public int[][] board = new int[10][10];							//-1 - jasne pole,	0 - puste,	1 - bia³e,	2 - czarne,	3 - bia³a damka,	4 - czarna damka
	
	public VBoard() {
		whiteCtr = 20;
		blackCtr = 20;
		whiteKingCtr = 0;
		blackKingCtr = 0;
		side = true;
		
		for (int j=0; j<5; j++) {
			for (int i=0; i<4; i++) {
				board[i][(j*2)+1-(i%2)]=2;
				board[i][(j*2)+(i%2)]=-1;
			}
			for (int i=4; i<6; i++) {
				board[i][(j*2)+1-(i%2)]=0;
				board[i][(j*2)+(i%2)]=-1;
			}
			for (int i=6; i<10; i++) {
				board[i][(j*2)+1-(i%2)]=1;
				board[i][(j*2)+(i%2)]=-1;
			}
		}
		
	}
	
	public VBoard(int[][] board, int whiteCtr, int blackCtr, Boolean side, int whiteKingCtr, int blackKingCtr) {
		this.board = board;
		this.whiteCtr = whiteCtr;
		this.blackCtr = blackCtr;
		this.side = side;
		this.whiteKingCtr = whiteKingCtr;
		this.blackKingCtr = blackKingCtr;
		
		
					if (side) {
						for(int i=0; i<10; i++) {
							for(int j=0; j<10; j++) {
						if(board[i][j]==1) {
							if(i>1 && j>1 && board[i-1][j-1]==2 && board[i-2][j-2]==0) {													//bicia zwyk³ych - ruch bia³ych - pola parzyste
								canBeat=true;
							}
							if(i<8 && j>1 && board[i+1][j-1]==2 && board[i+2][j-2]==0) {
								canBeat=true;
							}
							if(i<8 && j<8 && board[i+1][j+1]==2 && board[i+2][j+2]==0) {
								canBeat=true;
							}
							if(i>1 && j<8 && board[i-1][j+1]==2 && board[i-2][j+2]==0) {
								canBeat=true;
							}
							
							
							if(i>1 && j>1 && board[i-1][j-1]==4 && board[i-2][j-2]==0) {													//bicia damki - ruch bia³ych - pola parzyste
								canBeat=true;
							}
							if(i<8 && j>1 && board[i+1][j-1]==4 && board[i+2][j-2]==0) {
								canBeat=true;
							}
							if(i<8 && j<8 && board[i+1][j+1]==4 && board[i+2][j+2]==0) {
								canBeat=true;
							}
							if(i>1 && j<8 && board[i-1][j+1]==4 && board[i-2][j+2]==0) {
								canBeat=true;
							}
						}
						
						if(board[i][j]==3) {
							canBeat=canKingBeat(i, j);
						}
						if (canBeat)
							break;
							}}
					}
					else {
						for(int i=0; i<10; i++) {
							for(int j=0; j<10; j++) {
						if(board[i][j]==2) {
							if(i>1 && j>1 && board[i-1][j-1]==1 && board[i-2][j-2]==0) {													//bicia zwyk³ych - ruch czarnych - pola parzyste
								canBeat=true;
							}
							if(i<8 && j>1 && board[i+1][j-1]==1 && board[i+2][j-2]==0) {
								canBeat=true;
							}
							if(i<8 && j<8 && board[i+1][j+1]==1 && board[i+2][j+2]==0) {
								canBeat=true;
							}
							if(i>1 && j<8 && board[i-1][j+1]==1 && board[i-2][j+2]==0) {
								canBeat=true;
							}
							
							
							if(i>1 && j>1 && board[i-1][j-1]==3 && board[i-2][j-2]==0) {													//bicia damki - ruch czarnych - pola parzyste
								canBeat=true;
							}
							if(i<8 && j>1 && board[i+1][j-1]==3 && board[i+2][j-2]==0) {
								canBeat=true;
							}
							if(i<8 && j<8 && board[i+1][j+1]==3 && board[i+2][j+2]==0) {
								canBeat=true;
							}
							if(i>1 && j<8 && board[i-1][j+1]==3 && board[i-2][j+2]==0) {
								canBeat=true;
							}
						}
						
						if(board[i][j]==4) {
							canBeat=canKingBeat(i, j);
						}
						if (canBeat)
							break;
							}}
					}
				

	}
	
	public VBoard(VBoard copy) {
		whiteCtr=copy.whiteCtr;
		blackCtr=copy.blackCtr;
		whiteKingCtr=copy.whiteKingCtr;
		blackKingCtr=copy.blackKingCtr;
		side=copy.side;
		board=copy2d(copy.board);
	}
	
	ArrayList<VBoard> createMoves(){							//main func
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		Boolean beating=false;
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if(side) {
					if(board[i][j]==1) {
						if (canPawnBeat(i, j)) {
							out.addAll(pawnCanBeat(i, j));
							beating = true;
						}
					}
					if(board[i][j]==3) {
						if (canKingBeat(i, j)){
							out.addAll(kingCanBeat(i, j));
							beating = true;
						}
					}
				}
				else {
					if(board[i][j]==2) {
						if (canPawnBeat(i, j)){
							out.addAll(pawnCanBeat(i, j));
							beating = true;
						}
					}
					if(board[i][j]==4) {
						if (canKingBeat(i, j)){
							out.addAll(kingCanBeat(i, j));
							beating = true;
						}
					}
				}
			}
		}
		
		if (beating) {
			//System.out.println("wykrycie");
			for (int i=0; i<out.size(); i++) {
				for (int j=0; j<10; j++) {
					if (out.get(i).board[0][j]==1) {
						out.get(i).board[0][j]=3;
						out.get(i).whiteCtr--;
						out.get(i).whiteKingCtr++;
					}
					if (out.get(i).board[9][j]==2) {
						out.get(i).board[9][j]=4;
						out.get(i).blackCtr--;
						out.get(i).blackKingCtr++;
					}
				}
			}
			return findMostChanges(out);
		}
		else {
			out.clear();
			for (int i=0; i<10; i++) {
				for (int j=0; j<10; j++) {
					if(side) {
						if(board[i][j]==1) {
							out.addAll(pawnCanMove(i, j));
						}
						if(board[i][j]==3) {
							out.addAll(kingCanMove(i, j));
						}
					}
					else {
						if(board[i][j]==2) {
							out.addAll(pawnCanMove(i, j));
						}
						if(board[i][j]==4) {
							out.addAll(kingCanMove(i, j));
						}
					}
				}
			}
		}
		
		for (int i=0; i<out.size(); i++) {
			for (int j=0; j<10; j++) {
				if (out.get(i).board[0][j]==1) {
					out.get(i).board[0][j]=3;
					out.get(i).whiteCtr--;
					out.get(i).whiteKingCtr++;
				}
				if (out.get(i).board[9][j]==2) {
					out.get(i).board[9][j]=4;
					out.get(i).blackCtr--;
					out.get(i).blackKingCtr++;
				}
			}
		}
		
		return out;
	}
	
	Boolean canKingBeat(int i, int j){
		int it = i;
		int jt = j;
		
		
			if (it>1 && jt>1) {															//góra lewo
				it--;
				jt--;
				
				while (it>1 && jt>1 && board[it][jt]==0) {
					it--;
					jt--;
				}
				
				switch (board[it][jt]) {
					case 1: {
						if (board[i][j]==4 && board[it-1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==4 && board[it-1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==3 && board[it-1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==3 && board[it-1][jt-1]==0) {
							return true;
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt>1) {															//dó³ lewo
				it++;
				jt--;
				
				while (it<8 && jt>1 && board[it][jt]==0) {
					it++;
					jt--;
				}
				
				switch (board[it][jt]) {
					case 1: {
						if (board[i][j]==4 && board[it+1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==4 && board[it+1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==3 && board[it+1][jt-1]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==3 && board[it+1][jt-1]==0) {
							return true;
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt<8) {															//dó³ prawo
				it++;
				jt++;
				
				while (it<8 && jt<8 && board[it][jt]==0) {
					it++;
					jt++;
				}
				
				switch (board[it][jt]) {
					case 1: {
						if (board[i][j]==4 && board[it+1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==4 && board[it+1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==3 && board[it+1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==3 && board[it+1][jt+1]==0) {
							return true;
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it>1 && jt<8) {															//góra prawo
				it--;
				jt++;
				
				while (it>1 && jt<8 && board[it][jt]==0) {
					it--;
					jt++;
				}
				
				switch (board[it][jt]) {
					case 1: {
						if (board[i][j]==4 && board[it-1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==4 && board[it-1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==3 && board[it-1][jt+1]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==3 && board[it-1][jt+1]==0) {
							return true;
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}

		return false;
	}

	ArrayList<VBoard> kingCanBeat(int i, int j){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		
		out = kingCanBeatAgain(i, j, new VBoard(this));
		out = findMostChanges(out);
		
		return out;
	}
	
	ArrayList<VBoard> kingCanBeatAgain(int i, int j, VBoard before) {
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		if (!before.canKingBeat(i, j)) {
			before.side=!before.side;
			out.add(before);
			return out;
		}
		
		int it = i;
		int jt = j;
		
		
			if (it>1 && jt>1) {															//góra lewo
				it--;
				jt--;
				
				while (it>1 && jt>1 && before.board[it][jt]==0) {
					it--;
					jt--;
				}
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==4 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
							while(it2>=0 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2--;
								jt2--;
							}
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==4 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
							while(it2>=0 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								it2--;
								jt2--;
							}
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==3 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
							while(it2>=0 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2--;
								jt2--;
							}
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==3 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
							while(it2>=0 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								it2--;
								jt2--;
							}
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt>1) {															//dó³ lewo
				it++;
				jt--;
				
				while (it<8 && jt>1 && before.board[it][jt]==0) {
					it++;
					jt--;
				}
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==4 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
							while(it2<=9 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2++;
								jt2--;
							}
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==4 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
							while(it2<=9 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-3, before.blackKingCtr)));
								it2++;
								jt2--;
							}
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==3 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
							while(it2<=9 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2++;
								jt2--;
							}
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==3 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
							while(it2<=9 && jt2>=0 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								it2++;
								jt2--;
							}
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt<8) {															//dó³ prawo
				it++;
				jt++;
				
				while (it<8 && jt<8 && before.board[it][jt]==0) {
					it++;
					jt++;
				}
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==4 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
							while(it2<=9 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2++;
								jt2++;
							}
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==4 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
							while(it2<=9 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								it2++;
								jt2++;
							}
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==3 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
							while(it2<=9 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2++;
								jt2++;
							}
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==3 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
							while(it2<=9 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								it2++;
								jt2++;
							}
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it>1 && jt<8) {															//góra prawo
				it--;
				jt++;
				
				while (it>1 && jt<8 && before.board[it][jt]==0) {
					it--;
					jt++;
				}
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==4 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
							while(it2>=0 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2--;
								jt2++;
							}
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==4 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
							while(it2>=0 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=4;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								it2--;
								jt2++;
							}
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==3 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
							while(it2>=0 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								it2--;
								jt2++;
							}
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==3 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
							while(it2>=0 && jt2<=9 && before.board[it2][jt2]==0) {
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=3;		//po³ po zbiciu
								out.addAll(kingCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								it2--;
								jt2++;
							}
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}

		
		return out;
	}
	
	int compare(VBoard com) {
		int out = 0;
		for (int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if (board[i][j]!=com.board[i][j])
					out++;
			}
		}
		return out;
	}
	
	ArrayList<VBoard> findMostChanges(ArrayList<VBoard> inp){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		int m=0;
		int n;
		
		for (VBoard in:inp) {
			n = compare(in);
			if (n>m)
				m=n;
		}
		
		for (VBoard in:inp) {
			if (m==compare(in))
				out.add(in);
		}
		
		for (int i=0; i<out.size(); i++) {
			for (int j=i+1; j<out.size(); j++) {
				if (out.get(i).compare(out.get(j))==0)
						out.remove(j);
			}
		}
		return out;
	}
	
	Boolean canPawnBeat(int i, int j) {
		
			if (i>1 && j>1) {															//góra lewo
				switch (board[i-1][j-1]) {
					case 1: {
						if (board[i][j]==2 && board[i-2][j-2]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==2 && board[i-2][j-2]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==1 && board[i-2][j-2]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==1 && board[i-2][j-2]==0) {
							return true;
						}
						break;
					}
				}
			}
			
			if (i<8 && j>1) {															//dó³ lewo
				switch (board[i+1][j-1]) {
					case 1: {
						if (board[i][j]==2 && board[i+2][j-2]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==2 && board[i+2][j-2]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==1 && board[i+2][j-2]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==1 && board[i+2][j-2]==0) {
							return true;
						}
						break;
					}
				}
			}
			
			if (i<8 && j<8) {															//dó³ prawo
				switch (board[i+1][j+1]) {
					case 1: {
						if (board[i][j]==2 && board[i+2][j+2]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==2 && board[i+2][j+2]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==1 && board[i+2][j+2]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==1 && board[i+2][j+2]==0) {
							return true;
						}
						break;
					}
				}
			}
			
			if (i>1 && j<8) {															//góra prawo
				switch (board[i-1][j+1]) {
					case 1: {
						if (board[i][j]==2 && board[i-2][j+2]==0) {
							return true;
						}
						break;
					}
					case 3: {
						if (board[i][j]==2 && board[i-2][j+2]==0) {
							return true;
						}
						break;
					}
					case 2: {
						if (board[i][j]==1 && board[i-2][j+2]==0) {
							return true;
						}
						break;
					}
					case 4: {
						if (board[i][j]==1 && board[i-2][j+2]==0) {
							return true;
						}
						break;
					}
				}
			}

		return false;
	}

	ArrayList<VBoard> pawnCanBeatAgain(int i, int j, VBoard before){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		if (!before.canPawnBeat(i, j)) {
			before.side=!before.side;
			out.add(before);
			return out;
		}
		
		int it = i;
		int jt = j;
		
		
			if (it>1 && jt>1) {															//góra lewo
				it--;
				jt--;
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==2 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ pionka
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==2 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==1 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==1 && before.board[it-1][jt-1]==0) {
							int it2 = it-1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt>1) {															//dó³ lewo
				it++;
				jt--;
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==2 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==2 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-3, before.blackKingCtr)));
								
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==1 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==1 && before.board[it+1][jt-1]==0) {
							int it2 = it+1;
							int jt2 = jt-1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it<8 && jt<8) {															//dó³ prawo
				it++;
				jt++;
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==2 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==2 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==1 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==1 && before.board[it+1][jt+1]==0) {
							int it2 = it+1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
			if (it>1 && jt<8) {															//góra prawo
				it--;
				jt++;
				
				switch (before.board[it][jt]) {
					case 1: {
						if (before.board[i][j]==2 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr-1, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 3: {
						if (before.board[i][j]==2 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=2;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr-1, before.blackKingCtr)));
								
						}
						break;
					}
					case 2: {
						if (before.board[i][j]==1 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr-1, before.side, before.whiteKingCtr, before.blackKingCtr)));
								
						}
						break;
					}
					case 4: {
						if (before.board[i][j]==1 && before.board[it-1][jt+1]==0) {
							int it2 = it-1;
							int jt2 = jt+1;
								int[][] tmp = copy2d(before.board);
								tmp[i][j]=0;			//po³ damki
								tmp[it][jt]=0;			//po³ zbijanego
								tmp[it2][jt2]=1;		//po³ po zbiciu
								out.addAll(pawnCanBeatAgain(it2, jt2, new VBoard(tmp, before.whiteCtr, before.blackCtr, before.side, before.whiteKingCtr, before.blackKingCtr-1)));
								
						}
						break;
					}
				}
				
				it=i;
				jt=j;
			}
		
		return out;
	}

	ArrayList<VBoard> pawnCanBeat(int i, int j){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		
		out = pawnCanBeatAgain(i, j, new VBoard(this));
		out = findMostChanges(out);
		
		return out;
	}
	
	ArrayList<VBoard> kingCanMove (int i, int j){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
				
		if (i>0 && j>0) {										//lewo góra
			int it = i-1;
			int jt = j-1;
			while (it>=0 && jt>=0 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
				it--;
				jt--;
			}
		}
		
		if (i<9 && j>0) {										//lewo dó³
			int it = i+1;
			int jt = j-1;
			while (it<=9 && jt>=0 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
				it++;
				jt--;
			}
		}
		
		if (i<9 && j<9) {										//prawo dó³
			int it = i+1;
			int jt = j+1;
			while (it<=9 && jt<=9 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
				it++;
				jt++;
			}
		}
		
		if (i>0 && j<9) {										//prawo góra
			int it = i-1;
			int jt = j+1;
			while (it>=0 && jt<=9 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
				it--;
				jt++;
			}
		}
		return out;
	}
	
	ArrayList<VBoard> pawnCanMove (int i, int j){
		ArrayList<VBoard> out = new ArrayList<VBoard>();
		
		if (i>0 && j>0) {										//lewo góra
			int it = i-1;
			int jt = j-1;
			if (board[it][jt]==0 && board[i][j]==1) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
			}
		}
		
		if (i<9 && j>0) {										//lewo dó³
			int it = i+1;
			int jt = j-1;
			if (board[i][j]==2 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
			}
		}
		
		if (i<9 && j<9) {										//prawo dó³
			int it = i+1;
			int jt = j+1;
			if (board[i][j]==2 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
			}
		}
		
		if (i>0 && j<9) {										//prawo góra
			int it = i-1;
			int jt = j+1;
			if (board[i][j]==1 && board[it][jt]==0) {
				int[][]tmp = copy2d(board);
				tmp[it][jt]=board[i][j];
				tmp[i][j]=0;
				out.add(new VBoard(tmp, whiteCtr, blackCtr, !side, whiteKingCtr, blackKingCtr));
			}
		}
		/*for (VBoard o:out) {
			for (int jj=0; j<10; j++) {
				if(o.board[0][jj]==1) {
					o.board[0][jj]=3;
					o.whiteCtr--;
					o.whiteKingCtr++;
				}
				if(o.board[9][jj]==2) {
					o.board[9][jj]=4;
					o.blackCtr--;
					o.blackKingCtr++;
				}
			}
		}*/
		return out;
	}
	
	void showBoard (){
		for (int i=0; i<10; i++) {
			System.out.print(i + "|");
			for (int j=0; j<10; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(whiteCtr + " " + whiteKingCtr + " " + blackCtr + " " + blackKingCtr + " " + side + " " + canBeat);
	}
	
	int [][] copy2d (int[][] inp){
		int [][] out = new int[10][10];
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				out[i][j]=inp[i][j];
			}
		}
		return out;
	}
}

