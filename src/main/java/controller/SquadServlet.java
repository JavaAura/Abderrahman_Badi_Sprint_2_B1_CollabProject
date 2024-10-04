package controller;

import service.SquadService;
import model.Squad;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SquadServlet extends HttpServlet {

  
    private final SquadService squadService;

    public SquadServlet() {
        this.squadService = new SquadService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("get".equals(action)) {
            getSquadById(request, response);
        } else {
            listSquads(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSquad(request, response);
        } else if ("update".equals(action)) {  
            updateSquad(request, response);
        } else if ("delete".equals(action)) { 
            deleteSquad(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void listSquads(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 10;
        List<Squad> squads = squadService.getAllSquads(page, pageSize);
        request.setAttribute("squads", squads);
        request.getRequestDispatcher("views/squads.jsp").forward(request, response);
    }

    private void getSquadById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Squad squad = squadService.getSquadById(id);

        if (squad != null) {
            request.setAttribute("squad", squad);
            request.getRequestDispatcher("views/squads.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Squad not found");
        }
    }

    private void addSquad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Squad squad = new Squad();
        squad.setName(name);
        squadService.addSquad(squad);
        response.sendRedirect("squads?action=squad");
    }

    private void updateSquad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Squad squad = new Squad();
        squad.setId(id);
        squad.setName(name);
        squadService.updateSquad(squad);
        response.sendRedirect("squads?action=squad");
    }

    private void deleteSquad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            squadService.deleteSquad(id);
            request.setAttribute("message", "Squad deleted successfully!");  
        } catch (Exception e) {
            e.printStackTrace();  
            request.setAttribute("errorMessage", "Failed to delete squad: " + e.getMessage());  
        }
        response.sendRedirect("squads?action=squad");  
    }

}
