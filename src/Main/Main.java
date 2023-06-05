/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.LogControl;
import Model.modelMaul;
import View.LoginPageView;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginPageView loginPage = new LoginPageView();
        modelMaul model = new modelMaul();
        LogControl login = new LogControl(loginPage, model);
        
    }
    
}
