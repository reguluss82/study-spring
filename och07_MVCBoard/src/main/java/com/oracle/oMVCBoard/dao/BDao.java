package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {
	DataSource dataSource;
	public BDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("생성자 dataSource --> " + e.getMessage());
			e.printStackTrace();
		}
	}
	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<BDto>();
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		ResultSet         resultSet         = null;
		String query = "select * from mvc_board order by bgroup desc, bstep";
		
		System.out.println("BDao boardList start...");
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			System.out.println("BDao query --> " + query);
			resultSet = preparedStatement.executeQuery();
			System.out.println("resultSet : " + resultSet);
			while (resultSet.next()) {

//				setter 이용
//				bDto.setbId(resultSet.getInt("bid"));
//				bDto.setbName(resultSet.getString("bname"));
//				bDto.setbTitle(resultSet.getString("btitle"));
//				bDto.setbContent(resultSet.getString("bcontent"));
//				bDto.setbDate(resultSet.getTimestamp("bdate"));
//				bDto.setbHit(resultSet.getInt("bhit"));
//				bDto.setbGroup(resultSet.getInt("bgroup"));
//				bDto.setbStep(resultSet.getInt("bstep"));
//				bDto.setbStep(resultSet.getInt("bindent"));
				
				// 생성자 이용
				int bId = resultSet.getInt("bId");
				String    bName    = resultSet.getString("bName");
				String    bTitle   = resultSet.getString("bTitle");
				String    bContent = resultSet.getString("bContent");
				Timestamp bDate    = resultSet.getTimestamp("bDate");
				int       bHit     = resultSet.getInt("bHit");
				int       bGroup   = resultSet.getInt("bGroup");
				int       bStep    = resultSet.getInt("bStep");
				int       bIndent  = resultSet.getInt("bIndent");
				BDto bDto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				bList.add(bDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet         != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bList;
	}
	public BDto contentView(String strId) {
		upHit(strId);
		BDto dto = null;
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		ResultSet         resultSet         = null;
		String query = "select * from mvc_board where bId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String    bName    = resultSet.getString("bName");
				String    bTitle   = resultSet.getString("bTitle");
				String    bContent = resultSet.getString("bContent");
				Timestamp bDate    = resultSet.getTimestamp("bDate");
				int       bHit     = resultSet.getInt("bHit");
				int       bGroup   = resultSet.getInt("bGroup");
				int       bStep    = resultSet.getInt("bStep");
				int       bIndent  = resultSet.getInt("bIndent");
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			System.out.println("contentView err -> " + e.getMessage());
		} finally {
				try {
					if(resultSet         != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection        != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return dto;
	}
	private void upHit(String strId) {
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("upHit err -> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void modify(int bId, String bName, String bTitle, String bContent) {
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "update mvc_board set bName = ? , bTitle = ? , bContent = ? where bId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt   (4, bId);
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("modify err -> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void delete(int bId) {
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from mvc_board where bId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bId);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	public void write(String bName, String bTitle, String bContent) {
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into mvc_board (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) "
				     + "values (MVC_BOARD_SEQ.nextval, ?, ?, ?, sysdate, ?, MVC_BOARD_SEQ.currval, ?, ? )";
		
		try {
			int bHit = 0;
			int bStep = 0;
			int bIndent = 0;
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt   (4, bHit);
			preparedStatement.setInt   (5, bStep);
			preparedStatement.setInt   (6, bIndent);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("write dataSource --> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	public BDto reply_view(int bId) {
		System.out.println("reply_view dao start");
		BDto dto = new BDto();
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		ResultSet         resultSet         = null;
		String query = "select * from mvc_board where bId = ?";
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bId);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				bId = resultSet.getInt("bId");
				String    bName    = resultSet.getString("bName");
				String    bTitle   = resultSet.getString("bTitle");
				String    bContent = resultSet.getString("bContent");
				Timestamp bDate    = resultSet.getTimestamp("bDate");
				int       bHit     = resultSet.getInt("bHit");
				int       bGroup   = resultSet.getInt("bGroup");
				int       bStep    = resultSet.getInt("bStep");
				int       bIndent  = resultSet.getInt("bIndent");
				
				dto = new BDto(bId , bName , bTitle , bContent , bDate , bHit , bGroup , bStep , bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet         != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {
		replyShape(bGroup, bStep);  // 원래 있던 답글 step 증가시키는 method
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into mvc_board (BID,BNAME,BTITLE,BCONTENT,BDATE,BHIT,BGROUP,BSTEP,BINDENT) "
			         + "values (MVC_BOARD_SEQ.nextval, ?, ?, ?, sysdate, 0, ?, ?, ? )";
		try {
			bStep   += 1;
			bIndent += 1;
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt   (4, bGroup);
			preparedStatement.setInt   (5, bStep);
			preparedStatement.setInt   (6, bIndent);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void replyShape(int bGroup, int bStep) {
		Connection        connection        = null;
		PreparedStatement preparedStatement = null;
		String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bGroup);
			preparedStatement.setInt(2, bStep);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println("replyShape err --> " + e.getMessage());
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection        != null) connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
