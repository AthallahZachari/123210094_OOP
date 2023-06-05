/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.modelMaul;
import View.RenterDataView;
import View.LoginPageView;

public class RentDataControl {
    RenterDataView rentView = new RenterDataView();
    modelMaul model = new modelMaul();
    String room;
    
    public RentDataControl(RenterDataView rentView, modelMaul model, String room){
        this.rentView = rentView;
        this.model = model;
        this.room = room;
        
        rentView.getBtnAddPanel().addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0){
               System.out.println("add bg");
                model.insertRent(rentView.getName(), rentView.getID(), rentView.getContact(), rentView.getRentTime(), room);
            } 
        });
        rentView.getBtnLogout().addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0){
                LoginPageView logView = new LoginPageView();
                LogControl logControl = new LogControl(logView, model);
                rentView.window.dispose();
            } 
        });
    }
}
