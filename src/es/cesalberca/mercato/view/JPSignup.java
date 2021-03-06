package es.cesalberca.mercato.view;

import es.cesalberca.mercato.controller.auth.Signup;
import es.cesalberca.mercato.controller.shop.Shop;
import es.cesalberca.mercato.model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Clase que genera un modal para registrarse.
 * @author César Alberca
 */
public class JPSignup extends javax.swing.JPanel {

    public JPSignup(Shop shop) {
        initComponents();
        Signup signup = new Signup(shop);
        JTextField username = new JTextField(5);
        JPasswordField password = new JPasswordField(5);
        JPasswordField confirmPassword = new JPasswordField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Usuario:"));
        myPanel.add(username);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Contraseña:"));
        myPanel.add(password);
        myPanel.add(new JLabel("Confirmar contraseña:"));
        myPanel.add(confirmPassword);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Registrar usuario", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Comprobamos que ambas contraseñas coinciden y que no están vacías.
            if (password.getText().equals(confirmPassword.getText()) && !password.getText().isEmpty()) {
                User userTryingToSignup = new User(username.getText(), password.getText());
                
                try {
                    // Comprobamos en la bbdd que ese nombre de usuario no está cogido.
                    if (signup.isUserAvailable(userTryingToSignup)) {
                        signup.register(userTryingToSignup);
                        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre.");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(JPSignup.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Base de datos no disponible en estos momentos. Inténtalo de nuevo más tarde.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseñas no coinciden");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
