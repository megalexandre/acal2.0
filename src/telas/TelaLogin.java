package telas;

import dao.DaoUsuario;
import entidades.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.hibernate.Session;

public class TelaLogin extends javax.swing.JFrame {

 
    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTelaLoginNome = new javax.swing.JTextField();
        jButtonTelaLoginLogar = new javax.swing.JButton();
        jPasswordFieldTelaPrincipalSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/ico.png")).getImage());
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seja bem vindo"));

        jLabel1.setText("Nome");

        jLabel2.setText("Senha");

        jTextFieldTelaLoginNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelaLoginNomeActionPerformed(evt);
            }
        });

        jButtonTelaLoginLogar.setText("Logar");
        jButtonTelaLoginLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTelaLoginLogarActionPerformed(evt);
            }
        });

        jPasswordFieldTelaPrincipalSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldTelaPrincipalSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonTelaLoginLogar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTelaLoginNome, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldTelaPrincipalSenha))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTelaLoginNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPasswordFieldTelaPrincipalSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButtonTelaLoginLogar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTelaLoginLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTelaLoginLogarActionPerformed
        comecar();
    }//GEN-LAST:event_jButtonTelaLoginLogarActionPerformed

    private void jPasswordFieldTelaPrincipalSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldTelaPrincipalSenhaActionPerformed
        comecar();
    }//GEN-LAST:event_jPasswordFieldTelaPrincipalSenhaActionPerformed

    private void jTextFieldTelaLoginNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelaLoginNomeActionPerformed
        comecar();
    }//GEN-LAST:event_jTextFieldTelaLoginNomeActionPerformed
    private void comecar(){

        Session session = null;
        try {

            String log = jTextFieldTelaLoginNome.getText();
            String pass = new String(jPasswordFieldTelaPrincipalSenha.getPassword());
            DaoUsuario du = new DaoUsuario();
            User usuario = du.BuscaUsuario(log, pass);
            if (usuario != null) {
             
                Properties prop = new Properties();
                File caminho = new File("properties/hibernate.properties");
                File dir = new File("properties/");
                
                if(!caminho.exists()){
                   dir.mkdir();
                   caminho.createNewFile();
                }
                prop.setProperty("hibernate.connection.username", log);
                prop.setProperty("hibernate.connection.password", pass);
                
                FileOutputStream fo;
                
                try {   
                    fo = new FileOutputStream(caminho);
                    prop.store(fo, null);
                    fo.close();
                } catch (IOException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                          
                new TelaPrincipal().setVisible(true);
                dispose();
                
            } else {

                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            jTextFieldTelaLoginNome.setText("");
            jPasswordFieldTelaPrincipalSenha.setText("");
            jTextFieldTelaLoginNome.setFocusable(true);
            jTextFieldTelaLoginNome.requestFocus();
            JOptionPane.showMessageDialog(this, "Verifique o nome do usuário e a senha", "Login", JOptionPane.OK_OPTION);
        } finally {
            if (session != null) {
                session.close();
            }
        }
}

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonTelaLoginLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldTelaPrincipalSenha;
    private javax.swing.JTextField jTextFieldTelaLoginNome;
    // End of variables declaration//GEN-END:variables
}
