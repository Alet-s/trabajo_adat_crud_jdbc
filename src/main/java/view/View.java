package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import usuarioDAO.UsuarioDAO;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

public class View extends JFrame {
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JButton btnInsertar;
    private JButton btnActualizar;
    private JButton btnBorrar;
    private JLabel lblControl;
    private JLabel lblUsuarios;
    private JTable table;
    private DefaultTableModel tableModel;
    private UsuarioDAO usuarioDAO;
    private JLabel lblEscribaAqui;
    private Box horizontalBox;
    private Box horizontalBox_1;

    /**
     * Create the frame.
     */
    public View() {
        usuarioDAO = new UsuarioDAO(); // Inicializar la instancia del DAO

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 250, 250));
        contentPane.setForeground(SystemColor.info);
        contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ---Tabla---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(51, 92, 685, 361);
        contentPane.add(scrollPane);

        // Crear el modelo de la tabla
        String[] columnNames = {"ID", "Nombre", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // Cargar datos en la tabla al iniciar la vista
        cargarDatosEnTabla();

        // --TextFields---
        txtId = new JTextField();
        txtId.setForeground(Color.LIGHT_GRAY);
        txtId.setHorizontalAlignment(SwingConstants.CENTER);
        txtId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtId.setText("id");
        txtId.setBounds(73, 500, 54, 22);
        contentPane.add(txtId);
        txtId.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setForeground(Color.LIGHT_GRAY);
        txtNombre.setText("Nombre");
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNombre.setColumns(10);
        txtNombre.setBounds(137, 500, 100, 22);
        contentPane.add(txtNombre);

        txtEmail = new JTextField();
        txtEmail.setForeground(Color.LIGHT_GRAY);
        txtEmail.setText("Email");
        txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtEmail.setColumns(10);
        txtEmail.setBounds(247, 500, 100, 22);
        contentPane.add(txtEmail);

        // --Botones--
        btnInsertar = new JButton("Insertar");
        btnInsertar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnInsertar.setBounds(414, 501, 89, 23);
        contentPane.add(btnInsertar);

        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertarUsuario();
            }
        });

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnActualizar.setBounds(513, 501, 89, 23);
        contentPane.add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        btnBorrar = new JButton("Borrar");
        btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnBorrar.setBounds(612, 501, 89, 23);
        contentPane.add(btnBorrar);

        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarUsuario();
            }
        });

        // --Labels--
        lblControl = new JLabel("Confirme su selección:");
        lblControl.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblControl.setBounds(487, 475, 141, 14);
        contentPane.add(lblControl);

        lblUsuarios = new JLabel("Usuarios");
        lblUsuarios.setForeground(new Color(0, 0, 128));
        lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuarios.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
        lblUsuarios.setBounds(298, 40, 192, 25);
        contentPane.add(lblUsuarios);
        
        lblEscribaAqui = new JLabel("Introduzca sus datos aquí:");
        lblEscribaAqui.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEscribaAqui.setBounds(73, 475, 192, 14);
        contentPane.add(lblEscribaAqui);
        
        horizontalBox = Box.createHorizontalBox();
        horizontalBox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        horizontalBox.setBounds(61, 468, 323, 69);
        contentPane.add(horizontalBox);
        
        horizontalBox_1 = Box.createHorizontalBox();
        horizontalBox_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        horizontalBox_1.setBounds(394, 468, 323, 69);
        contentPane.add(horizontalBox_1);
        
        Box horizontalBox_2 = Box.createHorizontalBox();
        horizontalBox_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        horizontalBox_2.setBounds(309, 29, 169, 46);
        contentPane.add(horizontalBox_2);
    }

    private void cargarDatosEnTabla() {
        // Limpiar el modelo de la tabla
        tableModel.setRowCount(0);

        // Obtener los datos de la base de datos
        try {
            ResultSet rs = usuarioDAO.obtenerUsuariosResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                tableModel.addRow(new Object[]{id, nombre, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarUsuario() {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        try {
            usuarioDAO.crearUsuario(nombre, email);
            cargarDatosEnTabla(); // Refrescar la tabla
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarUsuario() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        try {
            usuarioDAO.actualizarUsuarios(id, nombre, email);
            cargarDatosEnTabla(); // Refrescar la tabla
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarUsuario() {
        int id = Integer.parseInt(txtId.getText());
        try {
            usuarioDAO.eliminarUsuario(id);
            cargarDatosEnTabla(); // Refrescar la tabla
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al borrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        View frame = new View();
        frame.setVisible(true);
    }
}
