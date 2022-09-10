import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.*;
import java.math.*;

public class chess {
	private static String[][] board;
	private static int total_A;
	private static int total_B;
	private static String[] A_values;
	private static String[] B_values;
	private static List<String> presentstatesA;
	private static List<String> presentstatesB;
	
	public void setTotalA(){
		this.total_A = 5;
	}
	
	public void setTotalB(){
		this.total_B = 5;
	}
	
	public int getTotalA(){
		return total_A;
	}
	
	public int getTotalB(){
		return total_B;
	}
	
	public void setboard(String[][] t) {
		this.board = t;
	}
	
	public String[][] getboard(){
		return board;
	}
	
	public void setGridforA(String s) {
		String[] A = s.split(" ");
		for(int i=0;i<5;++i) {
			board[4][i] = A[i];
			presentstatesA.add(A[i]);
		}
	}
	
	public void setGridforB(String s) {
		String[] B = s.split(" ");
		for(int i=0;i<5;++i) {
			board[0][i] = B[i];
			presentstatesB.add(B[i]);
		}
	}
	
	public void printState() {
		for(int i=0;i<board.length;++i) {
			for(int j=0;j<board[i].length;++j) {
				System.out.print(board[i][j]+"		");
			}
			System.out.println();
		}
	}
	
	public boolean checkinput(String input) {
		String[] in = input.split(":");
		if(in.length!=2)	return false;
		return true;
	}
	
	public boolean checkupward(int r, int c) {
		return r!=4;
	}
	
	public boolean checkdown(int r, int c) {
		return r!=0;
	}
	
	public boolean checkleft(int r, int c) {
		return c!=0;
	}
	
	public boolean checkright(int r, int c) {
		return c!=4;
	}
	
	public static void main(String args[]) {
		Scanner S = new Scanner(System.in);
		board = new String[5][5];
		presentstatesB = new ArrayList();
		presentstatesA = new ArrayList();
		chess object = new chess();
		String[][] temporary = new String[5][5];
		int row=0,col=0;
		for(row = 0;row < 5; ++row) {
			for(col = 0;col < 5; ++col) {
				temporary[row][col] = "";
			}
		}
		object.setboard(temporary);
		System.out.println("Welcome to Chess Game -5x5");
		System.out.println("****************************************************************************");
		System.out.println("Please enter your inputs : ");
		System.out.println("Player1[A] Input: ");
		String inputOfA = S.nextLine();
		object.setGridforA(inputOfA);
		object.printState();
		System.out.println("Player2[B] Input: ");
		String inputOfB = S.nextLine();
		object.setGridforB(inputOfB);
		object.printState();//grid 
		object.setTotalA();//Total for A
		object.setTotalB();
		System.out.println("*****************************************************************************");
		System.out.println("Game is about to start");
		System.out.println("A will go first and then B and so on !");
		System.out.println("AIM : Remove all pieces of your opponent");
		System.out.println("*****************************************************************************");
		System.out.println("GAME STARTS !");
		int order = 0;
		String curr = "";
		while(true) {
			if(order==0)	System.out.println("Player A’s Move:");
			else	System.out.println("Player B’s Move:");
			String input = S.nextLine();
			if(!object.checkinput(input)) {
				/*while(!object.checkinput(input)) {
					System.out.println("Invalid input");
					input = S.nextLine();
				}*/
			}
			String[] move = input.split(":");
			if(order == 0) {
				curr = "A";
				order = 1;
			}
			else {
				curr = "B";
				order = 0;
			}
			if(curr.equals("A")) {
				if(!presentstatesA.contains(move[0])) {//WHEN THAT PIECE IS ELIMINATED
					/*while(!object.checkinput(input)) {
						System.out.println("Invalid input 1");
						input = S.nextLine();
					}*/
					move = input.split(":");
				}
				else {//PIECE FOUND
					boolean cond1 = false;
					int foundROW = 0, foundCOL = 0;
					for(row = 4;row>=0;--row) {
						for(col = 0;col<5;++col) {
							if(board[row][col].equals(move[0])) {
								foundROW = row;
								foundCOL = col; 
								cond1 =true;
								break;
							}
						}
						if(cond1)	break;
					}
					String temp="",temp2="";
					if(move[1].equals("F")) {
						if(!object.checkupward(row, col)) {
							temp = board[foundROW][foundCOL];
							board[foundROW][foundCOL] = "";
							if(!board[foundROW+1][foundCOL].equals("")) {
								temp2 = board[foundROW+1][foundCOL];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW+1][foundCOL] = temp;
							}
						}
					}
					else if(move[1].equals("B")) {
						if(!object.checkdown(row, col)) {
							temp = board[foundROW][foundCOL];
							board[foundROW][foundCOL] = "";
							if(!board[foundROW-1][foundCOL].equals("")) {
								temp2 = board[foundROW-1][foundCOL];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW-1][foundCOL] = temp;
							}
						}
					}
					else if(move[1].equals("L")) {
						if(!object.checkleft(row, col)) {
							temp = board[foundROW][foundCOL];
							board[foundROW][foundCOL] = "";
							if(!board[foundROW][foundCOL-1].equals("")) {
								temp2 = board[foundROW][foundCOL-1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW][foundCOL-1] = temp;
							}
						}
					}
					else if(move[1].equals("R")) {
						if(!object.checkright(row, col)) {
							temp = board[foundROW][foundCOL];
							board[foundROW][foundCOL] = "";
							if(!board[foundROW][foundCOL+1].equals("")) {
								temp2 = board[foundROW][foundCOL+1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW][foundCOL+1] = temp;
							}
						}
					}
					
				}
				object.printState();
			}
			else {
				if(!presentstatesB.contains(move[0])) {//WHEN THAT PIECE IS ELIMINATED
					while(!object.checkinput(input)) {
						System.out.println("Invalid input 2");
						input = S.nextLine();
					}
					move = input.split(":");
				}
				else {//PIECE FOUND
					boolean cond2 = false;
					int foundROW1 = 0, foundCOL1 = 0;
					for(row = 0;row<5;++row) {
						for(col = 0;col<5;++col) {
							if(board[row][col].equals(move[0])) {
								foundROW1 = row;
								foundCOL1 = col; 
								cond2 =true;
								break;
							}
						}
						if(cond2)	break;
					}
					String temp="",temp2="";
					if(move[1].equals("F")) {
						if(!object.checkupward(row, col)) {
							temp = board[foundROW1][foundCOL1];
							board[foundROW1][foundCOL1] = "";
							if(!board[foundROW1+1][foundCOL1].equals("")) {
								temp2 = board[foundROW1+1][foundCOL1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW1+1][foundCOL1] = temp;
							}
						}
					}
					else if(move[1].equals("B")) {
						if(!object.checkdown(row, col)) {
							temp = board[foundROW1][foundCOL1];
							board[foundROW1][foundCOL1] = "";
							if(!board[foundROW1-1][foundCOL1].equals("")) {
								temp2 = board[foundROW1-1][foundCOL1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW1-1][foundCOL1] = temp;
							}
						}
					}
					else if(move[1].equals("L")) {
						if(!object.checkleft(row, col)) {
							temp = board[foundROW1][foundCOL1];
							board[foundROW1][foundCOL1] = "";
							if(!board[foundROW1][foundCOL1-1].equals("")) {
								temp2 = board[foundROW1][foundCOL1-1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW1][foundCOL1-1] = temp;
							}
						}
					}
					else if(move[1].equals("R")) {
						if(!object.checkright(row, col)) {
							temp = board[foundROW1][foundCOL1];
							board[foundROW1][foundCOL1] = "";
							if(!board[foundROW1][foundCOL1+1].equals("")) {
								temp2 = board[foundROW1][foundCOL1+1];
								total_B--;
								presentstatesB.remove(String.valueOf(temp2));
							}
							else {
								board[foundROW1][foundCOL1+1] = temp;
							}
						}
					}

				}
				object.printState();
			
			}
			if(total_A == 0) {
				System.out.println("GAME OVER!");
				System.out.println("B WINNER!");
				break;
			}
			else if(total_B == 0) {
				System.out.println("GAME OVER!");
				System.out.println("A WINNER!");
				break;
			} 
		}
		
	}
}
