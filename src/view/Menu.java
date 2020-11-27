package view;

//Elementos Graficos
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
//Elementos decorativos
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;
import logic.logica;

public class Menu extends JFrame {

    private final int ancho, alto;
    private JTextField input;
    private JLabel title, result;
    private JButton btn;

    public Menu() {
        ancho = 400;
        alto = 400;
        input = new JTextField();
        title = new JLabel("Conversión de infijo a posfijo ");
        btn = new JButton("Convertir");
        result = new JLabel();
    }

    public void initElements() {
        input.setSize(new Dimension(ancho - 10, 45));
        input.setHorizontalAlignment(SwingConstants.CENTER);
        input.setFont(new Font("Arial", Font.PLAIN, 15));
        input.setLocation(5, 100);

        JScrollPane scrollUno = new JScrollPane(input);
        scrollUno.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollUno.setSize(new Dimension(ancho - 10, 35));
        scrollUno.setLocation(5, 100);
        add(scrollUno);

        title.setSize(new Dimension(ancho - 10, 75));
        title.setLocation(5, 10);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(title);

        result.setSize(new Dimension(ancho - 10, 150));
        result.setLocation(5, 150);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 25));
        result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(result);

        btn.setSize(new Dimension(200, 30));
        btn.setLocation((this.getWidth() - btn.getWidth()) / 2, 320);
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setFocusable(false);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        add(btn);
    }

    public void initTemplate() {
        setLayout(null);
        setTitle("Conversión");
        setSize(new Dimension(ancho, alto));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initElements();
        initListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initListeners() {
        btn.addActionListener((ae) -> {
            logica logic = new logica();
            String respuesta = logic.calcularSufijo(input.getText());
            logic.calcularGruposNumeros(input.getText());
            result.setText(respuesta);
            repaint();
        });
    }

}
