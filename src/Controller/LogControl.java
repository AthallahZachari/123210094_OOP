/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; 
import Model.modelMaul;
import View.AdminPageView;
import View.RenterDataView;
import View.LoginPageView;
import View.RoomListView;


public class LogControl {
    LoginPageView loginPage;
    modelMaul model;
    
    public LogControl(LoginPageView loginPage, modelMaul model) {
        this.loginPage = loginPage;
        this.model = model;
        
        loginPage.blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.userCheck(loginPage.getUsername(), loginPage.getPassword()).equals("admin")){
                    AdminPageView admView = new AdminPageView();
                    AdminControl admControl =new AdminControl(admView,model);
                    loginPage.dispose();
                }
                else if(model.userCheck(loginPage.getUsername(), loginPage.getPassword()).equals("renter")){
                    RoomListView roomView = new RoomListView();
                    RoomControl roomControl = new RoomControl(roomView,model);
                    loginPage.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid username/password!");
                }
            }
        });
    }
}
