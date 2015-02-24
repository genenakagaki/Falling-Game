import javax.swing.JOptionPane;
public class Test {

	public static void main(String[] args) {
		process();
	}
	
	public static void process(){
		int first  = 0;
		int second = 0;
		String input;
		
		//Process first input
		input = JOptionPane.showInputDialog("Input an integer.");
		if (input == null || isStop(input)){
			System.exit(0);
		}else if (isNumber(input)){
			first = Integer.parseInt(input);
		}else{
			JOptionPane.showMessageDialog(null, "Wrong integer input. Try again.");
			process();
		}

		//Process second input
		input = JOptionPane.showInputDialog("Input second integer.");
		if (input == null || isStop(input)){
			System.exit(0);
		}else if (isNumber(input)){
			second = Integer.parseInt(input);
		}else{
			JOptionPane.showMessageDialog(null, "Wrong integer input. Try again.");
			process();
		}
		
		//Process third inputs
		input = JOptionPane.showInputDialog("Input the required operation.");
		if (input == null || isStop(input)){
			System.exit(0);
		}else if (isOperator(input)){
			if (input.equals("+")){
				add(first,second);
				process();
			} else if (input.equals("-")){
				sub(first,second);
				process();
			} else if (input.equals("*")){
				mult(first,second);
				process();
			} else if (input.equals("/")){
				if (second != 0) {
				div(first,second);
				process();
				}else{ process(); }
			} else {
				exp(first,second);
				process();
			}
		}else{
			JOptionPane.showMessageDialog(null, "Invalid operator. Try again.");
		}
	}
	
	public static boolean isStop(String input){
		return (input.equalsIgnoreCase("stop"));
	}
	
	public static boolean isNumber(String x){
		int check;
		try{
			check = Integer.parseInt(x);
		}catch(NumberFormatException nfe){
			return false;
		}
		if ( check <= 0 || check >= 0 ){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isOperator(String x){
		if ( x.equals("*") || x.equals("/") || x.equals("+") || x.equals("-") || x.equals("^")){
			return true;
		}else{
			return false;
		}
	}
	
	public static int add(int x, int y){
		int result = 0;
		result = x + y;
		JOptionPane.showMessageDialog(null, x + " + " + y + " = " + result);
		return result;
		
	}
	public static int sub(int x, int y){
		int result = 0;
		result = x - y;
		JOptionPane.showMessageDialog(null, x + " - " + y + " = " + result);
		return result;
	}
	public static int mult(int x, int y){
		int result = 0;
		result = x * y;
		JOptionPane.showMessageDialog(null, x + " * " + y + " = " + result);
		return result;
	}
	public static double div(int x, int y){
		double result = 0;
		result = (double)x / y;
		JOptionPane.showMessageDialog(null, x + " / " + y + " = " + result);
		return result;
		}
	public static int exp(int x, int y){
		int result = 0;
		result = (int) Math.pow( x , y );
		JOptionPane.showMessageDialog(null, x + " ^ " + y + " = " + result);
		return result;
	}

}