// TODO: Think about implementing a DB ... 
// TODO: Plan acumulativo ..., es decir, si aparecen nuevas órdenes ... que cotinue en el día que se quedó ...
// TODO: recibir un mapeo para Punzonado para private final Map<String, String> partMachineInfo = new HashMap<>();
// TODO: tomar en cuenta que cuando hay partes iguales en un WorkCenter ... un mismo setup aplica para ello.
// Es decir: si hay dos part numbers iguales, solo el primero tendrïa un setup ...
// el segundo se aprovecha
// TODO: think about how the files will be generated ...
package com.production;

import com.production.domain.Priority;
import com.production.domain.WorkOrderInformation;
import com.production.util.Constants;
import com.production.util.Utils;

import java.io.File;
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
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import static com.production.util.Constants.PART_MACHINE_FILE_NAME;
import static com.production.util.Constants.FIRST_TURN_LENGTH;
import static com.production.util.Constants.SECOND_TURN_LENGTH;
import static com.production.util.Utils.extractWorkOrdersFromSheetFile;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

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
    }
    
    private void loadPartMachineInformation() {
        final File partMachineCSVFile = new File(PART_MACHINE_FILE_NAME);
        if (!partMachineCSVFile.exists()) {
            showWarningMessage(String.format("El archivo '%s' no fue encontrado, los comentarios o máquinas no serán cargados.", PART_MACHINE_FILE_NAME), "Warning ... ");
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
            showErrorMessage(String.format("error: %s", ex.getMessage()), "Error");
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
        wcDescriptions = new javax.swing.JComboBox<>();
        generatePlanBtn = new javax.swing.JButton();
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
                return true;
            }
        }

    );
    workOrderTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    workOrderTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

    wcDescriptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DOBLADO", "EMPAQUE_A_PROVEEDOR", "EMPAQUE_FINAL", "ENSAMBLE", "INSERTOS_PEM", "INSPECCION_DE_ACABADOS", "LASER", "LIMPIEZA", "LIMPIEZA_LUZ_NEGRA", "MAQUINADO_CNC", "MAQUINADO_MANUAL", "PINTURA_EN_POLVO", "PULIDO", "PUNZONADO", "REBABEO", "SERIGRAFIA", "SOLDADURA", "SPOT_WELD", "SURTIR_MATERIAL", "TIME_SAVER", "TRATAMIENTO_QUIMICO" }));
    wcDescriptions.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            wcDescriptionsActionPerformed(evt);
        }
    });

    generatePlanBtn.setMnemonic('G');
    generatePlanBtn.setText("Generate Plan");
    generatePlanBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            generatePlanBtnActionPerformed(evt);
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(wcDescriptions, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearButton)
                            .addGap(18, 18, 18)
                            .addComponent(testButton))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(moveToSelectedPrioritiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(generatePlanBtn)))
                    .addContainerGap(20, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(24, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(testButton)
                        .addComponent(clearButton)
                        .addComponent(generatePlanBtn))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(wcDescriptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
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
    
    private void extractWorkOrderItemsFromFile(final File file) throws IOException, InvalidFormatException {
        final List<WorkOrderInformation> workOrdersFromSheetFile = extractWorkOrdersFromSheetFile(file.getAbsolutePath());
        this.workOrderInformationItems = Optional.of(workOrdersFromSheetFile);
    }
    
    private void showErrorMessage(final String message, final String title) {
        JOptionPane.showMessageDialog(this, message, title, ERROR_MESSAGE);
    }
    
    private void showWarningMessage(final String message, final String title) {
        JOptionPane.showMessageDialog(this, message, title, WARNING_MESSAGE);
    }
    
    private void openFabLoadByWCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFabLoadByWCMenuItemActionPerformed
        // Note, the following code can be changed to use something like:
        // new JFileChooser(System.getProperty("user.home"))
        final JFileChooser jfc = Utils.genericXLSFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.fabLoadFilePath = jfc.getSelectedFile();
            try {
                this.extractWorkOrderItemsFromFile(this.fabLoadFilePath);
            } catch (IOException | InvalidFormatException ex) {
                showErrorMessage(String.format("error loading Fab Load by WC File: %s", ex.getMessage()), "Error");
            }
        }
        updateStatusBar();
    }//GEN-LAST:event_openFabLoadByWCMenuItemActionPerformed

    private void reconcileInformationAndUpdateTable(
            final File file
            , final List<WorkOrderInformation> workOrderItems) throws IOException, InvalidFormatException {
        Utils.reconcileInformationFromAgeFile(file.getAbsolutePath(), workOrderItems);
        this.updateTable(workOrderItems, this.workOrderTable);
    }
    
    private void openAgeByWCFileItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAgeByWCFileItemActionPerformed
        final JFileChooser jfc = Utils.genericXLSFileChooser();

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.ageByWCFilePath = jfc.getSelectedFile();
            this.workOrderInformationItems.ifPresent(workOrderItems -> {
                try {
                    this.reconcileInformationAndUpdateTable(this.ageByWCFilePath, workOrderItems);
                } catch (IOException | InvalidFormatException ex) {
                    showErrorMessage(String.format("error loading Age file: %s", ex.getMessage()), "Error");
                }
            });
        }
        updateStatusBar();
    }//GEN-LAST:event_openAgeByWCFileItemActionPerformed

    private void updateTable(final List<WorkOrderInformation> workOrderItems, final JTable table) {        
        final DefaultTableModel workOrdersModel = (DefaultTableModel) table.getModel();
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
        
        final DefaultTableModel workOrdersModel = (DefaultTableModel) this.workOrderTable.getModel();
        
        workOrdersModel.addRow(contentToAdd);
        workOrdersModel.addRow(contentToAdd2);
        workOrdersModel.addRow(contentToAdd3);
        workOrdersModel.addRow(contentToAdd4);
    }//GEN-LAST:event_testButtonActionPerformed

    private void moveToSelectedPrioritiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToSelectedPrioritiesButtonActionPerformed
        
        final int[] rowIndexesToRemove = this.workOrderTable.getSelectedRows();
        if (rowIndexesToRemove.length == 0) {
            return;
        }
        
        final DefaultTableModel selectedPrioritiesModel = (DefaultTableModel) this.selectedPrioritiesTable.getModel();
        final DefaultTableModel workOrdersModel = (DefaultTableModel) this.workOrderTable.getModel();
        
        final int rowCount = selectedPrioritiesModel.getRowCount();
        int priority = rowCount > 0 ? (rowCount + 1) : 1;
        for (int rowIndex : rowIndexesToRemove) {
            final String ptNumber = Utils.getPartNumberFromRow(this.workOrderTable.getModel(), rowIndex);
            final String[] data = {priority + "", ptNumber};
            selectedPrioritiesModel.addRow(data);
            priority++;
        }
        
        // Remove indexes from the table ...
        int numRows = workOrdersModel.getRowCount();
        
        for (final int rowIndexToRemove : rowIndexesToRemove) {
            workOrdersModel.removeRow(rowIndexToRemove);
            this.workOrderTable.clearSelection();
            workOrdersModel.setRowCount(--numRows);
            workOrdersModel.fireTableDataChanged();
            this.workOrderTable.repaint();
        }
        
    }//GEN-LAST:event_moveToSelectedPrioritiesButtonActionPerformed

    private void cleanTable(final JTable table) {
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        table.clearSelection();
    }
    
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        ((DefaultTableModel) this.selectedPrioritiesTable.getModel()).setRowCount(0);
        this.workOrderTable.clearSelection();
        
        final String selectedWCDescription = this.wcDescriptions.getSelectedItem().toString();
        this.workOrderInformationItems.ifPresent(workOrderItems -> {
            cleanTable(this.workOrderTable);
            updateTableWithWCDescription(selectedWCDescription, workOrderItems, this.workOrderTable);
        });
    }//GEN-LAST:event_clearButtonActionPerformed

    private void findFilesInCurrentPathMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findFilesInCurrentPathMenuItemActionPerformed
        
        try {
            final String jarFilepath = MainWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            final File jf = new File(jarFilepath);
            this.jarPath = URLDecoder.decode(jf.getParent(), "UTF-8");
            
            final Path fabLoadByWCPath = Paths.get(this.jarPath, Constants.FAB_LOAD_FILE_NAME);
            final File fabLoadByWCFile = fabLoadByWCPath.toFile();
            final Path ageByWCPath = Paths.get(this.jarPath, Constants.AGE_BY_WC_FILE_NAME);
            final File ageByWCFile = ageByWCPath.toFile();
            
            if (fabLoadByWCFile.exists() && ageByWCFile.exists()) {
                // Clean the tables ... 
                cleanTable(this.workOrderTable);
                cleanTable(this.selectedPrioritiesTable);
                
                this.fabLoadFilePath = fabLoadByWCFile;
                this.ageByWCFilePath = ageByWCFile;
                
                this.extractWorkOrderItemsFromFile(this.fabLoadFilePath);
                this.workOrderInformationItems.ifPresent(workOrderItems -> {
                    try {
                        this.reconcileInformationAndUpdateTable(this.ageByWCFilePath, workOrderItems);
                        this.wcDescriptions.setSelectedIndex(0);
                    } catch (IOException | InvalidFormatException ex) {
                        showErrorMessage(String.format("error loading Age file: %s", ex.getMessage()), "Error");
                    }
                });
                
            } else {
                showErrorMessage("Error: los archivos necesarios no existen en el directorio actual.", "Error");
                return;
            }
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateStatusBar();
    }//GEN-LAST:event_findFilesInCurrentPathMenuItemActionPerformed

    private void updateTableWithWCDescription(
            final String wcDescription
            , final List<WorkOrderInformation> workOrderItems
            , final JTable table
    ) {
        
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        
         workOrderItems
                 .stream()
                 .filter(wo -> wo.getWcDescription().equalsIgnoreCase(wcDescription))
                 .forEach(item -> {
                    final String machine = this.partMachineInfo.getOrDefault(item.getPartNumber(), "");
                    final Object[] data = {
                        item.getPartNumber()
                        , item.getRunHours()
                        , item.getSetupHours()
                        , item.getQty()
                        , machine
                    };
                    model.addRow(data);
                 });
    }
    
    private void wcDescriptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wcDescriptionsActionPerformed
        cleanTable(this.selectedPrioritiesTable);
        
        final String selectedItem = this.wcDescriptions.getSelectedItem().toString();
        this.workOrderInformationItems.ifPresent(workOrderItems -> {
            cleanTable(this.workOrderTable);
            updateTableWithWCDescription(selectedItem, workOrderItems, this.workOrderTable);
        });
    }//GEN-LAST:event_wcDescriptionsActionPerformed

    private void generatePlanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePlanBtnActionPerformed
        final DefaultTableModel model = (DefaultTableModel) selectedPrioritiesTable.getModel();
        
        final String wcDescription = this.wcDescriptions.getSelectedItem().toString();
        
        final List<Priority> priorities = buildPrioritiesFromTable(model);
        
        this.workOrderInformationItems.ifPresentOrElse(workOrderItems -> {
            final List<WorkOrderInformation> workOrderItemsByWCDescription = workOrderItems
                    .stream()
                    .filter(wo -> wo.getWcDescription().equalsIgnoreCase(wcDescription))
                    .collect(Collectors.toList());
            final String htmlContent = buildHtmlContent(wcDescription, workOrderItemsByWCDescription, priorities);
            
        }, () -> {
            
        });
    }//GEN-LAST:event_generatePlanBtnActionPerformed

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
    private javax.swing.JButton generatePlanBtn;
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
    private javax.swing.JComboBox<String> wcDescriptions;
    private javax.swing.JTable workOrderTable;
    // End of variables declaration//GEN-END:variables

    private List<Priority> buildPrioritiesFromTable(final DefaultTableModel model) {
        final int rowCount = model.getRowCount();
        if (rowCount <= 0) {
            return Collections.EMPTY_LIST;
        }
        final List<Priority> priorities = new ArrayList<>();
        
        for (int row = 0; row < rowCount; row++) {
            final Priority priority = new Priority();
            priority.setOrder(Integer.parseInt(model.getValueAt(row, 0).toString()));
            priority.setPartNumber(model.getValueAt(row, 1).toString());
            priorities.add(priority);
        }
        
        return priorities;
    }

    private String buildHtmlContent(
            final String wcDescription
            , final List<WorkOrderInformation> workOrderItems
            , final List<Priority> priorities) {
        // TODO: remove magic numbers ... 
        
        double sumHoursTurns = 0.0D;
        workOrderItems.forEach(wo -> {
            final double woHours = wo.getRunHours() + wo.getSetupHours();
            final double woHoursAdded = woHours + sumHoursTurns;
            if (woHoursAdded <= FIRST_TURN_LENGTH) {
                
            } else if (woHoursAdded > FIRST_TURN_LENGTH && woHoursAdded <= (FIRST_TURN_LENGTH + SECOND_TURN_LENGTH)) {
                
            } else if (woHoursAdded <= (FIRST_TURN_LENGTH + SECOND_TURN_LENGTH)) {
                
            }
        });
        
        return "";
    }
    
}
