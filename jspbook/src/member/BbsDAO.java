package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BbsDAO {
	private static final Logger LOG = LoggerFactory.getLogger(BbsDAO.class);

    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "javapass";
    private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
    private Connection conn;
	
    public BbsDAO() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");	
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
	

    public void createBbsTable() {
    	LOG.trace("createBbsTable_시작");
    	String query = "create table if not exists bbs (" + 
    			"  id int unsigned not null auto_increment," + 
    			"  memberId int unsigned not null," + 
    			"  title varchar(50) not null," + 
    			"  date datetime not null default current_timestamp," + 
    			"  content varchar(400)," + 
    			"  primary key(id)," + 
    			"  foreign key(memberId) references member(id)" + 
    			") default charset=utf8;";
    	PreparedStatement pStmt = null;
    	try {
			pStmt = conn.prepareStatement(query);
			
			pStmt.execute();
			LOG.trace("테이블생성_성공");
		} catch (Exception e) {
			LOG.trace("테이블생성_실패");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				LOG.trace("쿼리문_에러");
				se.printStackTrace();
			}
		}	
    }
    
    public int getCount() {
    	LOG.trace("getCount_시작");
		String query = "select count(*) from bbs;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		LOG.trace("getCount_카운트 값 가져오기 성공");
		return count;
	}
    
	public BbsDTO selectOne(int id) {
		LOG.trace("selectOne_시작");
		String query = "select * from bbs where id=?;";
		PreparedStatement pStmt = null;
		BbsDTO bDto = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				bDto.setId(rs.getInt(1));
				bDto.setMemberId(rs.getInt(2));
				bDto.setTitle(rs.getString(3));
				bDto.setDate(rs.getString(4));
				bDto.setContent(rs.getString(5));
			}
			rs.close();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		LOG.trace("selectOne_성공");
		return bDto;
	}
	
	public void updateBbs(BbsDTO bDto) {
		LOG.trace("updateBbs_시작");
		PreparedStatement pStmt = null;
		//String date = getCurrentDBTime();
		String query = "update bbs set title=?, date=now(), content=? where id=?;";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, bDto.getTitle());
			//pStmt.setString(2, date);
			pStmt.setString(2, bDto.getContent());
			pStmt.setInt(3, bDto.getId());
			pStmt.executeUpdate();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void insertBbs(BbsDTO bDto) {
		LOG.trace("insertBbs_시작");
		PreparedStatement pStmt = null;
		String query = "insert into bbs (memberId, title, content) values(?, ?, ?);";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, bDto.getMemberId());
			pStmt.setString(2, bDto.getTitle());
			pStmt.setString(3, bDto.getContent());		
			pStmt.executeUpdate();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void deleteBbs(int id) {
		LOG.trace("deleteBbs_시작");
		String query = "delete from bbs where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public BbsMember ViewData(int id) {
		LOG.trace("ViewData_시작");
		String query = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " + 
				"inner join member on bbs.memberId=member.id where bbs.id=?;";;
		PreparedStatement pStmt = null;
		BbsMember bmDto = new BbsMember();
		int result = -1;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4).substring(0, 16));
				bmDto.setContent(rs.getString(5));
			}
			rs.close();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		LOG.trace("ViewData_성공");
		return bmDto;
	}
	
	public List<BbsMember> selectJoinAll(int page) {
		LOG.trace("selectJoinAll_시작");
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc;";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<BbsMember> bmList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4).substring(0, 16));
				bmList.add(bmDto);
			}
			rs.close();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		LOG.trace("selectJoinAll_성공");
		return bmList;
	}
	
	public String getCurrentDBTime() {
		LOG.trace("getCurrentDBTime_시작");
		String query = "select now();";
		PreparedStatement pStmt = null;
		String ts = null;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				ts = rs.getString(1);
			}
			rs.close();
		} catch (Exception e) {
			LOG.trace("예외발생");
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		LOG.trace("getCurrentDBTime_성공");
		return ts;
	}
	
	public void close() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception se1) { }
	}
}