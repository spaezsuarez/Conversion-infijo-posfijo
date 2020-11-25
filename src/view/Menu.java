package view;

//Elementos Graficos
import javax.swing.JFrame;
//Elementos decorativos
import java.awt.Dimension;

public class Menu extends JFrame {
    
    private final int ancho,alto;
    
    public Menu(){
        ancho = 600;
        alto = 800;
    }
    
    public void initTemplate(){
        setLayout(null);
        setSize(new Dimension(ancho,alto));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
