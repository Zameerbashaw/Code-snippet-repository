package newproject1;
import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("enter the number of subjects");
		int totalsubjects = scanner.nextInt();
		
		int totalmarks = 0;
		
		for(int i=0; i < totalsubjects; i++) {
			System.out.println("enter marks for the subject" +(i+1));
			int marks = scanner.nextInt();
			totalmarks  +=marks;
		}
		
		double average = totalmarks/totalsubjects;
		String grade = calculateGrade(average);
		
		      System.out.printf("total marks of the student = ",totalmarks);
	          System.out.printf("average percentage of the student = ",average);
	          System.out.printf("grade obtained by the  student = ",grade);  
		}
		
	static String calculateGrade(double average) {
		String grade;
			if(average>=90) {
				grade = ("O");
			}else if(average>=80) {
				grade = ("A+");
			}else if(average>=70) {
				grade = ("A");
			}else if (average>=60) {
				grade = ("B+");
	        }else if (average>=50) {
				grade = ("B");
	        }else if (average>=40) {
				grade = ("C");
	        } else if (average >= 30) {
				grade = ("D");
			} else {
				grade = ("F");
			}
			return grade;
	
	}
	
		
         
	}


	
	
	