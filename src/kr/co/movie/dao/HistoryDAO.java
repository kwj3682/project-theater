package kr.co.movie.dao;

import java.util.List;

import kr.co.movie.domain.History;

public interface HistoryDAO {
	void insertHistory(History h);	
	List<History> selectHistoryList(String id);
}
