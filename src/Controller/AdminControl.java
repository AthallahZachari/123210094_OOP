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
import View.AdminPageView;
import View.LoginPageView;
import View.EditRentView;
import Controller.RentEditControl;
import Controller.LogControl;

public class AdminControl {
    AdminPageView admView;
    modelMaul model;
    String[][] data;
    int row;
    
    public AdminControl(AdminPageView admView, modelMaul model){
        this.admView = admView;
        this.model = model;
        showData();
        
        admView.blogout.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0){
               LoginPageView admView = new LoginPageView();
               LogControl login = new LogControl(admView, model);
               admView.dispose();
           }
        });
        
        admView.tabel.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e){
               row = admView.tabel.getSelectedRow();
               if(data[row][5].equals("notPaid")){
                   int input = JOptionPane.showConfirmDialog(null, "Make changes on renter '"+data[row][0]+"' to 'Paid?'", "Option", JOptionPane.YES_NO_OPTION);
                   if(input==0){
                       admView.updateStatus(data[row][0], data[row][1], data[row][6]);
                       showData();
                   }
               }else{
                   int input = JOptionPane.showConfirmDialog(null, "Delete '"+data[row][6]+"'?", "Option", JOptionPane.YES_NO_OPTION);
                   if(input==0){
                       model.delRent(data[row][1], data[row][6]);
                       showData();
                   }
                   else{
                       int input1 = JOptionPane.showConfirmDialog(null, "Delete '"+data[row][6]+"'?", "Option", JOptionPane.YES_NO_OPTION);
                       if(input==0){
                           EditRentView REView = new EditRentView();
                           RentEditControl rentControl = new RentEditControl(REView, model, data[row]);
                           REView.setVisible(true);
                           admView.window.dispose();
                       }
                   }
               }
           } 
        });
    }
    
    void showData(){
        data = model.readRenter();
        String[] columnName={"Name", "ID", "Contact", "Duration", "Bill", "Status", "Room"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnName){
            public boolean isCellEditTable(int row, int column){
                return false;
            }
            
        };
        admView.tabel.setModel(tableModel);
    }
}
