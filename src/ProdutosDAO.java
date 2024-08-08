/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    private final conectaDAO conexao;
    private final Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public ProdutosDAO(){
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos(nome,valor,status) VALUES (?,?,?)";
        try{
            ps = this.conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            ps.execute();
            System.out.println("Produto Cadastrado com sucesso!!");
        } catch (SQLException ex) {
            System.out.println("Erro ao Cadastrar produto");
        }finally{
            this.conexao.GetDesconectar();
        }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        String sql = "SELECT*FROM produtos";
        try{
            ps = this.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                lista.add(produto);
            }
            System.out.println("Lista de Produtos Carregada");
            return lista;
        }catch (SQLException ex) {
            System.out.println("Erro ao buscar lista produtos");
            return null;
        }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (this.conn != null) this.conn.close(); // Correção: Use `this.conn`
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar recursos: " + ex.getMessage());
        }
        
      }
    }
}

