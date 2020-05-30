// TODO: Put the information in the table ...
// TODO: Need to modify the domains to store the WC Description ... "Doblado" as an example.
    // TODO: Need a Dropdown here. 

package com.production;

import com.production.domain.WorkOrderInformation;
import com.production.util.Utils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import static com.production.util.Constants.PART_MACHINE_FILE_NAME;
import static com.production.util.Utils.extractWorkOrdersFromSheetFile;
import javax.swing.JTable;

/**
 * @author lgutierr <leogutierrezramirez@gmail.com>
 */
public class MainWindow extends javax.swing.JFrame {
    
    private File fabLoadFilePath = null;
    private File ageByWCFilePath = null;
    private String jarPath = null;
    private final Map<String, String> partMachineInfo = new HashMap<>();
    private Optional<List<WorkOrderInformation>> workOrderInformationItems = Optional.empty();
    
    public MainWindow() {
        initComponents();
        updateStatusBar();
        loadPartMachineInformation();
        // workOrderInformationItems.
    }
    
    private void loadPartMachineInformation() {
        final File partMachineCSVFile = new File(PART_MACHINE_FILE_NAME);
        if (!partMachineCSVFile.exists()) {
            JOptionPane.showMessageDialog(
                this
                , String.format("El archivo '%s' no fue encontrado, los comentarios o máquinas no serán cargados.", PART_MACHINE_FILE_NAME)
                , "Warning ... "
                , JOptionPane.WARNING_MESSAGE
            );
            return;
        }
            
        try {
            
            final List<String> lines = Files.readAllLines(Paths.get(PART_MACHINE_FILE_NAME));
            for (int i = 0; i < lines.size(); i++) {
                // Skip the header:
                if (i == 0) {
                    continue;
                }
                final String line = lines.get(i);
                final String[] tokens = line.split(",");
                this.partMachineInfo.put(tokens[0], tokens[1]);
            }
        } catch (final IOException ex) {
            JOptionPane.showMessageDialog(this, String.format("error: %s", ex.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
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

        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workOrderTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        selectedPrioritiesTable = new javax.swing.JTable();
        moveToSelectedPrioritiesButton = new javax.swing.JButton();
        testButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFabLoadByWCMenuItem = new javax.swing.JMenuItem();
        openAgeByWCFileItem = new javax.swing.JMenuItem();
        findFilesInCurrentPathMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Production Plan Priorities");
        setMaximumSize(new java.awt.Dimension(1000, 625));
        setMinimumSize(new java.awt.Dimension(1000, 625));
        setPreferredSize(new java.awt.Dimension(1000, 625));
        setResizable(false);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusLabel.setText("Status:");

        workOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Part", "Hr", "Stup", "P/Hac", "Máquina"
            }
        )
        {
            @Override public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        }

    );
    workOrderTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    jScrollPane1.setViewportView(workOrderTable);

    selectedPrioritiesTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "#", "#Part Number"
        }
    )
    {
        @Override public boolean isCellEditable(final int row, final int column) {
            return false;
        }
    }
    );
    jScrollPane2.setViewportView(selectedPrioritiesTable);

    moveToSelectedPrioritiesButton.setMnemonic('n');
    moveToSelectedPrioritiesButton.setText(">");
    moveToSelectedPrioritiesButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next-icon.png"))); // NOI18N
    moveToSelectedPrioritiesButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            moveToSelectedPrioritiesButtonActionPerformed(evt);
        }
    });

    testButton.setText("Test");
    testButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            testButtonActionPerformed(evt);
        }
    });

    clearButton.setMnemonic('C');
    clearButton.setText("Clear");
    clearButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearButtonActionPerformed(evt);
        }
    });

    fileMenu.setText("File");

    openFabLoadByWCMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
    openFabLoadByWCMenuItem.setText("Open \"FAB Load by WC\" file");
    openFabLoadByWCMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            openFabLoadByWCMenuItemActionPerformed(evt);
        }
    });
    fileMenu.add(openFabLoadByWCMenuItem);

    openAgeByWCFileItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
    openAgeByWCFileItem.setText("Open \"Age  by WC\" file");
    openAgeByWCFileItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            openAgeByWCFileItemActionPerformed(evt);
        }
    });
    fileMenu.add(openAgeByWCFileItem);

    findFilesInCurrentPathMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
    findFilesInCurrentPathMenuItem.setText("Find files");
    findFilesInCurrentPathMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            findFilesInCurrentPathMenuItemActionPerformed(evt);
        }
    });
    fileMenu.add(findFilesInCurrentPathMenuItem);

    menuBar.add(fileMenu);

    editMenu.setText("Edit");
    menuBar.add(editMenu);

    helpMenu.setMnemonic('H');
    helpMenu.setText("Help");
    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(clearButton)
                            .addGap(18, 18, 18)
                            .addComponent(testButton))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(moveToSelectedPrioritiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(24, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(testButton)
                .addComponent(clearButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(moveToSelectedPrioritiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(229, 229, 229))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
            .addComponent(statusLabel)
            .addGap(55, 55, 55))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateStatusBar() {
        if (this.fabLoadFilePath == null && this.ageByWCFilePath == null) {
            this.statusLabel.setText("Please open the required files.");
        } else if (this.fabLoadFilePath == null && this.ageByWCFilePath != null) {
            this.statusLabel.setText("Please open the file containing the 'FAB Load by WC' information.");
        } else if (this.fabLoadFilePath != null && this.ageByWCFilePath == null) {
            this.statusLabel.setText("Please open the file containing the 'Age  by WC' information.");
        } else if (this.fabLoadFilePath != null && this.ageByWCFilePath != null) {
            this.statusLabel.setText("Files ready.");
        }
    }
    
    private void openFabLoadByWCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFabLoadByWCMenuItemActionPerformed
                
        // Note, the following code can be changed to use something like:
        // new JFileChooser(System.getProperty("user.home"))
        final JFileChooser jfc = Utils.genericXLSFileChooser();

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.fabLoadFilePath = jfc.getSelectedFile();
            try {
                final List<WorkOrderInformation> workOrdersFromSheetFile = 
                        extractWorkOrdersFromSheetFile(this.fabLoadFilePath.getAbsolutePath());
                this.workOrderInformationItems = Optional.of(workOrdersFromSheetFile);
                this.workOrderInformationItems.ifPresent(items -> items.forEach(System.out::println));
            } catch (IOException | InvalidFormatException ex) {
                JOptionPane.showMessageDialog(
                    this
                    , String.format("error loading Fab Load by WC File: %s", ex.getMessage())
                    , "Error"
                    , JOptionPane.ERROR_MESSAGE
                );
            }
        }
        updateStatusBar();
    }//GEN-LAST:event_openFabLoadByWCMenuItemActionPerformed

    private void openAgeByWCFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAgeByWCFileItemActionPerformed
        final JFileChooser jfc = Utils.genericXLSFileChooser();

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.ageByWCFilePath = jfc.getSelectedFile();
            this.workOrderInformationItems.ifPresent(workOrderItems -> {
                try {
                    Utils.reconcileInformationFromAgeFile(this.ageByWCFilePath.getAbsolutePath(), workOrderItems);
                    this.updateTable(workOrderItems, workOrderTable);
                } catch (IOException | InvalidFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this
                        , String.format("error loading Age file: %s", ex.getMessage())
                        , "Error"
                        , JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }
        updateStatusBar();
    }//GEN-LAST:event_openAgeByWCFileItemActionPerformed

    private void updateTable(final List<WorkOrderInformation> workOrderItems, final JTable table) {        
        final DefaultTableModel workOrdersModel = (DefaultTableModel) workOrderTable.getModel();
        
        // "#Part", "Hr", "Stup", "P/Hac", "Máquina"
        workOrderItems.forEach(item -> {
            final String machine = this.partMachineInfo.getOrDefault(item.getPartNumber(), "");
            final Object[] data = {
                item.getPartNumber()
                , item.getRunHours()
                , item.getSetupHours()
                , item.getQty()
                , machine
            };
            workOrdersModel.addRow(data);
        });
    }
    
    // TODO: this method should be removed ... 
    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        
        final Object[] contentToAdd = {
            "4022.637.70102   ",
            "7.2",
            "0.4",
            "5",
            "3"
        };
        
        final Object[] contentToAdd2 = {
            "3022.637.70102   ",
            "7.2",
            "0.4",
            "5",
            "3"
        };
        
        final Object[] contentToAdd3 = {
            "5022.637.70102   ",
            "7.2",
            "0.4",
            "5",
            "3"
        };
        
        final Object[] contentToAdd4 = {
            "7022.637.70102   ",
            "7.2",
            "0.4",
            "5",
            "3"
        };
        
        final DefaultTableModel workOrdersModel = (DefaultTableModel) workOrderTable.getModel();
        
        workOrdersModel.addRow(contentToAdd);
        workOrdersModel.addRow(contentToAdd2);
        workOrdersModel.addRow(contentToAdd3);
        workOrdersModel.addRow(contentToAdd4);
    }//GEN-LAST:event_testButtonActionPerformed

    private void moveToSelectedPrioritiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToSelectedPrioritiesButtonActionPerformed
        
        final int[] selectedRowsIndexes = workOrderTable.getSelectedRows();
        if (selectedRowsIndexes.length == 0) {
            return;
        }
        
        final DefaultTableModel selectedPrioritiesModel = (DefaultTableModel) selectedPrioritiesTable.getModel();
        
        final int rowCount = selectedPrioritiesModel.getRowCount();
        
        int priority = rowCount > 0 ? (rowCount + 1) : 1;
        for (int rowIndex : selectedRowsIndexes) {
            final String ptNumber = Utils.getPartNumberFromRow(workOrderTable.getModel(), rowIndex);
            final String[] data = {priority + "", ptNumber};
            selectedPrioritiesModel.addRow(data);
            priority++;
        }
        
    }//GEN-LAST:event_moveToSelectedPrioritiesButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        ((DefaultTableModel)selectedPrioritiesTable.getModel()).setRowCount(0);
        workOrderTable.clearSelection();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void findFilesInCurrentPathMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findFilesInCurrentPathMenuItemActionPerformed
        
        try {
            final String jarFilepath = MainWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            final File jf = new File(jarFilepath);
            this.jarPath = URLDecoder.decode(jf.getParent(), "UTF-8");
            
            // TODO: fix this ... the word "Leo" shouldn't be in the path, read it from a constant.
            final Path fabLoadByWCPath = Paths.get(this.jarPath, "FAB Load by WC Leo.xls");
            final File fabLoadByWCFile = fabLoadByWCPath.toFile();
            if (fabLoadByWCFile.exists()) {
                this.fabLoadFilePath = fabLoadByWCFile;
            }
            
            // TODO: Use the name from a constant ..
            final Path ageByWCPath = Paths.get(this.jarPath, "Age  by WC.xls");
            final File ageByWCFile = ageByWCPath.toFile();
            if (ageByWCFile.exists()) {
                this.ageByWCFilePath = ageByWCFile;
            }
        } catch (final UnsupportedEncodingException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateStatusBar();
    }//GEN-LAST:event_findFilesInCurrentPathMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem findFilesInCurrentPathMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton moveToSelectedPrioritiesButton;
    private javax.swing.JMenuItem openAgeByWCFileItem;
    private javax.swing.JMenuItem openFabLoadByWCMenuItem;
    private javax.swing.JTable selectedPrioritiesTable;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton testButton;
    private javax.swing.JTable workOrderTable;
    // End of variables declaration//GEN-END:variables
}
