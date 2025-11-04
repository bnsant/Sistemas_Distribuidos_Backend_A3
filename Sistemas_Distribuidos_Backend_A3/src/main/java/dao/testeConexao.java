package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class testeConexao {
    public static void main(String[] args){
        
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if(conn == null){
            System.out.println("Erro: Conexão nula");
        }else{
            System.out.println("Conexao bem sucedida");
            
        }
            
        
        
        
        
        
    }
    
}
