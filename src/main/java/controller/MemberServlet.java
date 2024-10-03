package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import model.Squad;
import model.enums.Role;
import service.MemberService;

public class MemberServlet extends HttpServlet {

	 
	private final MemberService memberService;

	public MemberServlet() {
		this.memberService = new MemberService();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("get".equals(action)) {
			getMemberById(request, response);
		} else {
			listMembers(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("add".equals(action)) {
			addMember(request, response);
		} else if ("update".equals(action)) {
			updateMember(request, response);

		} else if ("delete".equals(action)) {
			deleteMember(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}

	}

	private void listMembers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int pageSize = 10;
		List<Member> members = memberService.getAllMembers(page, pageSize);
		request.setAttribute("members", members);
		request.getRequestDispatcher("views/members.jsp").forward(request, response);

	}

	private void getMemberById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Member member = memberService.getMemberById(id);

		if (member != null) {
			request.setAttribute("member", member);
			request.getRequestDispatcher("views/members.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
		}
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String roleString = request.getParameter("role");
		Long squadId = request.getParameter("squadId") != null ? Long.valueOf(request.getParameter("squadId")) : null;

		Role role = Role.valueOf(roleString.toUpperCase());

		Member member = new Member();
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(email);
		member.setRole(role);
		member.setSquadId(squadId);

		memberService.addMember(member);

		response.sendRedirect("member?action=list");

	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String roleString = request.getParameter("role");
		Long sqaudId = Long.parseLong(request.getParameter("squadId"));

		Role role = Role.valueOf(roleString.toUpperCase());

		Member member = new Member();
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(email);
		member.setRole(role);
		member.setSquadId(sqaudId);

		memberService.updateMember(member);

		response.sendRedirect("member?action=list");

	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		memberService.deleteMember(id);

		response.sendRedirect("members?action=list");

	}

}
