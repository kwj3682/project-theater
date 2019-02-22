package kr.co.movie.dao;

import kr.co.movie.domain.Member;

public interface MemberDAO {
	String selectMemberById(String id);
	Member selectMemberById2(String id);
	void insertMember(Member member);
	Member selectMemberForLogIn(Member m);
	void deleteMember(Member member);
	void updatePassword(Member member);
}
