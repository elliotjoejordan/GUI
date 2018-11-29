package pack1;

import apple.laf.JRSUIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class gui1 {
    private JPanel panel1;
    private JButton displaySelected;
    private JTabbedPane tabbedPane1;
    private JFormattedTextField searchField;
    private JCheckBox IDCheckBox;
    private JCheckBox alldetailsBox;
    private JCheckBox NameCheckBox;
    private JCheckBox DOB;
    private JCheckBox RiskLevelBox;
    private JCheckBox ContactBox;
    private JCheckBox NextOfKinBox;
    private JCheckBox PatientCommentsBox;
    private JFormattedTextField addIDNew;
    private JTabbedPane tabbedPane2;
    private JFormattedTextField addAddress;
    private JButton addButton;
    private JComboBox addID2;
    private JFormattedTextField diagCom;
    private JButton addButton1;
    private JButton addButton2;
    private JFormattedTextField ConsComments;
    private JButton displayAll;
    private JTable displayTable;
    private JComboBox addID3;
    private JComboBox addID4;
    private JComboBox addID5;
    private JComboBox diagno;
    private JComboBox diagnostician;
    private JComboBox addDiDate1;
    private JComboBox addDiDate2;
    private JComboBox addDiDate3;
    private JFormattedTextField MedstoAdd;
    private JButton addButton3;
    private JButton addButton4;
    private JFormattedTextField addFname;
    private JFormattedTextField addSname;
    private JComboBox addPDay;
    private JComboBox addPMonth;
    private JComboBox addPYear;
    private JFormattedTextField addContactDetails;
    private JComboBox addPractice;
    private JFormattedTextField addKin;
    private JFormattedTextField kinContact;
    private JComboBox addRisk;
    private JComboBox addConsultant;
    private JFormattedTextField addCommentsP;
    private JComboBox ConsDay;
    private JComboBox ConsMonth;
    private JComboBox ConsYear;
    private JComboBox ConsHour;
    private JComboBox ConsMinute;
    private JComboBox ConsPractice;
    private JComboBox ConsClinician;
    private JFormattedTextField addTreatment;
    private JComboBox MedDay;
    private JComboBox MedMonth;
    private JComboBox MedYear;
    private JComboBox PrescriberAdd;
    private JFormattedTextField MedComments;
    private JButton searchButton1;
    private JCheckBox GPBox;
    private JCheckBox ConsultantBox;
    private JCheckBox HistoryBox;
    private JCheckBox DiagnosisBox;
    private JCheckBox MedicationBox;
    private JCheckBox TreatmentBox;
    private JCheckBox patientInformationCheckBox;
    private JButton autoFillForEditButton;
    private JButton EditPButton;
    private JButton autoFillForEditButton1;
    private JButton autoFillConsult;
    private JButton autoFillForEditButton2;
    private JButton EditDButton;
    private JButton EditCons;
    private JButton EditMeds;

    public gui1() {
        alldetailsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alldetailsBox.isSelected()){
                    NameCheckBox.setSelected(true);
                    IDCheckBox.setSelected(true);
                    DOB.setSelected(true);
                    RiskLevelBox.setSelected(true);
                    ContactBox.setSelected(true);
                    NextOfKinBox.setSelected(true);
                    PatientCommentsBox.setSelected(true);
                    GPBox.setSelected(true);
                    ConsultantBox.setSelected(true);
                    DiagnosisBox.setSelected(false);
                    TreatmentBox.setSelected(false);
                    HistoryBox.setSelected(false);
                    MedicationBox.setSelected(false);
                    patientInformationCheckBox.setSelected(false);

                }
            }
        });
        tabbedPane2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fillBoxes();
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "INSERT INTO Diagnosis (Patient_ID, Diagnosis, Diagnostician, Diagnosis_Date, DiagnosisComments) VALUES (?,?,?,?,?)";
                    PreparedStatement state = connect.prepareStatement(query);
                    state.setString(1, addID2.getSelectedItem().toString());
                    state.setString(2, diagno.getSelectedItem().toString());
                    state.setString(3, diagnostician.getSelectedItem().toString());
                    state.setDate(4, getDateFromBoxes(addDiDate1, addDiDate2, addDiDate3));
                    state.setString(5, diagCom.getText());
                    state.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Success");
                    addID2.setSelectedIndex(0);
                    diagno.setSelectedIndex(0);
                    diagnostician.setSelectedIndex(0);
                    addDiDate1.setSelectedIndex(0);
                    addDiDate2.setSelectedIndex(0);
                    addDiDate3.setSelectedIndex(0);
                    diagCom.setText("");
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "INSERT INTO Patients (Patient_ID, Surname, Forename, Address, DOB, contact_detail, GP_Practice, Next_of_Kin, Next_of_Kin_contact, Risk_Cat, Consultant, PatientComments) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement state = connect.prepareStatement(query);
                    if (validNewID(addIDNew.getText())) {
                        if (nonZeroCheck(addFname.getText())) {
                            if (nonZeroCheck(addSname.getText())) {
                                if (nonZeroAddress(addAddress.getText())) {
                                    if (validPhone(addContactDetails.getText())) {
                                        if (nonZeroCheck(addKin.getText())) {
                                            if (validPhone(kinContact.getText())) {
                                                state.setString(1, addIDNew.getText());
                                                state.setString(3, addFname.getText());
                                                state.setString(2, addSname.getText());
                                                state.setString(4, addAddress.getText());
                                                state.setDate(5, getDateFromBoxes(addPDay, addPMonth, addPYear));
                                                state.setString(6, addContactDetails.getText());
                                                state.setString(7, addPractice.getSelectedItem().toString());
                                                state.setString(8, addKin.getText());
                                                state.setString(9, kinContact.getText());
                                                state.setString(10, addRisk.getSelectedItem().toString());
                                                state.setString(11, addConsultant.getSelectedItem().toString());
                                                state.setString(12, addCommentsP.getText());
                                                state.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Success");
                                                addIDNew.setText("");
                                                addFname.setText("");
                                                addSname.setText("");
                                                addAddress.setText("");
                                                addPDay.setSelectedIndex(0);
                                                addPMonth.setSelectedIndex(0);
                                                addPYear.setSelectedIndex(0);
                                                addContactDetails.setText("");
                                                addPractice.setSelectedIndex(0);
                                                addKin.setText("");
                                                kinContact.setText("");
                                                addRisk.setSelectedIndex(0);
                                                addConsultant.setSelectedIndex(0);
                                                addCommentsP.setText("");
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Enter Valid Contact Details For A Next Of Kin");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Enter A Valid Next Of Kin");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Enter A Valid Phone Number");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Enter A Valid Address");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Enter A Valid Surname");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Enter A Valid Forename");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Valid New Patient ID");
                    }

                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        addButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "INSERT INTO Consultations (Patient_ID, Date, Time, Location, Clinician, ConsultationComments) VALUES (?,?,?,?,?,?)";
                    PreparedStatement state = connect.prepareStatement(query);
                    state.setString(1,addID3.getSelectedItem().toString());
                    state.setDate(2, getDateFromBoxes(ConsDay, ConsMonth, ConsYear));
                    state.setTime(3, getTimeFromBoxes(ConsHour, ConsMinute));
                    state.setString(4, ConsPractice.getSelectedItem().toString());
                    state.setString(5, ConsClinician.getSelectedItem().toString());
                    state.setString(6, ConsComments.getText());
                    state.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Success");
                    addID3.setSelectedIndex(0);
                    ConsComments.setText("");
                    ConsDay.setSelectedIndex(0);
                    ConsMonth.setSelectedIndex(0);
                    ConsYear.setSelectedIndex(0);
                    ConsHour.setSelectedIndex(0);
                    ConsMinute.setSelectedIndex(0);
                    ConsPractice.setSelectedIndex(0);
                    ConsClinician.setSelectedIndex(0);
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "INSERT INTO Medication (Patient_ID, Prescribed_Meds, Prescriber, Prescription_Date, MedicationComments) VALUES (?,?,?,?,?)";
                    PreparedStatement state = connect.prepareStatement(query);
                    if(nonZeroCheck(MedstoAdd.getText())) {
                        state.setString(1, addID4.getSelectedItem().toString());
                        state.setString(2, MedstoAdd.getText());
                        state.setString(3, PrescriberAdd.getSelectedItem().toString());
                        state.setDate(4, getDateFromBoxes(MedDay, MedMonth, MedYear));
                        state.setString(5, MedComments.getText());
                        state.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Success");
                        addID4.setSelectedIndex(0);
                        MedstoAdd.setText("");
                        PrescriberAdd.setSelectedIndex(0);
                        MedDay.setSelectedIndex(0);
                        MedMonth.setSelectedIndex(0);
                        MedYear.setSelectedIndex(0);
                        MedComments.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Valid Medication");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "INSERT INTO Treatments (Patient_ID, Treatment) VALUES (?,?)";
                    PreparedStatement state = connect.prepareStatement(query);
                    if(nonZeroCheck(addTreatment.getText().toString())) {
                        state.setString(1, addID5.getSelectedItem().toString());
                        state.setString(2, addTreatment.getText());
                        state.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Success");
                        addID4.setSelectedIndex(0);
                        addTreatment.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Valid Treatment");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        HistoryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HistoryBox.isSelected()){
                    NameCheckBox.setSelected(false);
                    IDCheckBox.setSelected(false);
                    DOB.setSelected(false);
                    RiskLevelBox.setSelected(false);
                    ContactBox.setSelected(false);
                    NextOfKinBox.setSelected(false);
                    PatientCommentsBox.setSelected(false);
                    DiagnosisBox.setSelected(false);
                    TreatmentBox.setSelected(false);
                    MedicationBox.setSelected(false);
                    alldetailsBox.setSelected(false);
                    GPBox.setSelected(false);
                    ConsultantBox.setSelected(false);
                    patientInformationCheckBox.setSelected(false);

                }
            }
        });
        DiagnosisBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DiagnosisBox.isSelected()){
                    NameCheckBox.setSelected(false);
                    IDCheckBox.setSelected(false);
                    DOB.setSelected(false);
                    RiskLevelBox.setSelected(false);
                    ContactBox.setSelected(false);
                    NextOfKinBox.setSelected(false);
                    PatientCommentsBox.setSelected(false);
                    HistoryBox.setSelected(false);
                    TreatmentBox.setSelected(false);
                    MedicationBox.setSelected(false);
                    alldetailsBox.setSelected(false);
                    GPBox.setSelected(false);
                    ConsultantBox.setSelected(false);
                    patientInformationCheckBox.setSelected(false);

                }
            }
        });
        MedicationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MedicationBox.isSelected()){
                    NameCheckBox.setSelected(false);
                    IDCheckBox.setSelected(false);
                    DOB.setSelected(false);
                    RiskLevelBox.setSelected(false);
                    ContactBox.setSelected(false);
                    NextOfKinBox.setSelected(false);
                    PatientCommentsBox.setSelected(false);
                    DiagnosisBox.setSelected(false);
                    TreatmentBox.setSelected(false);
                    HistoryBox.setSelected(false);
                    alldetailsBox.setSelected(false);
                    GPBox.setSelected(false);
                    ConsultantBox.setSelected(false);
                    patientInformationCheckBox.setSelected(false);

                }
            }
        });
        TreatmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TreatmentBox.isSelected()){
                    NameCheckBox.setSelected(false);
                    IDCheckBox.setSelected(false);
                    DOB.setSelected(false);
                    RiskLevelBox.setSelected(false);
                    ContactBox.setSelected(false);
                    NextOfKinBox.setSelected(false);
                    PatientCommentsBox.setSelected(false);
                    DiagnosisBox.setSelected(false);
                    MedicationBox.setSelected(false);
                    HistoryBox.setSelected(false);
                    alldetailsBox.setSelected(false);
                    GPBox.setSelected(false);
                    ConsultantBox.setSelected(false);
                    patientInformationCheckBox.setSelected(false);
                }
            }
        });
        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (DiagnosisBox.isSelected()) {
                        diagnosisSearch();
                    } else if (MedicationBox.isSelected()) {
                        medicationSearch();
                    } else if (HistoryBox.isSelected()) {
                        consultationSearch();
                    } else if (TreatmentBox.isSelected()) {
                        treatmentSearch();
                    } else {
                        patientSearch();
                    }
            }
        });
        NameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        IDCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        DOB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        RiskLevelBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        ContactBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        ConsultantBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        GPBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
                MedicationBox.setSelected(false);
            }
        });
        NextOfKinBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        PatientCommentsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiagnosisBox.setSelected(false);
                TreatmentBox.setSelected(false);
                HistoryBox.setSelected(false);
                MedicationBox.setSelected(false);
                patientInformationCheckBox.setSelected(false);
            }
        });
        displayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel model = (DefaultTableModel) displayTable.getModel();
                    model.setColumnCount(0);
                    model.setRowCount(0);
                    String request = "";
                    ResultSet result = sqlRequest("SELECT * FROM Patients");
                    ResultSetMetaData md = result.getMetaData();
                    int columnCount = md.getColumnCount();
                    ArrayList<String> headers = new ArrayList<String>();
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(md.getColumnName(i));
                        headers.add(md.getColumnName(i));
                    }
                    model.addRow(headers.toArray());
                    for (int i = 1; i <= columnCount; i++) {
                        TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                        c.setHeaderValue((String) md.getColumnName(i));
                    }
                    Vector row;
                    while (result.next()) {
                        ArrayList<String> data = new ArrayList<String>();
                        for (int i = 1; i <= columnCount; i++) {
                            data.add(result.getString(i));
                        }
                        model.addRow(data.toArray());
                    }
                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Connection Failed");
                }
            }
        });
        patientInformationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (patientInformationCheckBox.isSelected()){
                    NameCheckBox.setSelected(false);
                    IDCheckBox.setSelected(false);
                    DOB.setSelected(false);
                    RiskLevelBox.setSelected(false);
                    ContactBox.setSelected(false);
                    NextOfKinBox.setSelected(false);
                    PatientCommentsBox.setSelected(false);
                    DiagnosisBox.setSelected(false);
                    MedicationBox.setSelected(false);
                    HistoryBox.setSelected(false);
                    alldetailsBox.setSelected(false);
                    GPBox.setSelected(false);
                    ConsultantBox.setSelected(false);
                    TreatmentBox.setSelected(false);
                }
            }
        });
        displaySelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> toShow = new ArrayList<String>();
                if(!IDCheckBox.isSelected()){
                    toShow.add("0");
                }
                if(NameCheckBox.isSelected() == false){
                    toShow.add("1");
                    toShow.add("2");
                }
                if(!ContactBox.isSelected()){
                    toShow.add("3");
                }
                if(!DOB.isSelected()){
                    toShow.add("4");
                }

                if(!ContactBox.isSelected()){
                    toShow.add("5");
                }
                if(!GPBox.isSelected()){
                    toShow.add("6");
                }
                if(!NextOfKinBox.isSelected()){
                    toShow.add("7");
                    toShow.add("8");
                }
                if(!RiskLevelBox.isSelected()){
                    toShow.add("9");
                }
                if(!ConsultantBox.isSelected()){
                    toShow.add("10");
                }

                if(!PatientCommentsBox.isSelected()){
                    toShow.add("11");
                }
                try {
                    DefaultTableModel model = (DefaultTableModel) displayTable.getModel();
                    model.setColumnCount(0);
                    model.setRowCount(0);
                    String request = "";
                    ResultSet result = sqlRequest("SELECT * FROM Patients");
                    ResultSetMetaData md = result.getMetaData();
                    int columnCount = md.getColumnCount();
                    ArrayList<String> headers = new ArrayList<String>();
                    for (int i = 1; i <= columnCount; i++) {
                        model.addColumn(md.getColumnName(i));
                        headers.add(md.getColumnName(i));
                    }
                    model.addRow(headers.toArray());
                    for (int i = 1; i <= columnCount; i++) {
                        TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                        c.setHeaderValue((String) md.getColumnName(i));
                    }
                    Vector row;
                    while (result.next()) {
                        ArrayList<String> data = new ArrayList<String>();
                        for (int i = 1; i <= columnCount; i++) {
                            data.add(result.getString(i));
                        }
                        model.addRow(data.toArray());
                    }
                    for(int i=toShow.size()-1; i>-1;i--) {
                        displayTable.removeColumn(displayTable.getColumnModel().getColumn(Integer.parseInt(toShow.get(i))));
                    }
                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Connection Failed");
                }
            }
        });
        autoFillForEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = addIDNew.getText();
                    if (id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Input an ID for Autofill");
                    }
                    else {
                        ResultSet info = sqlRequest("SELECT Patient_ID FROM Patients");
                        boolean found = false;
                        while (info.next()) {
                            if (info.getString(1).equals(id)) {
                                found = true;
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Input an existing ID for Autofill");
                        }
                        else{
                            Connection connect = null;
                            Class.forName("com.mysql.jdbc.Driver");
                            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                            String query = "SELECT * FROM Patients WHERE Patient_ID Like ?";
                            PreparedStatement state = connect.prepareStatement(query);
                            state.setString(1, id);
                            ResultSet result = state.executeQuery();
                            result.next();
                            addFname.setValue(result.getString(3));
                            addSname.setValue(result.getString(2));
                            addAddress.setValue(result.getString(4));
                            addContactDetails.setValue(result.getString(6));
                            addKin.setValue(result.getString(8));
                            kinContact.setValue(result.getString(9));
                            addRisk.setSelectedItem(result.getString(10));
                            addConsultant.setSelectedItem(result.getString(11));
                            addPractice.setSelectedItem(result.getString(7));
                            addCommentsP.setValue(result.getString(12));
                            addPYear.setSelectedItem(result.getString(5).toString().substring(0, 4));
                            addPMonth.setSelectedItem(result.getString(5).toString().substring(5,7));
                            addPDay.setSelectedItem(result.getString(5).toString().substring(8,10));
                        }
                    }

                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Request Failed");
                }
            }
        });
        EditPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "UPDATE Patients SET Surname = ?, Forename = ?, Address = ?, DOB = ?, contact_detail = ?, GP_Practice = ?, Next_of_Kin = ?, Next_of_Kin_contact = ?, Risk_Cat = ?, Consultant = ?, PatientComments = ? WHERE Patient_ID = ?";
                    PreparedStatement state = connect.prepareStatement(query);
                    if (!validNewID(addIDNew.getText())) {
                        if (nonZeroCheck(addFname.getText())) {
                            if (nonZeroCheck(addSname.getText())) {
                                if (nonZeroAddress(addAddress.getText())) {
                                    if (validPhone(addContactDetails.getText())) {
                                        if (nonZeroCheck(addKin.getText())) {
                                            if (validPhone(kinContact.getText())) {
                                                state.setString(2, addFname.getText());
                                                state.setString(1, addSname.getText());
                                                state.setString(3, addAddress.getText());
                                                state.setDate(4, getDateFromBoxes(addPDay, addPMonth, addPYear));
                                                state.setString(5, addContactDetails.getText());
                                                state.setString(6, addPractice.getSelectedItem().toString());
                                                state.setString(7, addKin.getText());
                                                state.setString(8, kinContact.getText());
                                                state.setString(9, addRisk.getSelectedItem().toString());
                                                state.setString(10, addConsultant.getSelectedItem().toString());
                                                state.setString(11, addCommentsP.getText());
                                                state.setString(12, addIDNew.getText());
                                                state.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Success");
                                                addIDNew.setText("");
                                                addFname.setText("");
                                                addSname.setText("");
                                                addAddress.setText("");
                                                addPDay.setSelectedIndex(0);
                                                addPMonth.setSelectedIndex(0);
                                                addPYear.setSelectedIndex(0);
                                                addContactDetails.setText("");
                                                addPractice.setSelectedIndex(0);
                                                addKin.setText("");
                                                kinContact.setText("");
                                                addRisk.setSelectedIndex(0);
                                                addConsultant.setSelectedIndex(0);
                                                addCommentsP.setText("");
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Enter Valid Contact Details For A Next Of Kin");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Enter A Valid Next Of Kin");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Enter A Valid Phone Number");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Enter A Valid Address");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Enter A Valid Surname");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Enter A Valid Forename");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Pre-existing Patient ID");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        autoFillForEditButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = addID2.getSelectedItem().toString();
                    String diagnosis = diagno.getSelectedItem().toString();
                    if (id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Input an ID for Autofill");
                    }
                    else {
                        ResultSet info = sqlRequest("SELECT Patient_ID, Diagnosis FROM Diagnosis");
                        boolean found = false;
                        while (info.next()) {
                            if (info.getString(1).equals(id)) {
                                if (info.getString(2).equals(diagnosis)) {
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Input an existing Patient's ID and diagnosis for Autofill");
                        }
                        else{
                            Connection connect = null;
                            Class.forName("com.mysql.jdbc.Driver");
                            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                            String query = "SELECT * FROM Diagnosis WHERE Patient_ID Like ? AND Diagnosis Like ?";
                            PreparedStatement state = connect.prepareStatement(query);
                            state.setString(1, id);
                            state.setString(2, diagnosis);
                            ResultSet result = state.executeQuery();
                            result.next();
                            addID2.setSelectedItem(result.getString(1));
                            diagno.setSelectedItem(result.getString(2));
                            diagnostician.setSelectedItem(result.getString(3));
                            diagCom.setValue(result.getString(6));
                            addDiDate3.setSelectedItem(result.getString(4).toString().substring(0, 4));
                            addDiDate2.setSelectedItem(result.getString(4).toString().substring(5,7));
                            addDiDate1.setSelectedItem(result.getString(4).toString().substring(8,10));
                        }
                    }

                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Request Failed");
                }
            }
        });
        autoFillConsult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = addID3.getSelectedItem().toString();
                    String day = ConsDay.getSelectedItem().toString();
                    String month = ConsMonth.getSelectedItem().toString();
                    String year = ConsYear.getSelectedItem().toString();
                    String date = year + "-" + month + "-" + day;
                    if (id.equals("")) {
                        JOptionPane.showMessageDialog(null, "Input an ID for Autofill");
                    }
                    else {
                        ResultSet info = sqlRequest("SELECT Patient_ID, Date FROM Consultations");
                        boolean found = false;
                        while (info.next()) {
                            if (info.getString(1).equals(id)) {
                                if (info.getString(2).equals(date)) {
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Input an existing Patient's ID and consultation date for Autofill");
                        }
                        else{
                            Connection connect = null;
                            Class.forName("com.mysql.jdbc.Driver");
                            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                            String query = "SELECT * FROM Consultations WHERE Patient_ID Like ? AND Date Like ?";
                            PreparedStatement state = connect.prepareStatement(query);
                            state.setString(1, id);
                            state.setString(2, date);
                            ResultSet result = state.executeQuery();
                            result.next();
                            addID3.setSelectedItem(result.getString(1));
                            ConsPractice.setSelectedItem(result.getString(4));
                            ConsClinician.setSelectedItem(result.getString(5));
                            ConsComments.setValue(result.getString(7));
                            ConsHour.setSelectedItem(result.getString(3).toString().substring(0, 2));
                            ConsMinute.setSelectedItem(result.getString(3).toString().substring(3,5));
                        }
                    }

                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Request Failed");
                }
            }
        });
        autoFillForEditButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = addID4.getSelectedItem().toString();
                    String meds = MedstoAdd.getText();
                    if (meds.equals("")) {
                        JOptionPane.showMessageDialog(null, "Input a medication for Autofill");
                    }
                    else {
                        ResultSet info = sqlRequest("SELECT Patient_ID, Prescribed_meds FROM Medication");
                        boolean found = false;
                        while (info.next()) {
                            if (info.getString(1).equals(id)) {
                                if (info.getString(2).equals(meds)) {
                                    found = true;
                                }
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Input an existing Patient's ID and medication for Autofill");
                        }
                        else{
                            Connection connect = null;
                            Class.forName("com.mysql.jdbc.Driver");
                            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                            String query = "SELECT * FROM Medication WHERE Patient_ID Like ? AND Prescribed_meds Like ?";
                            PreparedStatement state = connect.prepareStatement(query);
                            state.setString(1, id);
                            state.setString(2, meds);
                            ResultSet result = state.executeQuery();
                            result.next();
                            addID4.setSelectedItem(result.getString(1));
                            MedstoAdd.setValue(result.getString(2));
                            PrescriberAdd.setSelectedItem(result.getString(3));
                            MedComments.setValue(result.getString(6));
                            MedYear.setSelectedItem(result.getString(4).toString().substring(0, 4));
                            MedMonth.setSelectedItem(result.getString(4).toString().substring(5,7));
                            MedDay.setSelectedItem(result.getString(4).toString().substring(8,10));
                        }
                    }

                }catch(Exception x){
                    x.printStackTrace();
                    System.out.println("Request Failed");
                }
            }
        });
        EditDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "UPDATE Diagnosis SET Diagnostician = ?, Diagnosis_Date = ?, DiagnosisComments = ? WHERE Patient_ID = ? AND Diagnosis = ?";
                    PreparedStatement state = connect.prepareStatement(query);
                    if (!validNewID(addID2.getSelectedItem().toString())) {
                        if(currentDiagnosis(diagno.getSelectedItem().toString())){
                            state.setString(1, diagnostician.getSelectedItem().toString());
                            state.setString(3, diagCom.getText());
                            state.setDate(2, getDateFromBoxes(addDiDate1, addDiDate2, addDiDate3));
                            state.setString(4, addID2.getSelectedItem().toString());
                            state.setString(5, diagno.getSelectedItem().toString());
                            state.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Success");
                            addID2.setSelectedIndex(0);
                            diagno.setSelectedIndex(0);
                            diagnostician.setSelectedIndex(0);
                            diagCom.setText("");
                            addDiDate1.setSelectedIndex(0);
                            addDiDate2.setSelectedIndex(0);
                            addDiDate3.setSelectedIndex(0);
                        }else{
                            JOptionPane.showMessageDialog(null, "Enter A pre-existing diagnosis for selected patient");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Pre-existing Patient ID");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        EditCons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "UPDATE Consultations SET Time = ?, Location = ?, Clinician = ?, ConsultationComments = ? WHERE Patient_ID = ? AND Date = ?";
                    PreparedStatement state = connect.prepareStatement(query);
                    if (!validNewID(addID3.getSelectedItem().toString())) {
                        if(currentDate(ConsYear.getSelectedItem().toString() + "-" + ConsMonth.getSelectedItem().toString() + "-" + ConsDay.getSelectedItem().toString())){
                            state.setTime(1, getTimeFromBoxes(ConsHour, ConsMinute));
                            state.setString(2, ConsPractice.getSelectedItem().toString());
                            state.setString(3, ConsClinician.getSelectedItem().toString());
                            state.setString(4, ConsComments.getText());
                            state.setString(5, addID3.getSelectedItem().toString());
                            state.setDate(6, getDateFromBoxes(ConsDay, ConsMonth, ConsYear));
                            System.out.println(state);
                            state.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Success");
                            addID3.setSelectedIndex(0);
                            ConsYear.setSelectedIndex(0);
                            ConsPractice.setSelectedIndex(0);
                            ConsComments.setText("");
                            ConsMonth.setSelectedIndex(0);
                            ConsDay.setSelectedIndex(0);
                            ConsMinute.setSelectedIndex(0);
                            ConsHour.setSelectedIndex(0);
                            ConsClinician.setSelectedIndex(0);
                        }else{
                            JOptionPane.showMessageDialog(null, "Enter A pre-existing consultation date for selected patient");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Pre-existing Patient ID");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
        EditMeds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connect = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
                    String query = "UPDATE Medication SET Prescriber = ?, Prescription_Date = ?, MedicationComments = ? WHERE Patient_ID = ? AND Prescribed_meds = ? ";
                    PreparedStatement state = connect.prepareStatement(query);
                    if (!validNewID(addID4.getSelectedItem().toString())) {
                        if(nonZeroCheck(MedstoAdd.getText())){
                            if(currentMed(MedstoAdd.getText())){
                                state.setString(1, PrescriberAdd.getSelectedItem().toString());
                                state.setDate(2, getDateFromBoxes(MedDay, MedMonth, MedYear));
                                state.setString(3, MedComments.getText());
                                state.setString(4, addID4.getSelectedItem().toString());
                                state.setString(5, MedstoAdd.getText());
                                state.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Success");
                                addID4.setSelectedIndex(0);
                                MedstoAdd.setText("");
                                PrescriberAdd.setSelectedIndex(0);
                                MedComments.setText("");
                                MedDay.setSelectedIndex(0);
                                MedMonth.setSelectedIndex(0);
                                MedYear.setSelectedIndex(0);
                            }else{
                                JOptionPane.showMessageDialog(null, "Enter A existing medication for selected patient");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Enter A medication for selected patient");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Enter A Pre-existing Patient ID");
                    }
                }catch (Exception x){
                    x.printStackTrace();
                    System.out.println("connection fail");
                }
            }
        });
    }
    private static final String url = "jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS";
    private static final String username = "kbzh45";
    private static final String password = "quac73k";

    private Date getDateFromBoxes(JComboBox combo1, JComboBox combo2, JComboBox combo3){
        String day =  combo1.getSelectedItem().toString();
        String month = combo2.getSelectedItem().toString();
        String year = combo3.getSelectedItem().toString();
        String dated = (year + '-' + month + '-' + day);
        java.sql.Date date= java.sql.Date.valueOf(dated);
        return (date);
    }
    private Time getTimeFromBoxes(JComboBox combo1, JComboBox combo2){
        String hour = combo1.getSelectedItem().toString();
        String minute = combo2.getSelectedItem().toString();
        String timed = (hour + ':' + minute + ":00");
        java.sql.Time time = java.sql.Time.valueOf(timed);
        return(time);
    }
    private void fillBoxes(){
        try {
            ResultSet request = sqlRequest("SELECT Patient_ID FROM Patients");
            addID2.removeAllItems();
            addID3.removeAllItems();
            addID4.removeAllItems();
            addID5.removeAllItems();
            while (request.next()) {
                addID2.addItem(request.getString(1));
                addID3.addItem(request.getString(1));
                addID4.addItem(request.getString(1));
                addID5.addItem(request.getString(1));
            }
        }catch (Exception e){
                e.printStackTrace();
                System.out.println("Request Failure");
                }

    }
    private ResultSet sqlRequest(String request){
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url, username, password);
            PreparedStatement state = connect.prepareStatement( request);
            ResultSet result = state.executeQuery();
            return (result);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("connection fail");
        }
        return null;
    }
    private boolean validNewID(String ID){
        try {
            ResultSet request = sqlRequest("SELECT Patient_ID FROM Patients");
            while (request.next()) {
                if (ID.equals(request.getString(1))){
                    return (false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Request Failure");
        }
        if(ID.length() > 0) {
            return (true);
        }
        else{
            return(false);
        }
    }
    private boolean currentDiagnosis(String diag){
        try {
            ResultSet request = sqlRequest("SELECT Diagnosis FROM Diagnosis");
            while (request.next()) {
                if (diag.equals(request.getString(1))){
                    return (true);
                }
            }
            return (false);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Request Failure");
        }
        return(false);
    }
    private boolean currentMed(String med){
        try {
            ResultSet request = sqlRequest("SELECT Prescribed_meds FROM Medication");
            while (request.next()) {
                if (med.equals(request.getString(1))){
                    return (true);
                }
            }
            return (false);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Request Failure");
        }
        return(false);
    }
    private boolean currentDate(String diag){
        try {
            ResultSet request = sqlRequest("SELECT Date FROM Consultations");
            while (request.next()) {
                if (diag.equals(request.getString(1).toString())){
                    return (true);
                }
            }
            return (false);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Request Failure");
        }
        return(false);
    }
    private boolean nonZeroCheck(String thing){
        if (thing.length() <= 0){
            return (false);
        }
        else if(thing.length()>=30){
            return (false);
        }
        else {
            return (true);
        }
    }
    private boolean nonZeroAddress(String thing){
        if (thing.length() <= 0){
            return (false);
        }
        else {
            return (true);
        }
    }
    private boolean validPhone(String details){
        for (int i=0; i<details.length(); i++){
            if (Character.isDigit(details.charAt(i)) == false){
                return (false);
            }
        }
        if (details.length() != 0){
            if(details.length() < 13){
                return (true);
            }
        }
        return (false);
    }
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("gui1");
        frame1.setContentPane(new gui1().panel1);
        frame1.setSize(600, 500);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }
    private void patientSearch(){
        Connection connect = null;
        try {
            DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
            String query = "SELECT * FROM Patients WHERE Patient_ID Like ? OR Forename Like ? OR Surname Like ? OR GP_Practice Like ? OR Risk_Cat Like ? OR Consultant Like ?";
            PreparedStatement state = connect.prepareStatement(query);
            String searchString = "%" + searchField.getText() + "%";
            state.setString(1, searchString);
            state.setString(2, searchString);
            state.setString(3, searchString);
            state.setString(4, searchString);
            state.setString(5, searchString);
            state.setString(6, searchString);
            ResultSet result = state.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            ArrayList<String> headers = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnName(i));
                headers.add(md.getColumnName(i));
            }
            model.addRow(headers.toArray());
            for (int i = 1; i <= columnCount; i++) {
                TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                c.setHeaderValue((String) md.getColumnName(i));
            }
            Vector row;
            while (result.next()) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    results.add(result.getString(i));
                }
                model.addRow(results.toArray());
            }


        }catch (Exception x){
            x.printStackTrace();
            System.out.println("Search fail");
        }
    }
    private void treatmentSearch(){
        Connection connect = null;
        try {
            DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
            String query = "SELECT * FROM Treatments WHERE Patient_ID Like ? OR Treatment Like ?";
            PreparedStatement state = connect.prepareStatement(query);
            String searchString = "%" + searchField.getText() + "%";
            state.setString(1, searchString);
            state.setString(2, searchString);
            ResultSet result = state.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            ArrayList<String> headers = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnName(i));
                headers.add(md.getColumnName(i));
            }
            model.addRow(headers.toArray());
            for (int i = 1; i <= columnCount; i++) {
                TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                c.setHeaderValue((String) md.getColumnName(i));
            }
            Vector row;
            while (result.next()) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    results.add(result.getString(i));
                }
                model.addRow(results.toArray());
            }
        }catch (Exception x){
            x.printStackTrace();
            System.out.println("Search fail");
        }
    }
    private void medicationSearch(){
        Connection connect = null;
        try {
            DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
            String query = "SELECT * FROM Medication WHERE Patient_ID Like ? OR Prescribed_meds Like ? OR Prescriber Like ?";
            PreparedStatement state = connect.prepareStatement(query);
            String searchString = "%" + searchField.getText() + "%";
            state.setString(1, searchString);
            state.setString(2, searchString);
            state.setString(3, searchString);
            ResultSet result = state.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            ArrayList<String> headers = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnName(i));
                headers.add(md.getColumnName(i));
            }
            model.addRow(headers.toArray());
            for (int i = 1; i <= columnCount; i++) {
                TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                c.setHeaderValue((String) md.getColumnName(i));
            }
            Vector row;
            while (result.next()) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    results.add(result.getString(i));
                }
                model.addRow(results.toArray());
            }
        }catch (Exception x){
            x.printStackTrace();
            System.out.println("Search fail");
        }
    }
    private void diagnosisSearch(){
        Connection connect = null;
        try {
            DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
            String query = "SELECT * FROM Diagnosis WHERE Patient_ID Like ? OR Diagnosis Like ? OR Diagnostician Like ?";
            PreparedStatement state = connect.prepareStatement(query);
            String searchString = "%" + searchField.getText() + "%";
            state.setString(1, searchString);
            state.setString(2, searchString);
            state.setString(3, searchString);
            ResultSet result = state.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            ArrayList<String> headers = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnName(i));
                headers.add(md.getColumnName(i));
            }
            model.addRow(headers.toArray());
            for (int i = 1; i <= columnCount; i++) {
                TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                c.setHeaderValue((String) md.getColumnName(i));
            }
            Vector row;
            while (result.next()) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    results.add(result.getString(i));
                }
                model.addRow(results.toArray());
            }
        }catch (Exception x){
            x.printStackTrace();
            System.out.println("Search fail");
        }
    }
    private void consultationSearch(){
        Connection connect = null;
        try {
            DefaultTableModel model = (DefaultTableModel)displayTable.getModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://mysql.dur.ac.uk/Pkbzh45_PIS", "kbzh45", "quac73k");
            String query = "SELECT * FROM Consultations WHERE Patient_ID Like ? OR Location Like ? OR Clinician Like ?";
            PreparedStatement state = connect.prepareStatement(query);
            String searchString = "%" + searchField.getText() + "%";
            state.setString(1, searchString);
            state.setString(2, searchString);
            state.setString(3, searchString);
            ResultSet result = state.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            ArrayList<String> headers = new ArrayList<String>();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnName(i));
                headers.add(md.getColumnName(i));
            }
            model.addRow(headers.toArray());
            for (int i = 1; i <= columnCount; i++) {
                TableColumn c = displayTable.getTableHeader().getColumnModel().getColumn(i - 1);
                c.setHeaderValue((String) md.getColumnName(i));
            }
            Vector row;
            while (result.next()) {
                ArrayList<String> results = new ArrayList<String>();
                for (int i = 1; i <= columnCount; i++) {
                    results.add(result.getString(i));
                }
                model.addRow(results.toArray());
            }
        }catch (Exception x){
            x.printStackTrace();
            System.out.println("Search fail");
        }
    }
}
