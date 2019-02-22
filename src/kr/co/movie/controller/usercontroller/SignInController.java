package kr.co.movie.controller.usercontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.MemberDAO;
import kr.co.movie.domain.Member;

public class SignInController extends Controllers{
	SimpleDateFormat sdf;
	Date d;
	MemberDAO dao;
	public SignInController(MemberDAO d) {
		dao = d;
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(MemberDAO.class);
	}
	//id,password,m_name, address, birth, phone_number, point,mail,is_ten

	public void service() {
		System.out.println("회원가입");
		System.out.println("------------------------------------");
		Member m = new Member();
		m.setId(input("아이디를 입력하세요 : "));
		m.setPassword(input("패스워드를 입력하세요 : "));
		m.setmName(input("이름을 입력하세요 : "));
		m.setAddress(input("주소를 입력하세요 : "));
		String birth = input("생년월일을 입력하세요 : (입력 예> 1990/01/01 -> 1990/01/01)");
		if(birth.length() != 10 || !birth.contains("/")) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		try {
			d = sdf.parse(birth);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.setBirth(d);
		m.setPhoneNumber(input("전화번호를 입력하세요 : "));
		m.setMail(input("메일 주소를 입력하세요 : "));		
		System.out.println("------------------------------------");
		if(m.getAddress() == null || m.getBirth() == null || m.getId() == null || m.getMail() == null || m.getmName() == null ||
				m.getPassword() == null || m.getPhoneNumber() == null) {
			System.out.println("누락된 정보가 존재합니다. 확인하고 다시 시도해주세요.");
			return;
		}
		String idCheck = dao.selectMemberById(m.getId());
		if(idCheck == null) {
			dao.insertMember(m);
			System.out.println("회원가입이 완료되었습니다.");
			System.out.println("------------------------------------");
			return;
		}else {
			System.out.println("이미 존재하는 아이디입니다.");
			System.out.println("------------------------------------");
			return;
		}
		
		
	}
}
