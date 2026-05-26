package entity;

public class Sach {
    private int id;
    private String tenSach;
    private double gia;
    private String hinhAnh; // Đường dẫn ảnh nếu có

    public Sach() {
    }

    public Sach(int id, String tenSach, double gia, String hinhAnh) {
        this.id = id;
        this.tenSach = tenSach;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}