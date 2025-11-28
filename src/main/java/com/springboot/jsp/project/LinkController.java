package com.springboot.jsp.project;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/link")
public class LinkController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LinkDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new LinkDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String view = "";

        if (action == null) action = "list";

        switch (action) {
            case "list":
                view = list(request, response);
                break;

            case "insert":
                view = insert(request, response);
                break;

            case "delete":
                view = delete(request, response);
                break;

            case "edit":  // 수정 페이지 이동
                view = edit(request, response);
                break;

            case "update": // 수정 처리
                view = update(request, response);
                break;

            default:
                view = list(request, response);
        }

        getServletContext()
                .getRequestDispatcher("/project/" + view)
                .forward(request, response);
    }

    private String list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Link> links = dao.getAll();
        request.setAttribute("links", links);
        return "link.jsp";
    }

    private String insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Link l = new Link();
        l.setName(request.getParameter("name"));
        l.setUrl(request.getParameter("url"));

        dao.insert(l);
        return list(request, response);
    }

    private String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id);

        return list(request, response);
    }

    private String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Link l = dao.getById(id);

        request.setAttribute("link", l);
        return "update.jsp";
    }

    private String update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Link l = new Link();
        l.setId(Integer.parseInt(request.getParameter("id")));
        l.setName(request.getParameter("name"));
        l.setUrl(request.getParameter("url"));

        dao.update(l);

        return list(request, response);
    }
}