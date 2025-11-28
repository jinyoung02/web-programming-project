package com.springboot.jsp.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost//Users/jinyoung/Desktop/jwbookdb";

    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "jwbook", "0203");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // INSERT
    public void insert(Link l) {
        open();
        String sql = "INSERT INTO link(name, url) VALUES (?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, l.getName());
            pstmt.setString(2, l.getUrl());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // SELECT ALL
    public List<Link> getAll() {
        open();
        List<Link> links = new ArrayList<>();

        try {
            String sql = "SELECT * FROM link ORDER BY id DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Link l = new Link();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setUrl(rs.getString("url"));
                l.setCreated_at(rs.getTimestamp("created_at"));
                links.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return links;
    }

    // SELECT ONE (UPDATE할 때 필요)
    public Link getById(int id) {
        open();
        Link l = null;

        try {
            String sql = "SELECT * FROM link WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                l = new Link();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setUrl(rs.getString("url"));
                l.setCreated_at(rs.getTimestamp("created_at"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return l;
    }

    // UPDATE
    public void update(Link l) {
        open();
        String sql = "UPDATE link SET name = ?, url = ? WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, l.getName());
            pstmt.setString(2, l.getUrl());
            pstmt.setInt(3, l.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // DELETE
    public void delete(int id) {
        open();
        String sql = "DELETE FROM link WHERE id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}