package repository.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import model.Member;
import model.enums.Role;
import repository.interfaces.MemberRepository;

public class MemberRepositoryImpl implements MemberRepository {

	private static final String GET_ALL_MEMBERS_QUERY = "SELECT * FROM member LIMIT ? OFFSET ?";
	private static final String GET_MEMBER_BY_ID_QUERY = "SELECT * FROM member WHERE id = ?";
	private static final String ADD_MEMBER_QUERY = "INSERT INTO member (first_name, last_name, email, role, squad_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE_MEMBER_QUERY = "UPDATE member SET first_name = ?, last_name = ?, email = ?, role = ?, squad_id = ? WHERE id = ?";
	private static final String DELETE_MEMBER_QUERY = "DELETE FROM member WHERE id = ?";

	@Override
	public List<Member> getAllMembers(int page, int pageSize) {
		List<Member> members = new ArrayList<>();
		int pagination = (page - 1) * pageSize;

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_ALL_MEMBERS_QUERY)) {

			stmt.setInt(1, pageSize);
			stmt.setInt(2, pagination);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				member.setEmail(rs.getString("email"));
				member.setRole(Role.valueOf(rs.getString("role")));
				member.setSquadId(rs.getLong("squad_id"));
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public Member getMemberById(Long id) {
		Member member = null;

		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_MEMBER_BY_ID_QUERY)) {

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getLong("id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				member.setEmail(rs.getString("email"));
				member.setRole(Role.valueOf(rs.getString("role")));
				member.setSquadId(rs.getLong("squad_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public void addMember(Member member) {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(ADD_MEMBER_QUERY)) {

			stmt.setString(1, member.getFirstName());
			stmt.setString(2, member.getLastName());
			stmt.setString(3, member.getEmail());
			stmt.setString(4, member.getRole().name());

			if (member.getSquadId() != null) {
				stmt.setLong(5, member.getSquadId());
			} else {
				stmt.setNull(5, java.sql.Types.BIGINT);
			}

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMember(Member member) {
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(UPDATE_MEMBER_QUERY)) {
	        
	        stmt.setString(1, member.getFirstName());
	        stmt.setString(2, member.getLastName());
	        stmt.setString(3, member.getEmail());
	        stmt.setString(4, member.getRole().name());

	         
	        if (member.getSquadId() != null) {
	            stmt.setLong(5, member.getSquadId());
	        } else {
	            stmt.setNull(5, Types.BIGINT);  
	        }

	        stmt.setLong(6, member.getId());
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void deleteMember(Long id) {
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(DELETE_MEMBER_QUERY)) {

			stmt.setLong(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
