/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author duchi
 */
public class Item {
    private Sach sach;
    private int quantity;

    public Item() {} // Constructor mặc định

    public Item(Sach sach, int quantity) {
        this.sach = sach;
        this.quantity = quantity;
    }

    // Đảm bảo Getter/Setter viết đúng
    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}