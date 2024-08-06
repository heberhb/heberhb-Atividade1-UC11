
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    
    Connection conn = null;
    
    public Connection connectDB(){
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","19101992");
            System.out.println("Conexão bem sucedida !!");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public void GetDesconectar(){
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }else{
                System.out.println("Erro ao desconectar");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível desconectar do banco de dados");
        }
        
    }
    
}
