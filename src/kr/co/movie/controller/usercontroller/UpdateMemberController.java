package kr.co.movie.controller.usercontroller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.HistoryDAO;
import kr.co.movie.dao.MemberDAO;
import kr.co.movie.domain.History;
import kr.co.movie.domain.Member;

public class UpdateMemberController extends Controllers{
	private SimpleDateFormat sdf;
	private String id;
	private MemberDAO mDAO;
	private HistoryDAO hDAO;
	public UpdateMemberController(String id) {
		this.id = id;
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mDAO = session.getMapper(MemberDAO.class); 
		hDAO = session.getMapper(HistoryDAO.class);
	}
	
	public void service() {
		System.out.println(id + "님의 회원 정보 입니다.");
		System.out.println("--------------------------------------------");
		Member m = mDAO.selectMemberById2(id);
		System.out.println("아이디 : " + m.getId());
		System.out.println("비밀번호: " + m.getPassword());
		System.out.println("이름: " + m.getmName());
		System.out.println("이메일: " + m.getMail());
		System.out.println("전화번호: " + m.getPhoneNumber());
		System.out.println("생년월일: " + sdf.format(m.getBirth()));
		System.out.println("주소: " + m.getAddress());
		System.out.println("보유 포인트: " + m.getPoint());
		System.out.println("--------------------------------------------");
		System.out.println("히스토리");
		System.out.println("--------------------------------------------");
		List<History> list = hDAO.selectHistoryList(id);
		for(History h : list) {
			System.out.println("영화 제목: " + h.getTitle() + " 상영날짜 : " + h.getPlayTime());
		}
		System.out.println("--------------------------------------------");
		while(true) {
			System.out.println("1.비밀번호 수정");
			System.out.println("0.이전");
			System.out.println("--------------------------------------------");
			int sel = Integer.parseInt(input("메뉴 중 처리할 항목을 선택하세요 : "));
			switch(sel) {
			case 1:
				String pass = input("변경할 비밀번호를 입력하세요 : ");
				if(pass == null) {
					System.out.println("비밀번호가 입력되지 않았습니다.");
					continue;
				}
				m.setPassword(pass);
				mDAO.updatePassword(m);
				System.out.println("비밀번호가 변경되었습니다.");
				return;
			case 0:
				return;
			default : 
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
