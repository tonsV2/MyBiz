package dk.fitfit.mybiz.entities;


import dk.fitfit.mybiz.Settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expense {
	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;
	private double price;
	private int amount = 1;

	public Expense() {
	}

	public Expense(final String name, final String description, final double price, final int amount) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
	}

	public Expense(final long id, final String name, final String description, final double price, final int amount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public double getTotalPrice() {
		return price * Settings.VAT * amount;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}
}
