<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card-header">${title}</div>
            <div class="card-body">
                <!-- Title for the statistics -->
                <h3 class="text-center mb-4">Statistics Project</h3> <!-- Added title here -->

                <table class="table table-striped table-bordered text-white">
                    <thead>
                        <tr>
                            <th>Project Name</th>
                            <th>Member Count</th>
                            <th>Task Count</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty projectSummaries}">
                                <tr>
                                    <td colspan="3" class="text-center">No projects found.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="summary" items="${projectSummaries}">
                                    <tr>
                                        <td>${summary[0]}</td>
                                        <td>${summary[1]}</td>
                                        <td>${summary[2]}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>

                <!-- Pagination controls -->
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</div>
