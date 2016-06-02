package es.cesalberca.mercato.view;

import es.cesalberca.mercato.controller.auth.Login;
import es.cesalberca.mercato.controller.database.DBConnector;
import es.cesalberca.mercato.controller.shop.Shop;
import es.cesalberca.mercato.model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Modal para el inicio de sesión.
 * @author César Alberca
 */
public class JPLogin extends javax.swing.JPanel {
    
    /**
     * Constructor del JPLogin.
     * @param shop Controlador de la tienda.
     * @param jpa JPanel para poder activar un botón.
     */
    public JPLogin(Shop shop, JPApp jpa) {
        initComponents();
        Login login = new Login(shop);
        JTextField jtfUser = new JTextField(5);
        JPasswordField jtfPassword = new JPasswordField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Usuario:"));
        myPanel.add(jtfUser);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Contraseña:"));
        myPanel.add(jtfPassword);

        // Captura el resultado del click del usuario.
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Inicio de sesión", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                User userTryingToLogin = new User(jtfUser.getText(), jtfPassword.getText());
                
                if (login.isValidUser(userTryingToLogin)) {
                    // Buscamos el id a partir del nombre de usuario.
                    int userId = ((User) shop.getDbh().search(DBConnector.getConnection(), userTryingToLogin)).getId();;
                    shop.setUser(new User(userId, userTryingToLogin.getName(), userTryingToLogin.getPassword()));
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                    jpa.jbAddOrder.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(JPLogin.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Base de datos no disponible en estos momentos. Inténtalo de nuevo más tarde.");
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

        setBackground(new java.awt.Color(102, 51, 255));

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
