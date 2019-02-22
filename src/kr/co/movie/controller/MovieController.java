package kr.co.movie.controller;

import java.util.Scanner;

import kr.co.movie.controller.moviecontroller.Controller;
import kr.co.movie.controller.moviecontroller.ListMovieController;
import kr.co.movie.controller.moviecontroller.ListMovieReservationController;
import kr.co.movie.controller.moviecontroller.MovieReservationController;
import kr.co.movie.controller.moviecontroller.ReleasedMovieAdmin;
import kr.co.movie.controller.usercontroller.UpdateMemberController;
import kr.co.movie.dao.MovieDAO;
import kr.co.movie.dao.SeatDAO;

public class MovieController {
	String id = null;
	private boolean isAdmin = false;
	int serial;
	public MovieController(int l) {
		this.serial = l;
	}
	
	public MovieController(String id) {
		this.id = id;
		if(id.equals("admin")) {
			isAdmin = true;
		}
		serial = 0;
	}
	
	public Scanner sc = new Scanner(System.in);  // 사용자 입력..

	MovieDAO movieDAO = null;
	SeatDAO seatDAO = null;

	private int choiceMenu() {
		System.out.println("-----------------");
		System.out.println("1. 현재 상영중");
		System.out.println("2. 영화 예매");
		System.out.println("3. 티켓 조회");
		if(id != null) System.out.println("4. 회원 정보 수정");
//		System.out.println("5. 이벤트");
		if(isAdmin) {
			System.out.println("6. 관리자 메뉴");			
		}
		System.out.println("0. 로그아웃");
		System.out.println("-----------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public void service() {
		if(id == null) {
			System.out.println("환영합니다. " + serial + "님!");
		}else {
			System.out.println("환영합니다. " + id + "님!");
		}
		try {
			System.out.println("-----------------");
			while (true) {
				
				Controller ctrl = null;
				switch (choiceMenu()) {
				case 1:  // 현재 상영중
					ctrl = new ListMovieController(movieDAO);
					break;
				case 2:  // 영화 예매
					if(id == null ) ctrl = new MovieReservationController(movieDAO,seatDAO,serial);
					else if(serial == 0) ctrl = new MovieReservationController(movieDAO,seatDAO,id);
					break;
				case 3:  // 티켓 조회
					if(id == null ) ctrl = new ListMovieReservationController(serial);
					else if(serial == 0) ctrl = new ListMovieReservationController(id);
					break;
				case 4:  // 회원 정보 수정
					if(id != null) {
						new UpdateMemberController(id).service();	
						break;
					}
					
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");												
					
					break;
					/*
				case 5:  // 이벤트
//					ctrl = new 
					break;
 */
				case 6:
					if(isAdmin) {
						ctrl = new ReleasedMovieAdmin();
						break;
					}
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");						
					break;
				case 0:  // 로그 아웃
					return;
				default:
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");
				}
				
				if (ctrl != null) {
					ctrl.service();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}










