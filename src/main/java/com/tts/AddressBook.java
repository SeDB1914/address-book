package com.tts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AddressBook {

    Entry contacts;
    List contactList = new ArrayList<>();
    private JTable table1;

    AddressBook() {

        final JFrame[] frame = {new JFrame()};
        frame[0].setLayout(new FlowLayout());
        frame[0].setSize(360, 600);
        frame[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame[0].setVisible(true);

        JPanel leftColumn = new JPanel();


        JButton addContactButton = new JButton("Add Contact");
        JButton removeContactButton = new JButton("Remove Contact");
        JButton searchContactsButton = new JButton("Search");
        JButton printContactsButton = new JButton("Print Contacts");
        JButton deleteContactsButton = new JButton("Delete Contacts");
        JButton quit = new JButton("Quit Application");

        JLabel displayText = new JLabel();
        displayText.setPreferredSize(new Dimension(20, 20));

        JTextField firstName = new JTextField(20);
        JTextField lastName = new JTextField(20);
        JTextField phoneNumber = new JTextField(20);
        JTextField emailAddress = new JTextField(20);
        JTextField searchText = new JTextField(20);

        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        JLabel phoneNumberLabel = new JLabel("Phone Number");
        JLabel emailAddressLabel = new JLabel("Email Address");


        frame[0].add(firstNameLabel);
        frame[0].add(firstName);
        frame[0].add(lastNameLabel);
        frame[0].add(lastName);
        frame[0].add(phoneNumberLabel);
        frame[0].add(phoneNumber);
        frame[0].add(emailAddressLabel);
        frame[0].add(emailAddress);

        frame[0].add(displayText);
        frame[0].add(addContactButton);
        frame[0].add(removeContactButton);

        leftColumn.add(printContactsButton);
        leftColumn.add(deleteContactsButton);
        frame[0].add(leftColumn);
        frame[0].add(searchText);
        JScrollPane scrollPane = new JScrollPane();
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable();
        Object[] column = {"First Name", "Last Name", "Phone Number", "Email Address"};
        final Object[] row = new Object[4];
        model.setColumnIdentifiers(column);
        table.setModel(model);
        //scrollPane.setViewportView(leftColumn);
        frame[0].add(table);
        table.setBounds(200, 600, 100, 260);

        frame[0].add(searchContactsButton);
        frame[0].add(quit);

        addContactButton.addActionListener(e -> {
            if (firstName.getText().equals("") || lastName.getText().equals("") || phoneNumber.getText().equals("") || emailAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all fields.");
            } else {

                row[0] = firstName.getText();
                row[1] = lastName.getText();
                row[2] = phoneNumber.getText();
                row[3] = emailAddress.getText();
                model.addRow(row);
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                emailAddress.setText("");
            }
        });

        removeContactButton.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i > 0) model.removeRow(i);
        });

        searchContactsButton.addActionListener(e -> {
            String target = searchText.getText();
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    String next = (String) table.getValueAt(i, j);
                    if (next.equals(target)) {
                        table.repaint();
                    }
                }
            }
        });

//            printContactsButton.addActionListener(e -> {
//               displayText.setText(contactList.toString());
//
//            });

        deleteContactsButton.addActionListener(e -> {
            for(int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
        });

        quit.addActionListener(e -> {
            frame[0] = new JFrame();
            if (JOptionPane.showConfirmDialog(frame[0], "Do you wish to exit?", "Contacts", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        });

            JButton updateButton = new JButton("Update");
            leftColumn.add(updateButton);
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = table.getSelectedRow();
                    model.setValueAt(firstName.getText(), i, 0);
                    model.setValueAt(lastName.getText(), i, 1);
                    model.setValueAt(phoneNumber.getText(), i, 2);
                    model.setValueAt(emailAddress.getText(), i, 3);
                }
            });

            JButton filterButton = new JButton("Filter");


    }




}


