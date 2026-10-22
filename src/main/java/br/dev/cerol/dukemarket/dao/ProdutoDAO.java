/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.cerol.dukemarket.dao;

import br.dev.cerol.dukemarket.bean.Produto;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dib
 */
public class ProdutoDAO {

    private static final String SQL_INSERT = "INSERT INTO produto(codBarras, "
            + "descricao, qtd, valorCompra, ValorVenda) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM produto";
    private static final String SQL_SELECT_ID = "SELECT * FROM produto "
            + "WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE produto SET codBarras = ?,"
            + "descricao = ?, qdt = ?, valorCompra = ?, valorVenda = ? "
            + "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";

    public void create(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null, "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public List<Produto> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;
        List<Produto> listaProdutos = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            listaProdutos = new ArrayList<>();

            while (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("ValorCompra"));
                p.setValorVenda(rs.getDouble("ValorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
                listaProdutos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }

    public Produto getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("ValorCompra"));
                p.setValorVenda(rs.getDouble("ValorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
            }
        }
        catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return p;
    }
    
    public void update(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());
            stmt.setInt(6, p.getId());
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno);
        }
        catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null, "Delete: " + auxRetorno);
        }
        catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }
        finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
}
