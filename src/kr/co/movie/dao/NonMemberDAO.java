package kr.co.movie.dao;

import java.util.List;

import kr.co.movie.domain.NonMember;

public interface NonMemberDAO {
	NonMember selectNonMemberBySerial(int serial);
	List<NonMember> selectNonMemberByPhoneNumber(NonMember nonmember);
	void insertNonMember(NonMember nonmember);
}
