package kr.co.movie.controller.usercontroller;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.controller.MovieController;
import kr.co.movie.dao.MemberDAO;
import kr.co.movie.domain.Member;

public class LogInController extends Controllers{
	String id;
	String password;
	MemberDAO memberDAO;
	public LogInController(MemberDAO md) {
		memberDAO = md;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		memberDAO = session.getMapper(MemberDAO.class);
	}
	
	public void service() {
		System.out.println("로그인");
		System.out.println("------------------------------------");
		id = input("아이디 : ");
		password = input("비밀번호 : ");
		Member m = new Member();
		m.setId(id);
		m.setPassword(password);
		m = memberDAO.selectMemberForLogIn(m);
		if(m == null) {
			System.out.println("로그인 정보를 확인해주세요.");
			return;
		}else{
			System.out.println(m.getId()+ "님, 예매페이지로 이동합니다.");
			new MovieController(m.getId()).service();
			System.out.println("------------------------------------");
			return;
		}
	}
}
