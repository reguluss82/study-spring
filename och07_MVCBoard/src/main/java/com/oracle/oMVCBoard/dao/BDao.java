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
				
//				bDto.setbId(resultSet.getInt("bid"));
//				bDto.setbName(resultSet.getString("bname"));
//				bDto.setbTitle(resultSet.getString("btitle"));
//				bDto.setbContent(resultSet.getString("bcontent"));
//				bDto.setbDate(resultSet.getTimestamp("bdate"));
//				bDto.setbHit(resultSet.getInt("bhit"));
//				bDto.setbGroup(resultSet.getInt("bgroup"));
//				bDto.setbStep(resultSet.getInt("bstep"));
//				bDto.setbStep(resultSet.getInt("bindent"));
				
				int bId = resultSet.getInt("bId");
				System.out.println("bId : " + bId);
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				BDto bDto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				bList.add(bDto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bList;
	}
}
