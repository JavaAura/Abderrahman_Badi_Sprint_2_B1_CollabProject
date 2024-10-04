package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import model.Squad;
import model.enums.Role;
import service.MemberService;
import service.SquadService;

public class MemberServlet extends HttpServlet {

    private final MemberService memberService;
    private final SquadService squadService;

    public MemberServlet() {
        this.memberService = new MemberService();
        this.squadService = new SquadService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("get".equals(action)) {
            getMemberById(request, response);
        } else if ("listBySquad".equals(action)) {
            getMembersBySquad(request, response); 
        } else {
            listMembers(request, response);
        }
    }


	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("add".equals(action)) {
            addMember(request, response, session);
        } else if ("update".equals(action)) {
            updateMember(request, response, session);
        } else if ("delete".equals(action)) {
            deleteMember(request, response, session);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        int pageSize = 5;

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int totalMembers = memberService.getTotalMembersCount();
        int totalPages = (int) Math.ceil((double) totalMembers / pageSize);
        List<Member> members = memberService.getAllMembers(page, pageSize);
        List<Squad> squads = squadService.getAllSquads(page, pageSize);

        Map<Long, Squad> squadMap = new HashMap<>();
        for (Squad squad : squads) {
            squadMap.put(squad.getId(), squad);
        }

        for (Member member : members) {
            member.setSquad(squadMap.get(member.getSquadId()));
        }

        request.setAttribute("members", members);
        request.setAttribute("roles", Arrays.asList(Role.values()));
        request.setAttribute("squads", squads);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalMembers", totalMembers);

        
        if (request.getSession().getAttribute("message") != null) {
            request.setAttribute("message", request.getSession().getAttribute("message"));
            request.getSession().removeAttribute("message");  
        }

        
        request.getRequestDispatcher("views/members.jsp").forward(request, response);
    }
    
    private void getMembersBySquad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long squadId = Long.parseLong(request.getParameter("id"));
        
      
        List<Member> members = memberService.getMembersBySquad(squadId);
        
        
        Squad squad = squadService.getSquadById(squadId); 
        
       
        request.setAttribute("members", members);
        request.setAttribute("squad", squad); 
      
        request.getRequestDispatcher("views/squadMembers.jsp").forward(request, response);
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

    private void addMember(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String roleString = request.getParameter("role");
        Long squadId = request.getParameter("squadId") != null && !request.getParameter("squadId").isEmpty()
                ? Long.valueOf(request.getParameter("squadId"))
                : null;

        Role role = Role.valueOf(roleString.toUpperCase());

        Member member = new Member();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        member.setRole(role);
        member.setSquadId(squadId);

        memberService.addMember(member);
        session.setAttribute("message", "Member added successfully!");
        response.sendRedirect("members?action=list");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        Long id = null;

        if (idString != null && !idString.isEmpty()) {
            id = Long.parseLong(idString);
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String roleString = request.getParameter("role");
        Long squadId = null;

        String squadIdString = request.getParameter("squadId");
        if (squadIdString != null && !squadIdString.isEmpty()) {
            squadId = Long.parseLong(squadIdString);
        }

        Role role = Role.valueOf(roleString.toUpperCase());

        Member member = new Member();
        member.setId(id);
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        member.setRole(role);
        member.setSquadId(squadId);

        memberService.updateMember(member);
        session.setAttribute("message", "Member updated successfully!");
        response.sendRedirect("members?action=list");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        memberService.deleteMember(id);
        session.setAttribute("message", "Member deleted successfully!");
        response.sendRedirect("members?action=list");
    }
}
