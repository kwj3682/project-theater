package kr.co.movie.controller;

import java.util.Scanner;

import kr.co.movie.controller.usercontroller.Controller;
import kr.co.movie.controller.usercontroller.LogInController;
import kr.co.movie.controller.usercontroller.NonLogInController;
import kr.co.movie.controller.usercontroller.SignInController;
import kr.co.movie.dao.MemberDAO;
import kr.co.movie.dao.NonMemberDAO;

public class MemberController {
	MemberDAO memberDAO;
	NonMemberDAO nDAO;
	Scanner sc = new Scanner(System.in);
	public MemberController(){

	}
	private void exit() {
		System.out.println("종료 합니다");
		// 프로세스 중단.
		System.exit(0);
	}
	
	private int choiceMenu() {
		/*
			메인화면
			--------------------------------------------------------------
			1.회원 로그인
			2.비회원 예매
			3.회원가입
			0. 종료
			--------------------------------------------------------------
			선택:_
		 */
		System.out.println("메인화면");
		System.out.println("-----------------");
		System.out.println("1.회원 로그인");
		System.out.println("2.비회원 예매");
		System.out.println("3. 회원가입");
//		System.out.println("3.아이디 찾기");
//		System.out.println("4. 비밀번호 찾기");
		System.out.println("0. 종료");
		System.out.println("-----------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public void service() {
		try {
			System.out.println("-----------------");
			while (true) {
				
				Controller user = null;
				
				switch (choiceMenu()) {
				case 1:  // 로그인
					user = new LogInController(memberDAO);
					break;
				case 2:  // 비회원 예매
					user = new NonLogInController(nDAO);
					break;
				case 3:  // 회원 가입
					user = new SignInController(memberDAO);
					break;
				case 0:  // 종료
					exit();
				default:
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");
				}
				
				if (user != null) {
					user.service();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MemberController().service();
	}
}
