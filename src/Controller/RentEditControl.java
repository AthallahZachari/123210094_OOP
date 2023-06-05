/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.modelMaul;
import View.AdminPageView;
import View.EditRentView;

public class RentEditControl {
    EditRentView editView;
    modelMaul model;
    String[] data;
    
    public RentEditControl(EditRentView editView, modelMaul model, String[] data){
        this.editView = editView;
        this.model = model;
        this.data = data;
        setForm();
        
        editView.btnSubmit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                model.editRent(editView.getName(), editView.getID(), editView.getContact(), editView.getDuration(), data[6], data[1]);
                AdminPageView admView = new AdminPageView();
                AdminControl admControl = new AdminControl(admView, model);
                editView.dispose();
            }
        });
    }

    void setForm(){
        editView.txtName.setText(data[0]);
        editView.txtID.setText(data[1]);
        editView.txtDuration.setText(data[2]);
        editView.txtContact.setText(data[3]);
    }
}
