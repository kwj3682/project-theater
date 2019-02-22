package kr.co.movie.controller.moviecontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.movie.common.db.MyAppSqlConfig;
import kr.co.movie.dao.HistoryDAO;
import kr.co.movie.dao.MovieDAO;
import kr.co.movie.dao.ReservationDAO;
import kr.co.movie.dao.SeatDAO;
import kr.co.movie.domain.DistinctSeat;
import kr.co.movie.domain.History;
import kr.co.movie.domain.ReleasedMovie;
import kr.co.movie.domain.Reservation;
import kr.co.movie.domain.Seat;

public class MovieReservationController extends Controllers {
	List<DistinctSeat> list;
	private MovieDAO dao;
	private SeatDAO dao2;
	private HistoryDAO hDAO;
	private ReservationDAO dao3;
	private int theaterNo = 0;
	private int serial;
	private String id;
	private String movieTitle = "";
	private String pts = "";
	private String show = "";
	private SimpleDateFormat sdf,sdf2;
	private ReleasedMovie movie;
	
	public MovieReservationController(MovieDAO md, SeatDAO sd, String id) {
		this.id = id;
		serial = 0;
		movie = null;
		dao = md;
		dao2 = sd;
		list = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(MovieDAO.class);
		dao2 = session.getMapper(SeatDAO.class);
		dao3 = session.getMapper(ReservationDAO.class);
		hDAO = session.getMapper(HistoryDAO.class);
	} 
	public MovieReservationController(MovieDAO md, SeatDAO sd, int serial) {
		this.serial = serial;
		id = null;
		movie = null;
		dao = md;
		dao2 = sd;
		list = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SqlSession session = MyAppSqlConfig.getSqlSession();
		dao = session.getMapper(MovieDAO.class);
		dao2 = session.getMapper(SeatDAO.class);
		dao3 = session.getMapper(ReservationDAO.class);
	} 
	
	public List<ReleasedMovie> selectReleasedMovieList() {
		List<ReleasedMovie> list = dao.selectReleasedMovieList();

		return list;
	}
	public ReleasedMovie selectMovieByNo(int no) {
		return dao.selectMovieByNo(no);
	}
	
	public String getCalDate(int i) {
		String date = sdf.format(new Date());  // 시작 날짜
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, i);
		return sdf.format(c.getTime());
	}
	

	public Date getCalDate2(String str) throws Exception {
		return sdf.parse(str);
	}
	
	
	
	
	public int selectMovie() {		
		int max = 0;
		System.out.println("-----------------------------------------");
		System.out.println("오늘 날짜 "+sdf.format(new Date()));
		System.out.println("-----------------------------------------");
		List<ReleasedMovie> list = selectReleasedMovieList();
		List<Integer> idx = new ArrayList<>();
		for(ReleasedMovie r : list) {
			idx.add(r.getMovieNo());
		}
		
		outer:
		while(true) {
			System.out.println("----------------------------------------------------");
			for(ReleasedMovie rm : list) {
				System.out.print(rm.getMovieNo()+ ". ");
				System.out.print(rm.getTheaterNo() + "관 ");
				System.out.print(" "+rm.getTitle());
				System.out.print("  " + rm.getRating()+"세 이상 관람가");
				System.out.println(" 개봉일: " + sdf.format(rm.getReleaseDate()));
			}
			System.out.println("0. 이전");
			System.out.println("----------------------------------------------------");
			int sel = Integer.parseInt(input("영화를 선택하세요 : "));
			if(sel==0) {
				System.out.println("종료합니다.");
				break outer;
				
			}else if(sel < 0) {
				System.out.println("잘못된 입력입니다. 다시 확인해주세요.");
				continue outer;
			}else {
				boolean flag = false;
				for(int i = 0; i < idx.size();i++) {
					if(sel == idx.get(i)) {
						flag = true;
						break;
					}
				}
				
				if(flag == false) {
					System.out.println("잘못된 입력입니다. 다시 확인해주세요.");
					continue outer;
				}
			}
			movie = selectMovieByNo(sel);
			movieTitle = movie.getTitle();
			theaterNo = movie.getTheaterNo();
			return 1;
		}
		return 0;
	}
	public List<DistinctSeat> selectDate() {
		while(true) {
			theaterNo = movie.getTheaterNo();
			System.out.println("----------------------------------------------------");
			System.out.println("-"+movieTitle+"-");
			System.out.println("-"+theaterNo+"관-");
			System.out.println("----------------------------------------------------");
			System.out.println("1. 당일 : " + getCalDate(0));
			System.out.println("2. 다음날 : " + getCalDate(1));
			System.out.println("3. 모레 : " + getCalDate(2));
			System.out.println("0. 이전");
			System.out.println("----------------------------------------------------");
			int sel = Integer.parseInt(input("날짜를 선택하세요 : "));
			DistinctSeat dseat = new DistinctSeat();
			switch(sel) {
			case 1:
				dseat.setPlayTimeString(getCalDate(0));
				dseat.setTheaterNo(theaterNo);
				list = dao2.selectSeatListDistinct(dseat);
//						for(DistinctSeat d : list) {
//							System.out.print(d.getTheaterNo() + " ");
//							System.out.print(d.getPlayTime() + " ");
//							System.out.println(d.getSeatCnt() + " ");
//						}
				return list;
			case 2:
				dseat.setPlayTimeString(getCalDate(1));
				dseat.setTheaterNo(theaterNo);
				list = dao2.selectSeatListDistinct(dseat);
				return list;
			case 3:
				
				dseat.setPlayTimeString(getCalDate(2));
				dseat.setTheaterNo(theaterNo);
				list = dao2.selectSeatListDistinct(dseat);
				return list;
			case 0:
				return null;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}		
	}
	
	public DistinctSeat selectTheater(List<DistinctSeat> l) {
		if(l == null) {
			return null;
		}
		l = list;
		String result = "";
		String tmpDate = "";
		inner:
			while(true) {
				System.out.println("-"+movieTitle+"-");
				System.out.println("-"+theaterNo+"관-");
				System.out.println("---------------------------------------------------------------------------");
				int i = 0;
				for(DistinctSeat d : l) {
					System.out.print((++i)+ ". "+sdf2.format(d.getPlayTime())+"\t");
					System.out.println("잔여석 : "+d.getSeatCnt());
					result += d.getPlayTime() + " ";
					tmpDate = sdf.format(d.getPlayTime());
				}
				System.out.println("---------------------------------------------------------------------------");
				String sel = input("원하는 상영시간을 선택해주세요. (입력 예> 09:00 영화 ===> 09:30 / [취소 : 0]) 입력: ");
				
				if(sel.equals("0")){
					return null;
				}else if(result.contains(sel) && sel.contains(":") && sel.length() == 5) {
//					System.out.println(result);
					DistinctSeat ds = new DistinctSeat();
					ds.setTheaterNo(theaterNo);
					pts = tmpDate + " "+sel;
					ds.setPlayTimeString(pts);
					ds = dao2.selectSeatListDistinctByNo(ds);
//					System.out.print(ds.getSeatCnt()+ " ");
//					System.out.print(ds.getTheaterNo()+ " ");
//					System.out.println(ds.getPlayTime());
					pts = sdf2.format(ds.getPlayTime());
					return ds;
				}
				else {
					System.out.println("해당 상영시간이 존재하지 않습니다.");
				}
			}
		
	}
	public Seat selectSeat(DistinctSeat ds) {
		if(ds == null) {
			return null;
		}
		int totalSeatCnt = dao2.getSeatCnt(theaterNo);
		int currentSeatCnt = ds.getSeatCnt();
		
		while(true) {			
			System.out.println(theaterNo+"관 - " + movieTitle);
			System.out.println("입장 시간 : " + pts);
			System.out.printf("잔여석 : [ %d / %d ]%n", currentSeatCnt, totalSeatCnt);
			int ten = 0;
			if(totalSeatCnt% 12 == 0) {
				ten = 12;
			}else {
				ten = 10;
			}
			char alpha = 'a';
			System.out.println("--------------------------------------------------------------------");
			System.out.print(" ");
			for(int i = 1; i <= ten; i++) {
				System.out.print(i + " ");
				if(i<10) {
					System.out.print(" ");
				}
				
			}
			System.out.println();
			
			for(int i = 1 ; i <= totalSeatCnt; i++) {
				System.out.print(" □ ");
				
				if(i %ten == 0) {
					System.out.println( " " + (alpha++) );
				}
				if(i%(ten*3) == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println("--------------------------------------------------------------------");
			
			String ss = input("좌석을 선택해주세요. (입력 예> a열 1번 ===> a1/ [취소 : 0]) 입력 : ");
			if(ss.equals("0")) {
				return null;
			}
			int r = ss.charAt(0)-97;
			int c = Integer.parseInt(ss.substring(1));
			show = ss.charAt(0)+"열  "+ c + "석 ";

			
			int s = (r)*10 + c;
			
			Seat seat = new Seat();
			seat.setPlayTimeString(pts);		
			seat.setTheaterNo(theaterNo);	
			seat.setSeatName(s);
			
			seat = dao2.selectSeatBySeatName(seat);
			
			if(seat.getIsRes() == 2) {
				System.out.println("이미 예매된 좌석입니다. 다시 시도해주세요.");
				continue;
			}else {
				return seat;
			}
		}
	}
	
	public int confirmReservation(Seat seat) {
//		seat.getIsRes(), seat.getPlayTime(), seat.getPlayTimeString(), seat.getSeatName(), seat.getSeatNo(), seat.getTheaterNo()
			int sel = Integer.parseInt(input(
				"예매하실 영화,\n"
				+movieTitle+"\n"
				+pts
				+seat.getTheaterNo() + " 관\n"
				+show + "\n"
				+"예매를 진행하시겠습니까? (Y -> 1  / N -> 0)"
				));
		if(sel == 1) {
			Reservation r = new Reservation();
			r.setSeatNo(seat.getSeatNo());
			r.setSeatNameTag(show);	
			r.setId(id);
			r.setSerial(serial);
			
			if(id == null) dao3.insertReservation2(r);
			if(serial == 0) {
				dao3.insertReservation(r);
//				history_no, title, play_time, id
				History h = new History();
				h.setTitle(movieTitle);
				h.setId(id);
				h.setPlayTime(pts);
				hDAO.insertHistory(h);
			} 
			
			System.out.println("예매가 완료되었습니다.");
			dao2.updateSeatIsRes(seat.getSeatNo());
		}else {			
			System.out.println("예매가 취소되었습니다.");
		}
		
		return sel;
	}
	
	
	public void service() {
		/*
		System.out.println(movie.getTitle());
		System.out.println(movie.getMovieNo());
		System.out.println(movie.getReleaseDate());
		System.out.println(movie.getRating());
		*/
		Seat s; 
		while(true) {
			int isInput = selectMovie();
			if(isInput == 1) {
				s = selectSeat(     selectTheater(     selectDate()    )   );
				if(s != null) {
					confirmReservation(s);
					return;
				}
			}else break;			
		}
	}
/*
	public static void main(String[] args) throws Exception {
		MovieDAO md = null;
		SeatDAO sd = null;
		MovieReservationController mr = new MovieReservationController(md,sd,"admin");
		mr.service();
//		String s = mr.getCalDate(0);
//		System.out.println(s);
//		System.out.println(mr.getCalDate2(s));
	}*/
}
