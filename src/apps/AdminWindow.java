package apps;

import DB.DBConnection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AdminWindow extends javax.swing.JFrame {

    String user;

    public AdminWindow() {
        initComponents();
        CurrentDate();
    }

    public AdminWindow(String str) {
        initComponents();
        user = str;
        jLabel25.setText(user);
        CurrentDate();
    }

    public void CurrentDate() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    jLabel28.setText(day + "-" + (month + 1) + "-" + year);

                    int second = cal.get(Calendar.SECOND);
                    int minutes = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    jLabel105.setText(hour + ":" + (minutes) + ":" + second);

                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        clock.start();
    }

    void showViewReorderList()
    {
    try
    {
    Vector<String> header=new Vector<String>();
    Vector<Vector<String>> data=new Vector<Vector<String>>();
    header.add("Medicines Name");
    header.add("Company Name");
    header.add("Composition");
    header.add("Distributer Name");
    header.add("Distributer Contact");
    header.add("Company Contact");
    header.add("Available Quantity");
    DBConnection db=new DBConnection();
    db.pstmt=db.con.prepareStatement("select product_mstr.med_name,product_mstr.med_company,product_mstr.med_formula,product_mstr.med_sup,product_mstr.med_contact,product_mstr.med_cont,stock_detail.med_qty from product_mstr,stock_detail where product_mstr.med_name=stock_detail.med_name and stock_detail.med_qty<=stock_detail.reorder_level");
    db.rst=db.pstmt.executeQuery();
    while(db.rst.next())
    {
    Vector<String> temp=new Vector<String>();
    temp.add(db.rst.getString(1));
    temp.add(db.rst.getString(2));
    temp.add(db.rst.getString(3));
    temp.add(db.rst.getString(4));
    temp.add(db.rst.getString(5));
    temp.add(db.rst.getString(6));
    temp.add(db.rst.getString(7));
    data.add(temp);
    }
    jTable3.setModel(new DefaultTableModel(data, header));
    ReorderList.setVisible(true);
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    

    void showViewRegisterMedicine()
    {
    try
    {
    Vector<String> header=new Vector<String>();
    Vector<Vector<String>> data=new Vector<Vector<String>>();
    header.add("Medicines Name");
    header.add("Company Name");
    header.add("Composition");
    header.add("Distribeter Name");
    header.add("Distributer Contact");
    header.add("Company Contact");
    header.add("Medicine Purpose");
    DBConnection db=new DBConnection();
    db.pstmt=db.con.prepareStatement("select * from product_mstr");
    db.rst=db.pstmt.executeQuery();
    while(db.rst.next())
    {
    Vector<String> temp=new Vector<String>();
    temp.add(db.rst.getString(1));
    temp.add(db.rst.getString(2));
    temp.add(db.rst.getString(3));
    temp.add(db.rst.getString(4));
    temp.add(db.rst.getString(5));
    temp.add(db.rst.getString(6));
    temp.add(db.rst.getString(7));
    data.add(temp);
    }
    jTable4.setModel(new DefaultTableModel(data, header));
    ViewRegisterMedicine.setVisible(true);
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    
    
    void showViewStock()
    {
    try
    {
    Vector<String> header=new Vector<String>();
    Vector<Vector<String>> data=new Vector<Vector<String>>();
    header.add("Medicines Name");
    header.add("Medicine CP");
    header.add("Medicine SP");
    header.add("Manufactured Date");
    header.add("Expiry Date");
    header.add("Reorder Level");
    header.add("Medicine Quantity");
    DBConnection db=new DBConnection();
    db.pstmt=db.con.prepareStatement("select * from stock_detail");
    db.rst=db.pstmt.executeQuery();
    while(db.rst.next())
    {
    Vector<String> temp=new Vector<String>();
    temp.add(db.rst.getString(1));
    temp.add(db.rst.getString(2));
    temp.add(db.rst.getString(3));
    temp.add(db.rst.getString(4));
    temp.add(db.rst.getString(5));
    temp.add(db.rst.getString(6));
    temp.add(db.rst.getString(7));
    data.add(temp);
    }
    jTable5.setModel(new DefaultTableModel(data, header));
    ViewStock.setVisible(true);
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    
    
    
    void showAllInvoices()
    {
    try
    {
    Vector<String> header=new Vector<String>();
    Vector<Vector<String>> data=new Vector<Vector<String>>();
    header.add("Invoice Number");
    header.add("Medicine Name");
    header.add("Medicine Company");
    header.add("Medicine Formula");
    header.add("Medicine Quantity");
    header.add("Date and Time");
    header.add("Customer Name");
    header.add("Customer Contact");
    header.add("Bill Amount");
    DBConnection db=new DBConnection();
    db.pstmt=db.con.prepareStatement("SELECT bill_detail.invc_no,invoice.med_name,product_mstr.med_company,product_mstr.med_formula,invoice.med_qty,bill_detail.date_time,bill_detail.cust_name,bill_detail.cust_cont,bill_detail.bill_amt FROM ((invoice INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name) INNER JOIN bill_detail ON invoice.cust_name = bill_detail.cust_name);");
    db.rst=db.pstmt.executeQuery();
    while(db.rst.next())
    {
    Vector<String> temp=new Vector<String>();
    temp.add(db.rst.getString(1));
    temp.add(db.rst.getString(2));
    temp.add(db.rst.getString(3));
    temp.add(db.rst.getString(4));
    temp.add(db.rst.getString(5));
    temp.add(db.rst.getString(6));
    temp.add(db.rst.getString(7));
    temp.add(db.rst.getString(8));
    temp.add(db.rst.getString(9));
    data.add(temp);
    }
    jTable6.setModel(new DefaultTableModel(data, header));
    ViewAllInvoices.setVisible(true);
    jTable6.setEnabled(false);
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
    }
    
    
    
    void showSearchInvoiceByInvoiceNumber()
    {
        try {
            String ino = jTextField57.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select invoice_id from invoice where invoice_id = ?");
            db.pstmt.setString(1, ino);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                Vector<String> header = new Vector<String>();
                Vector<Vector<String>> data = new Vector<Vector<String>>();
                header.add("Invoice Number");
                header.add("Medicine Name");
                header.add("Medicine Company");
                header.add("Medicine Formula");
                header.add("Medicine Quantity");
                header.add("Date and Time");
                header.add("Customer Name");
                header.add("Customer Contact");
                header.add("Bill Amount");

                db.pstmt = db.con.prepareStatement("SELECT bill_detail.invc_no,invoice.med_name,product_mstr.med_company,product_mstr.med_formula,invoice.med_qty,bill_detail.date_time,bill_detail.cust_name,bill_detail.cust_cont,bill_detail.bill_amt FROM ((invoice INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name) INNER JOIN bill_detail ON invoice.cust_name = bill_detail.cust_name and invoice_id = ?);");
                db.pstmt.setString(1, ino);
                db.rst = db.pstmt.executeQuery();
                while (db.rst.next()) {
                    Vector<String> temp = new Vector<String>();
                    temp.add(db.rst.getString(1));
                    temp.add(db.rst.getString(2));
                    temp.add(db.rst.getString(3));
                    temp.add(db.rst.getString(4));
                    temp.add(db.rst.getString(5));
                    temp.add(db.rst.getString(6));
                    temp.add(db.rst.getString(7));
                    temp.add(db.rst.getString(8));
                    temp.add(db.rst.getString(9));
                    data.add(temp);

                    jTable7.setModel(new DefaultTableModel(data, header));
                    jTextField57.setEditable(true);
                    jTable7.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invoice ID Doesn't Exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    } 
    
    
    
     void showSearchInvoiceByCustomerNumber()
    {
        try {
            String cno = jTextField58.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select cust_contact from invoice where cust_contact= ?");
            db.pstmt.setString(1, cno);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                Vector<String> header = new Vector<String>();
                Vector<Vector<String>> data = new Vector<Vector<String>>();
                header.add("Invoice Number");
                header.add("Medicine Name");
                header.add("Medicine Company");
                header.add("Medicine Formula");
                header.add("Medicine Quantity");
                header.add("Date and Time");
                header.add("Customer Name");
                header.add("Customer Contact");
                header.add("Bill Amount");

                db.pstmt = db.con.prepareStatement("SELECT bill_detail.invc_no,invoice.med_name,product_mstr.med_company,product_mstr.med_formula,invoice.med_qty,bill_detail.date_time,bill_detail.cust_name,bill_detail.cust_cont,bill_detail.bill_amt FROM ((invoice INNER JOIN product_mstr ON invoice.med_name = product_mstr.med_name) INNER JOIN bill_detail ON invoice.cust_name = bill_detail.cust_name and cust_contact = ?);");
                db.pstmt.setString(1, cno);
                db.rst = db.pstmt.executeQuery();
                while (db.rst.next()) {
                    Vector<String> temp = new Vector<String>();
                    temp.add(db.rst.getString(1));
                    temp.add(db.rst.getString(2));
                    temp.add(db.rst.getString(3));
                    temp.add(db.rst.getString(4));
                    temp.add(db.rst.getString(5));
                    temp.add(db.rst.getString(6));
                    temp.add(db.rst.getString(7));
                    temp.add(db.rst.getString(8));
                    temp.add(db.rst.getString(9));
                    data.add(temp);

                    jTable8.setModel(new DefaultTableModel(data, header));
                    jTextField58.setEditable(true);
                    jTable8.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Customer Number Doesn't Exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    } 
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton9 = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        jButton10 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jButton11 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton12 = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JToolBar.Separator();
        jButton13 = new javax.swing.JButton();
        jSeparator19 = new javax.swing.JToolBar.Separator();
        jButton14 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButton15 = new javax.swing.JButton();
        jSeparator22 = new javax.swing.JToolBar.Separator();
        jButton16 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        AddEmployee = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        SearchUpdateDeleteEmployee = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton22 = new javax.swing.JButton();
        ViewEmployeeList = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        RegisterMedicine = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        SearchUpdateDeleteMedicine = new javax.swing.JInternalFrame();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        AddStock = new javax.swing.JInternalFrame();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        UpdateStock = new javax.swing.JInternalFrame();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        GenrateInvoice = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField38 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        Invoice = new javax.swing.JInternalFrame();
        jPanel12 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        ViewRegisterMedicine = new javax.swing.JInternalFrame();
        jPanel14 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        ViewStock = new javax.swing.JInternalFrame();
        jPanel15 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        ReorderList = new javax.swing.JInternalFrame();
        jPanel13 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        ViewAllInvoices = new javax.swing.JInternalFrame();
        jPanel16 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        ViewInvoiceByInvoiceNumber = new javax.swing.JInternalFrame();
        jPanel17 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jTextField57 = new javax.swing.JTextField();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        ViewAllInvoiceByCustomerNumber = new javax.swing.JInternalFrame();
        jPanel18 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel115 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        ChangePassword = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel82 = new javax.swing.JLabel();
        jPasswordField4 = new javax.swing.JPasswordField();
        jLabel83 = new javax.swing.JLabel();
        jPasswordField5 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-male-30.png"))); // NOI18N
        jButton1.setToolTipText("Add Employee");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator10);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-30.png"))); // NOI18N
        jButton2.setToolTipText("Serach/Update/Delete Employee");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator11);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-list-view-30.png"))); // NOI18N
        jButton3.setToolTipText("View Employee List");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator4);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-cash-register-30.png"))); // NOI18N
        jButton4.setToolTipText("Register Medicine");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator12);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-30 (1).png"))); // NOI18N
        jButton5.setToolTipText("Search/Update/Delete Medicine");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator13);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        jButton6.setToolTipText("Add Stock");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);
        jToolBar1.add(jSeparator14);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-30 (1).png"))); // NOI18N
        jButton7.setToolTipText("Update Stock");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator5);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-proforma-invoice-30.png"))); // NOI18N
        jButton8.setToolTipText("Generate Invoice");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);
        jToolBar1.add(jSeparator6);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-view-30.png"))); // NOI18N
        jButton9.setToolTipText("View Register Medicine");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);
        jToolBar1.add(jSeparator15);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-30.png"))); // NOI18N
        jButton10.setToolTipText("View Stock");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);
        jToolBar1.add(jSeparator16);

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-music-record-30.png"))); // NOI18N
        jButton11.setToolTipText("View Reorded List");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);
        jToolBar1.add(jSeparator7);

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-view-all-30.png"))); // NOI18N
        jButton12.setToolTipText("View All Invoice");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);
        jToolBar1.add(jSeparator18);

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-invoice-30.png"))); // NOI18N
        jButton13.setToolTipText("View Invoice by Customer Number");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);
        jToolBar1.add(jSeparator19);

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-invoice-28.png"))); // NOI18N
        jButton14.setToolTipText("View Invoice by Number");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton14);
        jToolBar1.add(jSeparator9);

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-show-password-28.png"))); // NOI18N
        jButton15.setToolTipText("Change Password");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);
        jToolBar1.add(jSeparator22);

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-exit-28.png"))); // NOI18N
        jButton16.setToolTipText("Log Out");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton16.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton16.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton16);

        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddEmployee.setClosable(true);
        AddEmployee.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddEmployee.setTitle("Add Employee");
        AddEmployee.setPreferredSize(new java.awt.Dimension(1080, 540));
        AddEmployee.setVisible(false);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel2.setText("Add New Employee");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Employee Code");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Employee Name");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Post of Employee");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Date of Birth");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Qualification");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Gender");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Address");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Contact");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("E Mail");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Password");

        jTextField1.setBackground(new java.awt.Color(51, 153, 255));
        jTextField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField2.setBackground(new java.awt.Color(51, 153, 255));
        jTextField2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField3.setBackground(new java.awt.Color(51, 153, 255));
        jTextField3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField4.setBackground(new java.awt.Color(51, 153, 255));
        jTextField4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField5.setBackground(new java.awt.Color(51, 153, 255));
        jTextField5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jRadioButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton1.setText("Male");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton2.setText("Female");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTextField6.setBackground(new java.awt.Color(51, 153, 255));
        jTextField6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.setBackground(new java.awt.Color(51, 153, 255));
        jTextField7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jTextField8.setBackground(new java.awt.Color(51, 153, 255));
        jTextField8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });

        jPasswordField1.setBackground(new java.awt.Color(51, 153, 255));
        jPasswordField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPasswordField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton17.setText("Submit");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton18.setText("Reset");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))))
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jTextField8)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(51, 51, 51)
                        .addComponent(jRadioButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addGap(51, 51, 51))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton1, jRadioButton2});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPasswordField1, jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7, jTextField8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton17, jButton18});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jButton17)
                                .addGap(101, 101, 101)
                                .addComponent(jButton18)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(33, 33, 33))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButton1, jRadioButton2});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField1, jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7, jTextField8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton17, jButton18});

        javax.swing.GroupLayout AddEmployeeLayout = new javax.swing.GroupLayout(AddEmployee.getContentPane());
        AddEmployee.getContentPane().setLayout(AddEmployeeLayout);
        AddEmployeeLayout.setHorizontalGroup(
            AddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddEmployeeLayout.setVerticalGroup(
            AddEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.add(AddEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        SearchUpdateDeleteEmployee.setClosable(true);
        SearchUpdateDeleteEmployee.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        SearchUpdateDeleteEmployee.setTitle("SearchUpdateDeleteEmployee");
        SearchUpdateDeleteEmployee.setPreferredSize(new java.awt.Dimension(1080, 540));
        SearchUpdateDeleteEmployee.setVisible(false);

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 30)); // NOI18N
        jLabel13.setText("Search Update Delete Employee");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel14.setText("Employee Code");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel15.setText("Employee Name");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel16.setText("Post of Employee");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel17.setText("Date of Birth");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setText("Qualification");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setText("Gender");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel20.setText("Address");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel21.setText("Contact");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel22.setText("E Mail");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel23.setText("Password");

        jTextField9.setBackground(new java.awt.Color(102, 204, 255));
        jTextField9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField10.setBackground(new java.awt.Color(102, 204, 255));
        jTextField10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField11.setBackground(new java.awt.Color(102, 204, 255));
        jTextField11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField12.setBackground(new java.awt.Color(102, 204, 255));
        jTextField12.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField13.setBackground(new java.awt.Color(102, 204, 255));
        jTextField13.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jRadioButton3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jRadioButton3.setText("Mail");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jRadioButton4.setText("Female");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jTextField14.setBackground(new java.awt.Color(102, 204, 255));
        jTextField14.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField15.setBackground(new java.awt.Color(102, 204, 255));
        jTextField15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jTextField16.setBackground(new java.awt.Color(102, 204, 255));
        jTextField16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextField16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jPasswordField2.setBackground(new java.awt.Color(102, 204, 255));
        jPasswordField2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jPasswordField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton19.setText("Search");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton20.setText("Update");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton21.setText("Delete");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton22.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButton22.setText("Reset");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jRadioButton3)
                                .addGap(71, 71, 71)
                                .addComponent(jRadioButton4)))
                        .addGap(89, 89, 89)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20)
                    .addComponent(jButton22))
                .addGap(22, 22, 22)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPasswordField2, jTextField10, jTextField11, jTextField12, jTextField13, jTextField14, jTextField15, jTextField16, jTextField9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton3, jRadioButton4});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton19, jButton20, jButton21, jButton22});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addGap(113, 113, 113)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton22))
                .addGap(145, 145, 145))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jRadioButton3)
                                .addComponent(jRadioButton4))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField2, jTextField10, jTextField11, jTextField12, jTextField13, jTextField14, jTextField15, jTextField16, jTextField9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButton3, jRadioButton4});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton19, jButton20, jButton21, jButton22});

        javax.swing.GroupLayout SearchUpdateDeleteEmployeeLayout = new javax.swing.GroupLayout(SearchUpdateDeleteEmployee.getContentPane());
        SearchUpdateDeleteEmployee.getContentPane().setLayout(SearchUpdateDeleteEmployeeLayout);
        SearchUpdateDeleteEmployeeLayout.setHorizontalGroup(
            SearchUpdateDeleteEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SearchUpdateDeleteEmployeeLayout.setVerticalGroup(
            SearchUpdateDeleteEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.add(SearchUpdateDeleteEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewEmployeeList.setClosable(true);
        ViewEmployeeList.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewEmployeeList.setTitle("ViewEmployeeList");
        ViewEmployeeList.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewEmployeeList.setVisible(false);

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel29.setText("View Employee List");

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(380, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(410, 410, 410))
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ViewEmployeeListLayout = new javax.swing.GroupLayout(ViewEmployeeList.getContentPane());
        ViewEmployeeList.getContentPane().setLayout(ViewEmployeeListLayout);
        ViewEmployeeListLayout.setHorizontalGroup(
            ViewEmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ViewEmployeeListLayout.setVerticalGroup(
            ViewEmployeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewEmployeeListLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jDesktopPane1.add(ViewEmployeeList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        RegisterMedicine.setClosable(true);
        RegisterMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        RegisterMedicine.setTitle("RegisterMedicine");
        RegisterMedicine.setToolTipText("");
        RegisterMedicine.setPreferredSize(new java.awt.Dimension(1080, 540));
        RegisterMedicine.setVisible(false);
        RegisterMedicine.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Register Medicine");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));
        jPanel6.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 270, -1));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel32.setText("Medicine Name");
        jPanel6.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, 20));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel33.setText("Medicine Company");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, 20));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel34.setText("Medicine Formula");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, 20));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel35.setText("Medicine Suplier");
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, 20));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel36.setText("Copmany Contact");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, 20));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel37.setText("Suplier Contact");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, 20));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel38.setText("Medicine Purpose");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, 20));
        jPanel6.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 410, -1));

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 410, -1));
        jPanel6.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 410, -1));
        jPanel6.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 410, -1));
        jPanel6.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 410, -1));
        jPanel6.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 410, -1));
        jPanel6.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 410, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (20).png"))); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (15).png"))); // NOI18N
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 170, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sublime Vivid.jpg"))); // NOI18N
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        RegisterMedicine.getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jDesktopPane1.add(RegisterMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        SearchUpdateDeleteMedicine.setClosable(true);
        SearchUpdateDeleteMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        SearchUpdateDeleteMedicine.setTitle("Search Update Delete Medicine");
        SearchUpdateDeleteMedicine.setPreferredSize(new java.awt.Dimension(1080, 540));
        SearchUpdateDeleteMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel42.setText("Search Update Delete Medicine");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel43.setText("Medicine Name");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel44.setText("Medicibe Company");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel45.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel45.setText("Medicine Formula");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel46.setText("Medicine Suplier");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel47.setText("Company Contact");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel48.setText("Suplier Contact");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel49.setText("Medicine Purpose");
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 400, -1));
        SearchUpdateDeleteMedicine.getContentPane().add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 400, -1));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (19).png"))); // NOI18N
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, -1));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (17).png"))); // NOI18N
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, -1, -1));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (25).png"))); // NOI18N
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, -1));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (15).png"))); // NOI18N
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sublime Vivid.jpg"))); // NOI18N
        SearchUpdateDeleteMedicine.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(SearchUpdateDeleteMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        AddStock.setClosable(true);
        AddStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        AddStock.setTitle("Add Stock");
        AddStock.setPreferredSize(new java.awt.Dimension(1080, 540));
        AddStock.setVisible(false);
        AddStock.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel54.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel54.setText("Add Stock");
        AddStock.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel56.setText("Medicine Name");
        AddStock.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel57.setText("Cost Price");
        AddStock.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel58.setText("Selling Price");
        AddStock.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel59.setText("Manufacturing Date");
        AddStock.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel60.setText("Expiry Date");
        AddStock.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, -1, -1));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel61.setText("Reorder Level");
        AddStock.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, -1));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel62.setText("Quantity");
        AddStock.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 460, -1));

        jTextField32.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 460, -1));

        jTextField33.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 460, -1));

        jTextField34.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 460, -1));

        jTextField35.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 460, -1));

        jTextField36.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 460, -1));

        jTextField37.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        AddStock.getContentPane().add(jTextField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 460, -1));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (20).png"))); // NOI18N
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel63MouseClicked(evt);
            }
        });
        AddStock.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, -1, -1));

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (15).png"))); // NOI18N
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });
        AddStock.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, -1, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sublime Vivid.jpg"))); // NOI18N
        AddStock.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1074, 510));

        jDesktopPane1.add(AddStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        UpdateStock.setClosable(true);
        UpdateStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        UpdateStock.setTitle("Update Stock");
        UpdateStock.setPreferredSize(new java.awt.Dimension(1080, 540));
        UpdateStock.setVisible(false);
        UpdateStock.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel66.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel66.setText("Update Stock");
        UpdateStock.getContentPane().add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel67.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel67.setText("Medicine Name");
        UpdateStock.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        jLabel68.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel68.setText("Cost Price");
        UpdateStock.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel69.setText("Selling Price");
        UpdateStock.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jLabel70.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel70.setText("Manufacturing Date");
        UpdateStock.getContentPane().add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel71.setText("Expiry Date");
        UpdateStock.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jLabel72.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel72.setText("Reorder Level");
        UpdateStock.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel73.setText("Avl. Quantity");
        UpdateStock.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        jLabel74.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel74.setText("Net Quantity");
        UpdateStock.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (17).png"))); // NOI18N
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });
        UpdateStock.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, -1, -1));

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        UpdateStock.getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 430, -1));
        UpdateStock.getContentPane().add(jTextField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 430, -1));
        UpdateStock.getContentPane().add(jTextField40, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 430, -1));
        UpdateStock.getContentPane().add(jTextField41, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 430, -1));
        UpdateStock.getContentPane().add(jTextField42, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 430, -1));

        jTextField43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField43ActionPerformed(evt);
            }
        });
        UpdateStock.getContentPane().add(jTextField43, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 430, -1));
        UpdateStock.getContentPane().add(jTextField44, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 430, -1));
        UpdateStock.getContentPane().add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 430, -1));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (15).png"))); // NOI18N
        jLabel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel76MouseClicked(evt);
            }
        });
        UpdateStock.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, -1, -1));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sublime Vivid.jpg"))); // NOI18N
        UpdateStock.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(UpdateStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        GenrateInvoice.setClosable(true);
        GenrateInvoice.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        GenrateInvoice.setTitle("Genrate Invoice ");
        GenrateInvoice.setPreferredSize(new java.awt.Dimension(1080, 540));
        GenrateInvoice.setVisible(false);

        jPanel11.setBackground(new java.awt.Color(255, 102, 255));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel84.setText("Genrate Invoice");

        jLabel85.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel85.setText("Medicine Name");

        jLabel86.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel86.setText("Price");

        jLabel87.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel87.setText("Manufacture Date");

        jLabel88.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel88.setText("Expiry Date");

        jLabel89.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel89.setText("Avalable Quantity");

        jLabel90.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel90.setText("Quantity");

        jComboBox3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jTextField38.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField45.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });

        jTextField46.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField47.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField48.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel91.setFont(new java.awt.Font("Arial", 0, 21)); // NOI18N
        jLabel91.setText("Customer Detail");

        jLabel92.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel92.setText("Customer Name");

        jLabel93.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel93.setText("Mobile Number");

        jTextField49.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField50.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButton25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton25.setText("Add to Cart");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton26.setText("Genrate Bill");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jLabel84))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89)
                            .addComponent(jLabel90)
                            .addComponent(jLabel85))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField38)
                            .addComponent(jTextField45)
                            .addComponent(jTextField46)
                            .addComponent(jTextField47)
                            .addComponent(jTextField48)
                            .addComponent(jComboBox3, 0, 318, Short.MAX_VALUE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jLabel92)
                            .addComponent(jLabel93))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jButton25)
                            .addGap(27, 27, 27)
                            .addComponent(jButton26)
                            .addGap(71, 71, 71))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addGap(38, 38, 38)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91))
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93)
                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25)
                    .addComponent(jButton26))
                .addGap(46, 46, 46)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout GenrateInvoiceLayout = new javax.swing.GroupLayout(GenrateInvoice.getContentPane());
        GenrateInvoice.getContentPane().setLayout(GenrateInvoiceLayout);
        GenrateInvoiceLayout.setHorizontalGroup(
            GenrateInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GenrateInvoiceLayout.setVerticalGroup(
            GenrateInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.add(GenrateInvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 58, 1080, 540));

        Invoice.setClosable(true);
        Invoice.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Invoice.setTitle("Invoice");
        Invoice.setPreferredSize(new java.awt.Dimension(1080, 540));
        Invoice.setVisible(false);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(1080, 510));

        jLabel94.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel94.setText("Invoice");

        jLabel95.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel95.setText("Anurag Pharmacy");

        jLabel96.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel96.setText("Jaunpur (U.P.), 8923743284");

        jLabel97.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel97.setText("Invoice Number");

        jLabel98.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel98.setText("Customer Name");

        jLabel99.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel99.setText("Invoice Date");

        jLabel100.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel100.setText("Contact Number");

        jTextField51.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextField51.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField52.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextField52.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField53.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextField53.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField54.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextField54.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel101.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel101.setText("Paybale Amount ");

        jLabel102.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel102.setText("Due Amount");

        jLabel103.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel104.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButton27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton27.setText("Print");

        jTextField55.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField55.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextField56.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField56.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel94)
                        .addGap(511, 511, 511))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel97)
                                        .addComponent(jLabel98))
                                    .addGap(33, 33, 33)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField52, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                        .addComponent(jTextField51))
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel99)
                                        .addComponent(jLabel100))
                                    .addGap(32, 32, 32)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField53, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                        .addComponent(jTextField54)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel96)
                                .addComponent(jLabel95)
                                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(35, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel102)
                                .addGap(55, 55, 55)))
                        .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton27)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel94)
                        .addComponent(jLabel95)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(jLabel99)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(jLabel100)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(jLabel102)
                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout InvoiceLayout = new javax.swing.GroupLayout(Invoice.getContentPane());
        Invoice.getContentPane().setLayout(InvoiceLayout);
        InvoiceLayout.setHorizontalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
        InvoiceLayout.setVerticalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.add(Invoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewRegisterMedicine.setClosable(true);
        ViewRegisterMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewRegisterMedicine.setTitle("Register Medicines");
        ViewRegisterMedicine.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewRegisterMedicine.setVisible(false);
        ViewRegisterMedicine.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(153, 153, 255));

        jLabel107.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel107.setText("View Register Medicine");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ViewRegisterMedicine.getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        jDesktopPane1.add(ViewRegisterMedicine, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewStock.setClosable(true);
        ViewStock.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewStock.setTitle("View Stock");
        ViewStock.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewStock.setVisible(false);
        ViewStock.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(255, 204, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel108.setText("View Stock");
        jPanel15.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable5);

        jPanel15.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1060, 430));

        ViewStock.getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ViewStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ReorderList.setClosable(true);
        ReorderList.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ReorderList.setTitle("Reorder List");
        ReorderList.setPreferredSize(new java.awt.Dimension(1080, 540));
        ReorderList.setVisible(false);
        ReorderList.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(204, 255, 255));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel106.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel106.setText("Reorder List");
        jPanel13.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 180, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jPanel13.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1060, 430));

        ReorderList.getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ReorderList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewAllInvoices.setClosable(true);
        ViewAllInvoices.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewAllInvoices.setResizable(true);
        ViewAllInvoices.setTitle("View All Invoices");
        ViewAllInvoices.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewAllInvoices.setVisible(false);
        ViewAllInvoices.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(0, 204, 204));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel109.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel109.setText("View All Invoices");
        jPanel16.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable6);

        jPanel16.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1070, 420));

        ViewAllInvoices.getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ViewAllInvoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewInvoiceByInvoiceNumber.setClosable(true);
        ViewInvoiceByInvoiceNumber.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewInvoiceByInvoiceNumber.setTitle("View Invoice By Invoice Number");
        ViewInvoiceByInvoiceNumber.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewInvoiceByInvoiceNumber.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(255, 102, 204));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel110.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("View Invoice By Invoice Number ");
        jPanel17.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel111.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel111.setText("Enter Invoice Number");
        jPanel17.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jTextField57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField57ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField57, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 290, 30));
        jPanel17.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 460, 10));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (18).png"))); // NOI18N
        jLabel112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel112MouseClicked(evt);
            }
        });
        jPanel17.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 170, 60));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable7);

        jPanel17.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1070, 340));

        ViewInvoiceByInvoiceNumber.getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ViewInvoiceByInvoiceNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ViewAllInvoiceByCustomerNumber.setClosable(true);
        ViewAllInvoiceByCustomerNumber.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ViewAllInvoiceByCustomerNumber.setTitle("View Invoice By Customer Number");
        ViewAllInvoiceByCustomerNumber.setPreferredSize(new java.awt.Dimension(1080, 540));
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(51, 0, 204));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("View Invoice By Customer Number ");
        jPanel18.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel114.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel114.setText("Enter Customer Number");
        jPanel18.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jTextField58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField58ActionPerformed(evt);
            }
        });
        jPanel18.add(jTextField58, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 290, 30));
        jPanel18.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 500, 10));

        jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/button (18).png"))); // NOI18N
        jLabel115.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel115MouseClicked(evt);
            }
        });
        jPanel18.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 170, 60));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable8);

        jPanel18.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1070, 340));

        ViewAllInvoiceByCustomerNumber.getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ViewAllInvoiceByCustomerNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        ChangePassword.setClosable(true);
        ChangePassword.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        ChangePassword.setTitle("Change Password");
        ChangePassword.setPreferredSize(new java.awt.Dimension(1080, 540));
        ChangePassword.setVisible(false);
        ChangePassword.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Arial", 0, 33)); // NOI18N
        jLabel77.setText("Change Password");
        jPanel7.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));
        jPanel7.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 260, 10));

        jLabel78.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel78.setText("Old Password");
        jPanel7.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel79.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel79.setText("New Password");
        jPanel7.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel80.setText("Conform Passwword");
        jPanel7.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jButton23.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton23.setText("Change");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, -1, 30));

        jButton24.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton24.setText("Clear");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 90, 30));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-28.png"))); // NOI18N
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel81MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel81MouseExited(evt);
            }
        });
        jPanel7.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, -1, -1));
        jPanel7.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 510, 30));

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-28.png"))); // NOI18N
        jLabel82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel82MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel82MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel82MouseExited(evt);
            }
        });
        jPanel7.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, -1, -1));
        jPanel7.add(jPasswordField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 510, 30));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-28.png"))); // NOI18N
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel83MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel83MouseExited(evt);
            }
        });
        jPanel7.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, -1, -1));
        jPanel7.add(jPasswordField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 510, 30));

        ChangePassword.getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 510));

        jDesktopPane1.add(ChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 1080, 540));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SL-120219-25650-35.jpg"))); // NOI18N
        jDesktopPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1160, 580));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204), 2));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Welcome,");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 255));
        jLabel25.setText("Name");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("How are You ?");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-alarm-clock-28.png"))); // NOI18N

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        jLabel105.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 670, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204), 2));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Employee");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-male-30.png"))); // NOI18N
        jMenuItem1.setText("Add Employee");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-30.png"))); // NOI18N
        jMenuItem2.setText("Search/Update/Delete Employee");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-list-view-30_1.png"))); // NOI18N
        jMenuItem3.setText("View Employee List");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Medicine");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-cash-register-30.png"))); // NOI18N
        jMenuItem4.setText("Register Medicine");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-30 (1).png"))); // NOI18N
        jMenuItem5.setText("Search/Update/Delete Medicine");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        jMenuItem6.setText("Add Stock");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-30 (1).png"))); // NOI18N
        jMenuItem7.setText("Update Stock");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Billing");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-proforma-invoice-30.png"))); // NOI18N
        jMenuItem8.setText("Generate Invoice");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Medicines Report");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-view-30.png"))); // NOI18N
        jMenuItem9.setText("View Register Medicine");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-30.png"))); // NOI18N
        jMenuItem10.setText("View Stock");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-music-record-30.png"))); // NOI18N
        jMenuItem11.setText("View Reorded List");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Invoice Report");

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-view-all-30.png"))); // NOI18N
        jMenuItem12.setText("View All Invoice");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-invoice-30.png"))); // NOI18N
        jMenuItem13.setText("View Invoice by Customer Number");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-invoice-28.png"))); // NOI18N
        jMenuItem14.setText("View Invoice by Number");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Account");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-show-password-28.png"))); // NOI18N
        jMenuItem15.setText("Change Password");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-exit-28.png"))); // NOI18N
        jMenuItem16.setText("Log Out");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem16);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            Vector<String> header = new Vector<String>();
            Vector<Vector<String>> data = new Vector<Vector<String>>();
            header.add("Employee Code");
            header.add("Employee Name");
            header.add("Employee Post");
            header.add("Date of Birth");
            header.add("Qualification");
            header.add("Gender");
            header.add("Address");
            header.add("Contact");
            header.add("E Mail");
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from emp_mstr");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                temp.add(db.rst.getString(8));
                temp.add(db.rst.getString(9));
                data.add(temp);
            }
            jTable1.setModel(new DefaultTableModel(data, header));
            
            
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(true);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
             
            

        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed

showViewStock(); 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed

showViewReorderList();
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 


// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AddEmployee.setVisible(true);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton2.setSelected(false);

// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jRadioButton1.setSelected(false);

// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            if(jTextField1.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Code"); 
             jTextField1.requestFocus();
            }
            else if(jTextField2.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Name"); 
             jTextField2.requestFocus();
            }
            else if(jTextField3.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Post"); 
             jTextField3.requestFocus();
            }
            else if(jTextField4.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Date of Birth"); 
             jTextField4.requestFocus();
            }
            else if(jTextField5.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Qualification"); 
             jTextField5.requestFocus();
            }
            else if(jTextField6.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Address"); 
             jTextField6.requestFocus();
            }
            else if(jTextField7.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Contact Number"); 
             jTextField7.requestFocus();
            }
            else if(jTextField8.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Email"); 
             jTextField8.requestFocus();
            }
            else if(jPasswordField1.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Password"); 
             jPasswordField1.requestFocus();
            }
            else
            {
            
            String code = jTextField1.getText();
            String name = jTextField2.getText();
            String post = jTextField3.getText();
            String dob = jTextField4.getText();
            String qua = jTextField5.getText();
            String gen = "";
            if (jRadioButton1.isSelected()) {
                gen = "Male";
            }
            if (jRadioButton2.isSelected()) {
                gen = "Female";
            }
            String addr = jTextField6.getText();
            String contact = jTextField7.getText();
            String user = jTextField8.getText();
            String pswd = jPasswordField1.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("insert into emp_mstr(ecode,ename,epost,edob,equa,egender,eaddr,econtact,email,password)value(?,?,?,?,?,?,?,?,?,?)");
            db.pstmt.setString(1, code);
            db.pstmt.setString(2, name);
            db.pstmt.setString(3, post);
            db.pstmt.setString(4, dob);
            db.pstmt.setString(5, qua);
            db.pstmt.setString(6, gen);
            db.pstmt.setString(7, addr);
            db.pstmt.setString(8, contact);
            db.pstmt.setString(9, user);
            db.pstmt.setString(10, pswd);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Employee Added Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Employee Registration");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jPasswordField1.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(true);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        jRadioButton3.setSelected(false);

// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            if(jTextField9.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Code"); 
             jTextField9.requestFocus();
            }
            else
            {
            
            String code = jTextField9.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from emp_mstr where ecode=?");
            db.pstmt.setString(1, code);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jTextField10.setText(db.rst.getString(2));
                jTextField11.setText(db.rst.getString(3));
                jTextField12.setText(db.rst.getString(4));
                jTextField13.setText(db.rst.getString(5));
                if (db.rst.getString(6).equals("Male")) {
                    jRadioButton3.setSelected(true);
                } else {
                    jRadioButton4.setSelected(true);
                }
                jTextField14.setText(db.rst.getString(7));
                jTextField15.setText(db.rst.getString(8));
                jTextField16.setText(db.rst.getString(9));
                jPasswordField2.setText(db.rst.getString(10));
            } else {
                JOptionPane.showMessageDialog(this, "Error!!! Invalid Employee Code");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddEmployee.setVisible(true);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
        

// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jPasswordField2.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jRadioButton4.setSelected(false);

// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
            if(jTextField9.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Code"); 
             jTextField9.requestFocus();
            }
            else if(jTextField10.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Name"); 
             jTextField10.requestFocus();
            }
            else if(jTextField11.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Post"); 
             jTextField11.requestFocus();
            }
            else if(jTextField12.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Date of Birth"); 
             jTextField12.requestFocus();
            }
            else if(jTextField13.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Qualification"); 
             jTextField13.requestFocus();
            }
            else if(jTextField14.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Employee Address"); 
             jTextField14.requestFocus();
            }
            else if(jTextField15.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Contact"); 
             jTextField15.requestFocus();
            }
            else if(jTextField16.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Email"); 
             jTextField16.requestFocus();
            }
            else if(jPasswordField2.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Password"); 
             jPasswordField2.requestFocus();
            }
            else
            {
            
            
            String code = jTextField9.getText();
            String name = jTextField10.getText();
            String post = jTextField11.getText();
            String dob = jTextField12.getText();
            String qua = jTextField13.getText();
            String gen = "";
            if (jRadioButton3.isSelected()) {
                gen = "Male";
            }
            if (jRadioButton4.isSelected()) {
                gen = "Female";
            }
            String addr = jTextField14.getText();
            String contact = jTextField15.getText();
            String user = jTextField16.getText();
            String pswd = jPasswordField2.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("update emp_mstr set ename=?,epost=?,edob=?,equa=?,egender=?,eaddr=?,econtact=?,email=?,password=? where ecode=?");
            db.pstmt.setString(1, name);
            db.pstmt.setString(2, post);
            db.pstmt.setString(3, dob);
            db.pstmt.setString(4, qua);
            db.pstmt.setString(5, gen);
            db.pstmt.setString(6, addr);
            db.pstmt.setString(7, contact);
            db.pstmt.setString(8, user);
            db.pstmt.setString(9, pswd);
            db.pstmt.setString(10, code);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Employee Update Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Employee Updation");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        try {
            String code = jTextField9.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("delete from emp_mstr where ecode=?");
            db.pstmt.setString(1, code);
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to delete Current Employee Record");
            if (a == 0) {
                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
                    jTextField9.setText("");
                    jTextField10.setText("");
                    jTextField11.setText("");
                    jTextField12.setText("");
                    jTextField13.setText("");
                    jRadioButton3.setSelected(false);
                    jRadioButton4.setSelected(false);
                    jTextField14.setText("");
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jPasswordField2.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Deletion");
                }
            } else if (a == 1) {
                SearchUpdateDeleteEmployee.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            Vector<String> header = new Vector<String>();
            Vector<Vector<String>> data = new Vector<Vector<String>>();
            header.add("Employee Code");
            header.add("Employee Name");
            header.add("Employee Post");
            header.add("Date of Birth");
            header.add("Qualification");
            header.add("Gender");
            header.add("Address");
            header.add("Contact");
            header.add("E Mail");
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from emp_mstr");
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                temp.add(db.rst.getString(6));
                temp.add(db.rst.getString(7));
                temp.add(db.rst.getString(8));
                temp.add(db.rst.getString(9));
                data.add(temp);
            }
            jTable1.setModel(new DefaultTableModel(data, header));
            AddEmployee.setVisible(false);
            SearchUpdateDeleteEmployee.setVisible(false);
            ViewEmployeeList.setVisible(true);
            RegisterMedicine.setVisible(false);
            SearchUpdateDeleteMedicine.setVisible(false);
            AddStock.setVisible(false);
            UpdateStock.setVisible(false);
            GenrateInvoice.setVisible(false);
            ViewRegisterMedicine.setVisible(false);
            ViewStock.setVisible(false);
            ReorderList.setVisible(false);
            ViewAllInvoices.setVisible(false);
            ViewInvoiceByInvoiceNumber.setVisible(false);
            ViewAllInvoiceByCustomerNumber.setVisible(false);
            ChangePassword.setVisible(false);
 
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(true);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        try {
            if(jTextField17.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Name"); 
             jTextField17.requestFocus();
            }
            else if(jTextField18.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Company"); 
             jTextField18.requestFocus();
            }
            else if(jTextField19.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Formula"); 
             jTextField19.requestFocus();
            }
            else if(jTextField20.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Suplier"); 
             jTextField20.requestFocus();
            }
            else if(jTextField21.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Company Contact"); 
             jTextField21.requestFocus();
            }
            else if(jTextField22.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Suplier Contact"); 
             jTextField22.requestFocus();
            }
            else if(jTextField23.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Purpose"); 
             jTextField23.requestFocus();
            }
            else
            {
            
            String name = jTextField17.getText();
            String company = jTextField18.getText();
            String formula = jTextField19.getText();
            String suplier = jTextField20.getText();
            String contact = jTextField21.getText();
            String cont = jTextField22.getText();
            String purpose = jTextField23.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("insert into product_mstr(med_name,med_company,med_formula,med_sup,med_contact,med_cont,med_purpose)value(?,?,?,?,?,?,?)");
            db.pstmt.setString(1, name);
            db.pstmt.setString(2, company);
            db.pstmt.setString(3, formula);
            db.pstmt.setString(4, suplier);
            db.pstmt.setString(5, contact);
            db.pstmt.setString(6, cont);
            db.pstmt.setString(7, purpose);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Medicine Registered Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error!!! in Registration");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(true);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(true);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 
        


// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        try {
            if(jTextField24.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Name"); 
             jTextField24.requestFocus();
            }
            else
            {
            String code = jTextField24.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from product_mstr where med_name=?");
            db.pstmt.setString(1, code);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jTextField25.setText(db.rst.getString(2));
                jTextField26.setText(db.rst.getString(3));
                jTextField27.setText(db.rst.getString(4));
                jTextField28.setText(db.rst.getString(5));
                jTextField29.setText(db.rst.getString(6));
                jTextField30.setText(db.rst.getString(7));
                jTextField24.setEditable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Medicine name does not match");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(true);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(true);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        try {
            if(jTextField24.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Name"); 
             jTextField24.requestFocus();
            }
            else if(jTextField25.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Company"); 
             jTextField25.requestFocus();
            }
            else if(jTextField26.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Formula"); 
             jTextField26.requestFocus();
            }
            else if(jTextField27.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Suplier"); 
             jTextField27.requestFocus();
            }
            else if(jTextField28.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Company Contact"); 
             jTextField28.requestFocus();
            }
            else if(jTextField29.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Suplier Contact"); 
             jTextField29.requestFocus();
            }
            else if(jTextField30.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Medicine Purpose"); 
             jTextField30.requestFocus();
            }
            else
            {
            
            String name = jTextField24.getText();
            String company = jTextField25.getText();
            String formula = jTextField26.getText();
            String suplier = jTextField27.getText();
            String contact = jTextField28.getText();
            String cont = jTextField29.getText();
            String purpose = jTextField30.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("update product_mstr set med_company=?,med_formula=?,med_sup=?,med_contact=?,med_cont=?,med_purpose=? where med_name=?");
            db.pstmt.setString(1, company);
            db.pstmt.setString(2, formula);
            db.pstmt.setString(3, suplier);
            db.pstmt.setString(4, contact);
            db.pstmt.setString(5, cont);
            db.pstmt.setString(6, purpose);
            db.pstmt.setString(7, name);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Medicine Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error!!! in Medicine Updation");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        try {
            String name = jTextField24.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("delete from product_mstr where med_name=?");
            db.pstmt.setString(1, name);
            int a = JOptionPane.showConfirmDialog(this, "Are you sure to delete Current Medicine Record");
            if (a == 0) {
                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Medicine Deleted Successfully");
                    jTextField24.setText("");
                    jTextField25.setText("");
                    jTextField26.setText("");
                    jTextField27.setText("");
                    jTextField28.setText("");
                    jTextField29.setText("");
                    jTextField30.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error in Deletion");
                }
            } else if (a == 1) {
                SearchUpdateDeleteMedicine.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        jTextField24.setText("");
        jTextField25.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
        jTextField30.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        jComboBox1.setSelectedIndex(0);
        jTextField32.setText("");
        jTextField33.setText("");
        jTextField34.setText("");
        jTextField35.setText("");
        jTextField36.setText("");
        jTextField37.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
        try {
            if(jTextField32.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Cost Price"); 
             jTextField32.requestFocus();
            }
            else if(jTextField33.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Selling Price"); 
             jTextField33.requestFocus();
            }
            else if(jTextField34.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Manufacturing Date"); 
             jTextField34.requestFocus();
            }
            else if(jTextField35.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Expiry Date"); 
             jTextField35.requestFocus();
            }
            else if(jTextField36.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Reorder Level"); 
             jTextField36.requestFocus();
            }
            else if(jTextField37.getText().equals(""))
            {
             JOptionPane.showMessageDialog(this, "Please Enter Quantity"); 
             jTextField37.requestFocus();
            }
            else
            {
            
            String name = ""+jComboBox1.getSelectedItem();
            DBConnection db = new DBConnection();
            int cp = Integer.parseInt(jTextField32.getText());
            int sp = Integer.parseInt(jTextField33.getText());
            String mfd = jTextField34.getText();
            String exd = jTextField35.getText();
            int reorder = Integer.parseInt(jTextField36.getText());
            int quant = Integer.parseInt(jTextField37.getText());
            db.pstmt = db.con.prepareStatement("insert into stock_detail(med_name,med_cp,med_sp,med_mfd,med_exd,reorder_level,med_qty)values(?,?,?,?,?,?,?)");
            db.pstmt.setString(1, name);
            db.pstmt.setInt(2, cp);
            db.pstmt.setInt(3, sp);
            db.pstmt.setString(4, mfd);
            db.pstmt.setString(5, exd);
            db.pstmt.setInt(6, reorder);
            db.pstmt.setInt(7, quant);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Stock Added Successfully");
                jTextField32.setText("");
                jTextField33.setText("");
                jTextField34.setText("");
                jTextField35.setText("");
                jTextField36.setText("");
                jTextField37.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Add Stock");
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Enter all Values in Correct Format:" + "" + e.getMessage());
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel63MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from product_mstr");
            db.rst = db.pstmt.executeQuery();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Choose Medicine Name");
            while (db.rst.next()) {
                jComboBox1.addItem(db.rst.getString(1));
            }
            
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(true);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 
            
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from product_mstr");
            db.rst = db.pstmt.executeQuery();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Choose Medicine Name");
            while (db.rst.next()) {
                jComboBox1.addItem(db.rst.getString(1));
            }
        AddStock.setVisible(true);    
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 
            
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        try {
            
            
            String name = jComboBox2.getSelectedItem().toString();
            String cp = jTextField39.getText();
            String sp = jTextField40.getText();
            String mfd = jTextField41.getText();
            String exd = jTextField42.getText();
            String reorder = jTextField43.getText();
            String avl_qty = jTextField44.getText();
            String net_qty = jTextField31.getText();
            int qty = Integer.parseInt(avl_qty) + Integer.parseInt(net_qty);
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("update stock_detail set med_cp=?,med_sp=?,med_mfd=?,med_exd=?,reorder_level=?,med_qty=? where med_name=?");
            db.pstmt.setString(1, cp);
            db.pstmt.setString(2, sp);
            db.pstmt.setString(3, mfd);
            db.pstmt.setString(4, exd);
            db.pstmt.setString(5, reorder);
            db.pstmt.setInt(6, qty);
            db.pstmt.setString(7, name);
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Stock Update Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Error!!! in Stock Updation");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel75MouseClicked

    private void jLabel76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseClicked

        jComboBox2.setSelectedIndex(0);
        jTextField39.setText("");
        jTextField40.setText("");
        jTextField41.setText("");
        jTextField42.setText("");
        jTextField43.setText("");
        jTextField44.setText("");
        jTextField31.setText("");
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel76MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox2.removeAllItems();

            while (db.rst.next()) {
                jComboBox2.addItem(db.rst.getString(1));
            }
            
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(true);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
        
        jTextField39.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox2.removeAllItems();
            
            while (db.rst.next()) {
                jComboBox2.addItem(db.rst.getString(1));
            }
            
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(true);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
        
        jTextField39.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        try {
            String name = ""+jComboBox2.getSelectedItem();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select * from stock_detail where med_name=?");
            db.pstmt.setString(1, name);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jTextField39.setText(db.rst.getString(2));
                jTextField40.setText(db.rst.getString(3));
                jTextField41.setText(db.rst.getString(4));
                jTextField42.setText(db.rst.getString(5));
                jTextField43.setText(db.rst.getString(6));
                jTextField44.setText(db.rst.getString(7));

            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        jPasswordField3.setText("");
        jPasswordField4.setText("");
        jPasswordField5.setText("");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        ChangePassword.setVisible(true);
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        

// TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       

  int a = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout ?");
        if (a == 0) {
            new LoginWindow().setVisible(true);
            this.setVisible(false);
        } else if (a == 1) {
            this.setVisible(true);
        }
        

// TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        ChangePassword.setVisible(true);
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
       
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        try {
            String old_pswd = jPasswordField3.getText();
            String new_pswd = jPasswordField4.getText();
            String cnf_pswd = jPasswordField5.getText();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select password from admin where user=?");
            db.pstmt.setString(1, user);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                String db_pswd = db.rst.getString(1);
                if (old_pswd.equals(db_pswd)) {
                    if (new_pswd.equals(cnf_pswd)) {
                        db.pstmt = db.con.prepareStatement("update admin set password=? where user=?");
                        db.pstmt.setString(1, new_pswd);
                        db.pstmt.setString(2, user);
                        int i = db.pstmt.executeUpdate();
                        if (i > 0) {
                            JOptionPane.showMessageDialog(this, "Password Updated Successfully");
                            ChangePassword.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(this, "Error in Password Updation");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "New and Confirm Password does not Match");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Old Password Does not Match");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Login First");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Choose Medicine Name");
            while (db.rst.next()) {
                jComboBox3.addItem(db.rst.getString(1));
            }
            
            AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(true);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
         
            db.pstmt = db.con.prepareStatement("Select MAX(invc_no) from bill_detail");
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                invoice = db.rst.getInt(1);   //int invoice is globally declared on 3036 line number
                invoice++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    int invoice;
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_name from stock_detail");
            db.rst = db.pstmt.executeQuery();
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Choose Medicine Name");
            while (db.rst.next()) {
                jComboBox3.addItem(db.rst.getString(1));
            }
            
            AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(true);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
         
            db.pstmt = db.con.prepareStatement("Select MAX(invc_no) from bill_detail");
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                invoice = db.rst.getInt(1);   //int invoice is globally declared on 3036 line number
                invoice++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       

// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        try {
            String name = jComboBox3.getSelectedItem().toString();
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_sp,med_mfd,med_exd,med_qty from stock_detail where med_name=?");
            db.pstmt.setString(1, name);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jTextField38.setText(db.rst.getString(1));
                jTextField45.setText(db.rst.getString(2));
                jTextField46.setText(db.rst.getString(3));
                jTextField47.setText(db.rst.getString(4));
                jTextField38.setEditable(false);
                jTextField45.setEditable(false);
                jTextField46.setEditable(false);
                jTextField47.setEditable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        try {
            int amt = 0, price, qty;
            DBConnection db = new DBConnection();
            db.pstmt = db.con.prepareStatement("select med_price,med_qty from invoice where invoice_id=?");
            db.pstmt.setInt(1, invoice);
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                price = db.rst.getInt(1);
                qty = db.rst.getInt(2);
                amt += (price * qty);
            }
            String custName = jTextField49.getText();
            String custMobile = jTextField50.getText();
            db.pstmt = db.con.prepareStatement("insert into bill_detail(invc_no,date_time,cust_name,cust_cont,bill_amt) values(?,?,?,?,?)");
            db.pstmt.setInt(1, invoice);
            db.pstmt.setString(2, new Date().toString());
            db.pstmt.setString(3, custName);
            db.pstmt.setString(4, custMobile);
            db.pstmt.setInt(5, amt);
            int i1 = db.pstmt.executeUpdate();
            Vector<String> header = new Vector<String>();
            Vector<Vector<String>> data = new Vector<Vector<String>>();
            header.add("Medicine Name");
            header.add("Mfd.Date");
            header.add("Exp. Date");
            header.add("Price");
            header.add("Quantity");
            header.add("Amount");
            db.pstmt = db.con.prepareStatement("select invoice.med_name,stock_detail.med_mfd,stock_detail.med_exd,invoice.med_price,invoice.med_qty from invoice,stock_detail where invoice.med_name=stock_detail.med_name AND invoice.invoice_id=?");
            db.pstmt.setInt(1, invoice);
            db.rst = db.pstmt.executeQuery();
            while (db.rst.next()) {
                Vector<String> temp = new Vector<String>();
                temp.add(db.rst.getString(1));
                temp.add(db.rst.getString(2));
                temp.add(db.rst.getString(3));
                temp.add(db.rst.getString(4));
                temp.add(db.rst.getString(5));
                int amt1 = Integer.parseInt(db.rst.getString(4)) * Integer.parseInt(db.rst.getString(5));
                temp.add("" + amt1);
                data.add(temp);
            }
            jTable2.setModel(new DefaultTableModel(data, header));
            db.pstmt = db.con.prepareStatement("select date_time,cust_name,cust_cont,bill_amt from bill_detail where invc_no=?");
            db.pstmt.setInt(1, invoice);
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                jTextField51.setText("" + invoice);
                jTextField53.setText(db.rst.getString(1));
                jTextField52.setText(db.rst.getString(2));
                jTextField54.setText(db.rst.getString(3));
                jTextField55.setText(db.rst.getString(4));
            }
            GenrateInvoice.setVisible(false);
            Invoice.setVisible(true);
            jTextField51.setEditable(false);
            jTextField52.setEditable(false);
            jTextField53.setEditable(false);
            jTextField54.setEditable(false);
            jTextField55.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

       
// TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        int avl_qty = Integer.parseInt(jTextField47.getText());
        int qty = Integer.parseInt(jTextField48.getText());
        if (avl_qty >= qty) {
            try {
                String name = jComboBox3.getSelectedItem().toString();
                String price = jTextField38.getText();
                String qty1 = jTextField48.getText();
                String custName = jTextField49.getText();
                String custMobile = jTextField50.getText();
                String emp_id = "Admin";
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("insert into invoice values(?,?,?,?,?,?,?,?)");
                db.pstmt.setInt(1, invoice);
                db.pstmt.setString(2, name);
                db.pstmt.setString(3, price);
                db.pstmt.setString(4, qty1);
                db.pstmt.setString(5, new java.util.Date().toString());
                db.pstmt.setString(6, custName);
                db.pstmt.setString(7, custMobile);
                db.pstmt.setString(8, emp_id);
                int i1 = db.pstmt.executeUpdate();
                db.pstmt = db.con.prepareStatement("update stock_detail set med_qty=? where med_name=?");
                db.pstmt.setInt(1, (avl_qty - qty));
                db.pstmt.setString(2, name);
                int i2 = db.pstmt.executeUpdate();
                if (i1 > 0 && i2 > 0) {
                    JOptionPane.showMessageDialog(this, "Added in Cart Successfully");
                    jTextField38.setText("");
                    jTextField45.setText("");
                    jTextField46.setText("");
                    jTextField47.setText("");
                    jTextField48.setText("");
                    jComboBox3.setSelectedIndex(0);
                }
            
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Quantity");
            jTextField48.setText("");
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField45ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

showViewReorderList();
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 
// TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

showViewRegisterMedicine();
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 


// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

showViewRegisterMedicine();  
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

showViewStock();
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

showAllInvoices();  
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

showAllInvoices(); 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField57ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed

        ViewInvoiceByInvoiceNumber.setVisible(true); 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
       
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        ViewInvoiceByInvoiceNumber.setVisible(true); 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
       
        ViewAllInvoiceByCustomerNumber.setVisible(false);
        ChangePassword.setVisible(false);
 


// TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jLabel112MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel112MouseClicked

showSearchInvoiceByInvoiceNumber();        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel112MouseClicked

    private void jTextField58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField58ActionPerformed

    private void jLabel115MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel115MouseClicked

showSearchInvoiceByCustomerNumber();        



// TODO add your handling code here:
    }//GEN-LAST:event_jLabel115MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

ViewAllInvoiceByCustomerNumber.setVisible(true); 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        
        
        ChangePassword.setVisible(false);
 


// TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

 
        AddEmployee.setVisible(false);
        SearchUpdateDeleteEmployee.setVisible(false);
        ViewEmployeeList.setVisible(false);
        RegisterMedicine.setVisible(false);
        SearchUpdateDeleteMedicine.setVisible(false);
        AddStock.setVisible(false);
        UpdateStock.setVisible(false);
        GenrateInvoice.setVisible(false);
        ViewRegisterMedicine.setVisible(false);
        ViewStock.setVisible(false);
        ReorderList.setVisible(false);
        ViewAllInvoices.setVisible(false);
        ViewInvoiceByInvoiceNumber.setVisible(false);
        ViewAllInvoiceByCustomerNumber.setVisible(true);
        ChangePassword.setVisible(false);
 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked

        jPasswordField5.setEchoChar((char) 0);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel83MouseClicked

    private void jLabel82MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseClicked

        jPasswordField4.setEchoChar((char) 0);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel82MouseClicked

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked

        jPasswordField3.setEchoChar((char) 0);

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel81MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseEntered

jPasswordField3.setEchoChar((char) 0);

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel81MouseEntered

    private void jLabel81MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseExited

jPasswordField3.setEchoChar('*');        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel81MouseExited

    private void jLabel82MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseExited

jPasswordField4.setEchoChar('*');        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel82MouseExited

    private void jLabel83MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseExited

jPasswordField5.setEchoChar('*');        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel83MouseExited

    private void jLabel83MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseEntered

jPasswordField5.setEchoChar((char) 0);        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel83MouseEntered

    private void jLabel82MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseEntered

jPasswordField4.setEchoChar((char) 0);        

// TODO add your handling code here:
    }//GEN-LAST:event_jLabel82MouseEntered

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
String str=jTextField7.getText();
for(int i=0;i<str.length();i++)
{
 char ch=str.charAt(i);
 int asc=ch;
 if(!(asc>=48 && asc<=57))
 {
  JOptionPane.showMessageDialog(this, "Invalid Mobile Number");
  jTextField7.setText("");
 }   
}
String str2=jTextField7.getText();
if(jTextField7.getText().length()>9)
{
 JOptionPane.showMessageDialog(this, "Mobile Number can not be more than 10 Digits");
  jTextField7.setText(str2.substring(0,10));  
}


// TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
String str=jTextField8.getText();
int a=0;
a=str.indexOf('@');
if(a==-1)
{
 JOptionPane.showMessageDialog(this,"Invalid Email");
 jTextField8.requestFocus();
}

// TODO add your handling code here:
    }//GEN-LAST:event_jTextField8FocusLost

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
            java.util.logging.Logger.getLogger(AdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame AddEmployee;
    private javax.swing.JInternalFrame AddStock;
    private javax.swing.JInternalFrame ChangePassword;
    private javax.swing.JInternalFrame GenrateInvoice;
    private javax.swing.JInternalFrame Invoice;
    private javax.swing.JInternalFrame RegisterMedicine;
    private javax.swing.JInternalFrame ReorderList;
    private javax.swing.JInternalFrame SearchUpdateDeleteEmployee;
    private javax.swing.JInternalFrame SearchUpdateDeleteMedicine;
    private javax.swing.JInternalFrame UpdateStock;
    private javax.swing.JInternalFrame ViewAllInvoiceByCustomerNumber;
    private javax.swing.JInternalFrame ViewAllInvoices;
    private javax.swing.JInternalFrame ViewEmployeeList;
    private javax.swing.JInternalFrame ViewInvoiceByInvoiceNumber;
    private javax.swing.JInternalFrame ViewRegisterMedicine;
    private javax.swing.JInternalFrame ViewStock;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JToolBar.Separator jSeparator18;
    private javax.swing.JToolBar.Separator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JToolBar.Separator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
