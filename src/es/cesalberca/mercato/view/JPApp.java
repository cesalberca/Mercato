package es.cesalberca.mercato.view;

import es.cesalberca.mercato.controller.database.DatabaseConnector;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import static es.cesalberca.mercato.view.JFApp.dbh;
import es.cesalberca.mercato.model.Category;
import es.cesalberca.mercato.model.Item;
import es.cesalberca.mercato.model.Order;
import es.cesalberca.mercato.model.User;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Panel principal de la aplicación.
 * @author César Alberca
 */
public class JPApp extends javax.swing.JPanel {
    private static ArrayList<Item> items = null;
    public static Order order = null;
    private static User u;
    public JPApp() {
        initComponents();
        items = new ArrayList<>();
        
        // Añadimos un listener event para poder capturar el evento del cambio de estado del combo box.
        jcbCategories.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Esto nos evita un null pointer exception al cargar la app.
                if (jcbCategories.getSelectedItem() != null) {
                    jcbItems.removeAllItems();
                    loadItems();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbLogin = new javax.swing.JButton();
        jbSignup = new javax.swing.JButton();
        jlCategory = new javax.swing.JLabel();
        jlItem = new javax.swing.JLabel();
        jbAddOrder = new javax.swing.JButton();
        jcbCategories = new javax.swing.JComboBox<>();
        jcbItems = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtOrders = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jbLogin.setText("Iniciar sesión");
        jbLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLoginActionPerformed(evt);
            }
        });

        jbSignup.setText("Registrarse");
        jbSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSignupActionPerformed(evt);
            }
        });

        jlCategory.setText("Categoría");

        jlItem.setText("Producto");

        jbAddOrder.setText("Añadir a la cesta");
        jbAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddOrderActionPerformed(evt);
            }
        });

        jcbCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));
        jcbCategories.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcbCategoriesFocusGained(evt);
            }
        });

        jcbItems.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        jtOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Categoría", "Producto", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtOrders);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSignup)
                        .addGap(197, 197, 197))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlCategory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jlItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbAddOrder)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCategory)
                    .addComponent(jlItem)
                    .addComponent(jbAddOrder)
                    .addComponent(jcbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSignup)
                    .addComponent(jbLogin))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Añade un item al pedido.
     */
    private void addItemToOrder() {
        try {
            // Buscamos primero ese item en la bbdd.
            Item itemToSearch = new Item(jcbItems.getSelectedItem().toString());
            Item item = (Item) dbh.search(DatabaseConnector.getConnection(), itemToSearch);
            
            items.add(item);
            // Generamos un objeto pedido
            order = new Order(items, u);
            
            
            Vector headersTable = new Vector();
            headersTable.add("Nombre");
            headersTable.add("Precio");
            headersTable.add("Categoría");
            
            DefaultTableModel dtm = new DefaultTableModel(headersTable, 0);
            jtOrders.setModel(dtm);
            
            for (int i=0;i<items.size();i++){
                dtm.setRowCount(dtm.getRowCount()+1);
                jtOrders.setValueAt(items.get(i).getName(), i, 0);
                jtOrders.setValueAt(items.get(i).getPrize(), i, 1);
                jtOrders.setValueAt(dbh.getCategoryById(DatabaseConnector.getConnection(), items.get(i).getCategory()).getName(), i, 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Carga los items en el combo box.
     */
    private void loadItems() {
        try {
            ArrayList<Item> items = dbh.searchItemsByCategory(DatabaseConnector.getConnection(), jcbCategories.getSelectedItem().toString());
            // Comprueba que hay resultados
            if (items.size() > 0) {
                for (Item item : items) {
                    jcbItems.addItem(item.getName());
                }
            } else {
                jcbItems.addItem("----");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Carga las categorías en el combo box.
     */
    private void loadCategories() {
        try {
            ResultSet rs = dbh.selectAll(DatabaseConnector.getConnection(), "Category");
            Category c = null;
            
            while (rs.next()) {
                c = new Category(rs.getString("NAME"), rs.getInt("ID"));
                jcbCategories.addItem(c.getName());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JPApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jbLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLoginActionPerformed
        JPLogin.login();
    }//GEN-LAST:event_jbLoginActionPerformed

    private void jbSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSignupActionPerformed
        JPSignup.signup();
    }//GEN-LAST:event_jbSignupActionPerformed

    private void jcbCategoriesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbCategoriesFocusGained
        jcbCategories.removeAllItems();
        loadCategories();
    }//GEN-LAST:event_jcbCategoriesFocusGained

    private void jbAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddOrderActionPerformed
        addItemToOrder();
    }//GEN-LAST:event_jbAddOrderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAddOrder;
    private javax.swing.JButton jbLogin;
    private javax.swing.JButton jbSignup;
    private javax.swing.JComboBox<String> jcbCategories;
    private javax.swing.JComboBox<String> jcbItems;
    private javax.swing.JLabel jlCategory;
    private javax.swing.JLabel jlItem;
    private javax.swing.JTable jtOrders;
    // End of variables declaration//GEN-END:variables
}
