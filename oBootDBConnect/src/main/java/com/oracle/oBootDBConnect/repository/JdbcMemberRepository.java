package com.oracle.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.oBootDBConnect.domain.Member1;


//@Repository
public class JdbcMemberRepository implements MemberRepository {
	// JDBC 사용
	private final DataSource dataSource; // final <- setter를 통해 변경을 하지 않겠다
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	@Override
	public Member1 save(Member1 member1) {
		String sql = "insert into member1(id,name) values(member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql -> " + sql);
		
		Connection        conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member1.getName());
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepository pstmt.excuteUpdate After");
			
			return member1;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);  //DataSourceUtils 로 conn받았으면 DataSourceUtils로 close 권장
		}
	}

	@Override
	public List<Member1> findAll() {
		String sql = "select * from member1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member1> members = new ArrayList<>();
			while (rs.next()) {
				Member1 member = new Member1();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
	// close method
	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource); //memory 깨끗하게 정리
	}
	// close method overLoading conn , pstmt , rs
	private void close(Connection conn , PreparedStatement pstmt , ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 
		try {
			if (pstmt != null) pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 
		try {
			if (conn != null) close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}