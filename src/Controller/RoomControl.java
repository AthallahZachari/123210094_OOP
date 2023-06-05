/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.modelMaul;
import View.RoomListView;
import View.RenterDataView;
import View.LoginPageView;
import Controller.RentDataControl;
import Controller.LogControl;

public class RoomControl {
    RoomListView roomView;
    modelMaul model;
    String[][] data;
    int row;
    
    public RoomControl(RoomListView roomView, modelMaul model){
        this.roomView = roomView;
        this.model = model;
        showData();
        
        roomView.tabel.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent e){
              row = roomView.tabel.getSelectedRow();
              if(data[row][3].equals("empty")){
                  RenterDataView rentView = new RenterDataView();
                  RentDataControl rentControl = new RentDataControl(rentView, model, data[row][0]);
                  roomView.window.dispose();
              }
              else{
                  JOptionPane.showMessageDialog(null, "Room is already occupied!");
              }
            }  
        });
        
        roomView.bcancel.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0){
               LoginPageView loginView = new LoginPageView();
               LogControl logControl = new LogControl(loginView, model);
               roomView.window.dispose();
           }
           
        });
    }
    void showData(){
        data = model.readRoom();
        String[] columnName = {"Name", "Size", "Price", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnName){
            public boolean isCellEditable(int row, int column){
            return false;
            }
        };
        roomView.tabel.setModel(tableModel);
    }
}
