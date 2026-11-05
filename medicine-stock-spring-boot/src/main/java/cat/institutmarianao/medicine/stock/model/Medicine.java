package cat.institutmarianao.medicine.stock.model;

import java.io.Serializable;
import java.util.Objects;

// Domain entity representing a medicine item in stock
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private double price;
	private String description;
	private String producer;
	private String category;
	private long stockQuantity;
	private long stockInOrder;
	private boolean active;

	// Default no-args constructor
	public Medicine() {
	}

	// Constructor with essential fields for creating a Medicine instance
	public Medicine(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public long getStockInOrder() {
		return stockInOrder;
	}

	public void setStockInOrder(long stockInOrder) {
		this.stockInOrder = stockInOrder;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	// Equals and HashCode based on entity identity (id)
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Medicine medicine) {
			return Objects.equals(id, medicine.id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}