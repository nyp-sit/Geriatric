package entity;
public class catalogueItem {

	private int itemID;
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	private String brandName;
	private String model;
	private double price;
	private String description;
	private String image;

	public catalogueItem(){
		super();
	}
	
	public catalogueItem(String brandName, String model, double price,
			String description, String image) {
		this.brandName = brandName;
		this.description = description;
		this.model = model;
		this.price = price;
		this.image = image;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
