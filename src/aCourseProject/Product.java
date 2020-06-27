package aCourseProject;


public class Product {

	private String productID, productName;
	private float price;
	private int quantity;
	
	/* ACCESSOR and MUTATORS methods */
	public String getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public float getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}
