package kr.co.movie.controller.usercontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.controller.MovieController;
import kr.co.movie.dao.NonMemberDAO;
import kr.co.movie.domain.NonMember;

public class NonLogInController extends Controllers{
	String name;
	int serial;
	NonMemberDAO nDAO;
	public NonLogInController(NonMemberDAO nd) {
		nDAO = nd;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		nDAO = session.getMapper(NonMemberDAO.class);
	}
	
	
	public void service() {
		int sel = Integer.parseInt(input("시리얼 넘버를 발급 받으시겠습니까? (1 -> 발급, 0 -> 기존 시리얼넘버로 비회원 예매) : "));
		if(sel == 1) {

			NonMember tmp = new NonMember();
			tmp.setNmName(input("이름 : "));
			tmp.setPhoneNumber(input("전화 번호 :"));
			nDAO.insertNonMember(tmp);
			List<NonMember> list = new ArrayList<>();
			
			list = nDAO.selectNonMemberByPhoneNumber(tmp);
			for(NonMember nm : list) {
				System.out.println(nm.getNmName());
				System.out.println(nm.getPhoneNumber());
				System.out.println(nm.getSerial());
			}
			if(list.size() > 0)
			tmp = list.get(list.size()-1);
			else return;
			System.out.println("발급 완료!");	
			System.out.println(tmp.getNmName()+"님의 시리얼 넘버는 " + tmp.getSerial()+"입니다.");
		}else if(sel == 0){			
			System.out.println("비회원 예매 시리얼 넘버");
			serial = Integer.parseInt(input("시리얼넘버 : "));
			NonMember m = nDAO.selectNonMemberBySerial(serial);
			if(m == null) {
				System.out.println("시리얼 넘버를 확인해주세요.");
				return;
			}else{
				System.out.println("비회원 " + m.getSerial()+ "번 " + m.getNmName()+ "님, 예매페이지로 이동합니다.");
				new MovieController(m.getSerial()).service();
				System.out.println("------------------------------------");
				return;
			}
		}else {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
	}
}
