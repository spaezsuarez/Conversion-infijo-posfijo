package view;

//Elementos Graficos
import java.awt.Color;
import javax.swing.JFrame;
//Elementos decorativos
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logic.logica;

public class Menu extends JFrame {

    private final int ancho, alto;
    private JTextField TxtFldTexto;
    private JLabel JLblTitle;
    private JButton BtnCalcularPosfijo;

    public Menu() {
        ancho = 500;
        alto = 190;
    }

    public void initTemplate() {
        setLayout(null);
        setSize(new Dimension(ancho, alto));
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Conversión infijo a posfijo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        initListeners();
    }

    public void initComponents() {
        JLblTitle = new JLabel("Ingrese su texto");
        add(JLblTitle);
        JLblTitle.setSize(475, 40);
        JLblTitle.setLocation(5, 5);
        JLblTitle.setVisible(true);
        JLblTitle.setHorizontalAlignment(JLabel.CENTER);
        JLblTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        JLblTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        TxtFldTexto = new JTextField();
        add(TxtFldTexto);
        TxtFldTexto.setSize(475, 40);
        TxtFldTexto.setLocation(5, 55);
        TxtFldTexto.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        TxtFldTexto.setHorizontalAlignment(JTextField.CENTER);
        TxtFldTexto.setVisible(true);

        BtnCalcularPosfijo = new JButton("Ctrl Z");
        add(BtnCalcularPosfijo);
        BtnCalcularPosfijo.setSize(475, 40);
        BtnCalcularPosfijo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        BtnCalcularPosfijo.setLocation(5, 105);
    }

    public void initListeners() {
        BtnCalcularPosfijo.addActionListener((ae) -> {
            logica logic = new logica(TxtFldTexto.getText());
        });
    }

}
