package demo;

import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;

public class frmAnalizador extends javax.swing.JFrame {

    public static DefaultTableModel modelo;

    public frmAnalizador() {
        initComponents();
        setLocationRelativeTo(null);
        modelo = new DefaultTableModel();
        modelo.addColumn("Token");
        modelo.addColumn("Observaciones");
        tabla.setModel(modelo);
    }
    //Variable global 
    String entrada;

    public void analizar() {
        HashMap<String, Integer> reservadas = new HashMap<>();
        HashMap<String, Integer> metodos = new HashMap<>();
        HashMap<String, Integer> operadoresAritmeticos = new HashMap<>();
        HashMap<String, Integer> operadoresLogicos = new HashMap<>();
        HashMap<String, Integer> operadoresComparacion = new HashMap<>();
        HashMap<String, Integer> delimitadores = new HashMap<>();

        //Reservadas
        reservadas.put("if", 0);
        reservadas.put("int", 0);
        reservadas.put("String", 0);
        reservadas.put("double", 0);
        reservadas.put("float", 0);
        reservadas.put("long", 0);
        reservadas.put("new", 0);
        reservadas.put("boolean", 0);
        reservadas.put("break", 0);
        reservadas.put("char", 0);
        reservadas.put("continue", 0);
        reservadas.put("final", 0);
        reservadas.put("float", 0);
        reservadas.put("else", 0);
        reservadas.put("while", 0);
        reservadas.put("switch", 0);
        reservadas.put("case", 0);
        reservadas.put("for", 0);
        reservadas.put("try", 0);
        reservadas.put("catch", 0);
        reservadas.put("private", 0);
        reservadas.put("void", 0);
        reservadas.put("var", 0);
        reservadas.put("public", 0);
        reservadas.put("protected", 0);
        reservadas.put("return", 0);
        reservadas.put("do", 0);

        //Métodos
        metodos.put("System.out.println", 0);
        metodos.put("Math.pow", 0);
        metodos.put("Math.sqrt", 0);

        //Operadores Aritméticos
        operadoresAritmeticos.put("/", 0);
        operadoresAritmeticos.put("*", 0);
        operadoresAritmeticos.put("+", 0);
        operadoresAritmeticos.put("-", 0);
        operadoresAritmeticos.put("^", 0);

        //Operadores de Comparación
        operadoresComparacion.put("=", 0);
        operadoresComparacion.put("<", 0);
        operadoresComparacion.put(">", 0);
        operadoresComparacion.put(">=", 0);
        operadoresComparacion.put("<=", 0);

        //Operadores lógicos
        operadoresLogicos.put("&&", 0);
        operadoresLogicos.put("||", 0);
        operadoresLogicos.put("!=", 0);

        //Delimitadores
        delimitadores.put("\"", 0);
        delimitadores.put(";", 0);
        delimitadores.put("{", 0);
        delimitadores.put("}", 0);
        delimitadores.put(")", 0);
        delimitadores.put(",", 0);
        delimitadores.put("(", 0);
        delimitadores.put(":", 0);

        //Obtención de la cadena por parte del usuario
        entrada = txtCadena.getText();

        //Lógica
        StringTokenizer st = new StringTokenizer(entrada, "{}();,\"+-*/ \n\t", true);
        String token, cadena = "";

        while (st.hasMoreTokens()) {
            token = st.nextToken();

            if (!" ".equals(token) && !"\n".equals(token) && !"\t".equals(token)) {
                if (reservadas.containsKey(token)) {
                    reservadas.put(token, reservadas.get(token) + 1);
                    if (reservadas.get(token) > 0) {
                        modelo.addRow(new Object[]{token, "Palabra reservada"});
                    }
                } else if (metodos.containsKey(token)) {
                    metodos.put(token, metodos.get(token) + 1);
                    switch (token) {
                        case "System.out.println" ->
                            modelo.addRow(new Object[]{token, "Método Println"});
                        case "Math.pow" ->
                            modelo.addRow(new Object[]{token, "Método Math Pow"});
                        case "Math.sqrt" ->
                            modelo.addRow(new Object[]{token, "Método Math Sqrt"});

                    }
                } else if (operadoresAritmeticos.containsKey(token)) {
                    switch (token) {
                        case "+" ->
                            modelo.addRow(new Object[]{token, "Operador Aritmetico Suma"});
                        case "-" ->
                            modelo.addRow(new Object[]{token, "Operador Aritmetico Resta"});
                        case "*" ->
                            modelo.addRow(new Object[]{token, "Operador Aritmetico Multiplicar"});
                        case "/" ->
                            modelo.addRow(new Object[]{token, "Operador Aritmetico de División"});
                    }
                } else if (operadoresComparacion.containsKey(token)) {
                    switch (token) {
                        case "<" ->
                            modelo.addRow(new Object[]{token, "Operador de Comparación Menor que"});
                        case ">" ->
                            modelo.addRow(new Object[]{token, "Operador de Comparación Mayor que"});
                        case "<=" ->
                            modelo.addRow(new Object[]{token, "Operador de Comparación Menor igual que"});
                        case ">=" ->
                            modelo.addRow(new Object[]{token, "Operador de Comparación Mayor igual que"});
                        case "=" ->
                            modelo.addRow(new Object[]{token, "Operador Igual que"});
                    }
                } else if (operadoresLogicos.containsKey(token)) {
                    switch (token) {
                        case "&&" ->
                            modelo.addRow(new Object[]{token, "Operador Lógico AND"});
                        case "||" ->
                            modelo.addRow(new Object[]{token, "Operador Lógico OR"});
                        case "!=" ->
                            modelo.addRow(new Object[]{token, "Operador Lógico NOT"});

                    }
                } else if (delimitadores.containsKey(token)) {
                    delimitadores.put(token, delimitadores.get(token) + 1);
                    //Identificación de Cadenas
                    if ("\"".equals(token)) {
                        token = st.nextToken();
                        while (st.hasMoreTokens() && !"\"".equals(token)) {
                            cadena += token;
                            token = st.nextToken();

                        }
                        modelo.addRow(new Object[]{cadena, "Cadena"});
                        cadena = "";
                    }
                    //Fin Cadena

                    if (delimitadores.get(token) > 0) {
                        modelo.addRow(new Object[]{token, "Delimitador"});
                    }

                } else if (token.matches("([0-9]*)|([0-9]*.[0-9]+)")) {
                    double numero = Double.parseDouble(token);
                    if (numero % 1 == 0) {
                        modelo.addRow(new Object[]{token, "Número"});
                    } else {
                        modelo.addRow(new Object[]{token, "Número Decimal"});
                    }

                } else {
                    modelo.addRow(new Object[]{token, "Identificador"});
                }

            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        txtCadena = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N
        jLabel1.setText("Cadena");

        btnProcesar.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        txtCadena.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N

        tabla.setFont(new java.awt.Font("Garamond", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jLabel2.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel2.setText("Analizador Léxico - Grupo 7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCadena))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)))
                        .addGap(18, 18, 18)
                        .addComponent(btnProcesar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnProcesar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed

        //Limpia la Tabla
        for (int i = 0; i < tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        analizar();
    }//GEN-LAST:event_btnProcesarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(frmAnalizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAnalizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAnalizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAnalizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAnalizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCadena;
    // End of variables declaration//GEN-END:variables
}
