import java.util.Scanner;
import java.util.StringTokenizer;


public class calculator {
	public static int n=100;
	public static int[] array=new int[n];
	public static int[] newArray=new int[n*2];
	public static int top=0;
	
	public static void main(String[] args) {

		Scanner keyboard=new Scanner(System.in);
		System.out.println("Enter an expression in RPN format");
		
		String input,temp;
		StringTokenizer myToken;
		int toInt,result=0;
		for (;;){
			input=keyboard.nextLine();
			if (input.matches("")){
				keyboard.close();
				break;
			}
			myToken=new StringTokenizer(input," ");
			while (myToken.hasMoreTokens()){
					temp=myToken.nextToken();
	
					if (temp.matches("[0-9]+")){
						toInt=Integer.parseInt(temp);
						push(toInt);
					}
					if(temp.matches("(\\+)")){
						push(pop() + pop());
					}
					if (temp.matches("(-)")){
						subtract();
					}
					if (temp.matches("(/)")){
						divide();
					}
					if (temp.matches("(\\*)")){
						push(pop()*pop());
					}
					if(temp.matches("(=)")){
						result=pop();
						if (isEmpty()){
							System.out.println("result is "+result);
						}else{
							incompleteExpression();
						}
					}
				}
			}
		}
	
	static void push(int operand){
		if (isFull()){
			extend();
		}
		array[top++]=operand;
	}
		
	static int pop(){
		if (isEmpty()){
			incompleteExpression();
		}
		return array[--top];
	}	
	
	static void extend(){
		n*=2;
		System.arraycopy(array,0,newArray,0,array.length);
		array=newArray;
	}
	
	static void subtract(){
		int subtrahend=pop();
		int minuend=pop();
		push (minuend-subtrahend);
	}
	
	static void divide(){
		int divisor=pop();
		int dividend=pop();
		push(dividend/divisor);
	}
	
	static void top(){
		int result=top-1;
		System.out.println("top is at index " + result);
	}
	
	static void size(){
		System.out.println("size is "+top);
	}
	
	static Boolean isEmpty(){
		return (top==0);
	}

	static Boolean isFull(){
		return (top==n);
	}
	
	static void incompleteExpression(){
		System.out.println("Incomplete expression");
		System.exit(0);
	}

}
