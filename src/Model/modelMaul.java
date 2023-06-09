/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Connector;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.*;

public class modelMaul extends Connector implements priceInterface{
    public String userCheck(String DBuser, String DBpw){
        try{
            String query = "SELECT role FROM accounts WHERE username='"+DBuser+"' and password='"+DBpw+"' ";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                if(res.getString("role").equals("admin")){
                    return "admin";
                }
                else if(res.getString("role").equals("renter")){
                    return "renter";
                }
            }
            
        }catch(Exception e){   
            JOptionPane.showMessageDialog(null, "Unregistered!");
        }
        return "none"; 
    }
    public String[][] readRenter(){
        String[][] data = new String[sumRenter()][7];
        int total = 0;
        try{
            String query = "SELECT * FROM renter";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                data[total][0] = res.getString("name");
                data[total][1] = res.getString("id");
                data[total][2] = res.getString("contact");
                data[total][3] = res.getString("duration");
                data[total][4] = res.getString("bill");
                data[total][5] = res.getString("status");
                data[total][6] = res.getString("room");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to read data!");
        }
        return data;
    }
    
    public int sumRenter(){
        int total = 0;
        try{
            String query = "SELECT * FROM renter";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                total++;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to read!");
        }
        return total;
    }
    
    public void delRent(String id, String room){
        try{
            String query = "DELETE FROM renter WHERE id='"+id+"' ";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            String queryUp = "UPDATE rooms SET status='empty' WHERE name='"+room+"' ";
            stmt = connection.createStatement();
            stmt.executeUpdate(queryUp);
            JOptionPane.showMessageDialog(null, "Data Deleted!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to Delete Data!"+e);
        }
    }
    
    public void insertRent(String name, String id, String contact, int duration, String room){
        try{
            String query = "INSERT INTO renter VALUES ('"+name+"', '"+id+"', '"+contact+"', '"+duration+"','"+duration*getPrice(room)+"', 'notPaid', '"+room+"')";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Booking Success!");
        }
        catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Booking Failed! "+e);
        }
    }
    
    public void editRent(String name, String id, String contact, int duration, String room, String ID){
        try{
            String query = "UPDATE renter SET name ='"+name+"', id ='"+id+"', contact='"+contact+"', duration ='"+duration+"', bill ='"+duration*getPrice(room)+"' WHERE id ='"+ID+"' ";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successfuly Updated!");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Update Failed!");
        }
    }
    
    public int getPrice(String room){
        int price = 0;
        try{
            String query = "SELECT price FROM rooms WHERE name = '"+room+"'";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                price = Integer.parseInt(res.getString("price"));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to get price!");
        }
        return price;
    }
    
    public int sumRoom(){
        int total = 0;
        try{
            String query = "SELECT * FROM rooms";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                total++;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to count room!");
        }
        return total;
    }
    
    public String[][] readRoom(){
        String[][] data = new String[sumRoom()][4];
        int total = 0;
        
        try{
            String query = "SELECT * FROM rooms";
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                data[total][0] = res.getString("name");
                data[total][1] = res.getString("size");
                data[total][2] = res.getString("price");
                data[total][3] = res.getString("status");
                total++;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unable to read data!");
        }
        return data;
    }
    
    public void updateStatus(String name, String ID, String room){
        try{
            String query = "UPDATE renter SET status='Paid' WHERE id='"+ID+"' ";
            stmt = connection.createStatement();
            stmt.executeQuery(query);
            String query1 = "UPDATE rooms SET status='"+name+"' WHERE name='"+room+"' ";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Update Success!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Update Failed!"+e);
        }
    }
}
