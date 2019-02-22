package kr.co.movie.controller.usercontroller;

import java.util.Scanner;

public abstract class Controllers implements Controller{
	public Scanner sc = new Scanner(System.in);
	protected String input(String str) {
		System.out.print(str);
		return sc.nextLine();
	}
}
