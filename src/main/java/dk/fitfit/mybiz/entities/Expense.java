package dk.fitfit.mybiz.entities;


import dk.fitfit.mybiz.Settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Expense implements Identifiable {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	private double price;
	private int amount = 1;
	private LocalDate date;

	public Expense() {
	}

	public Expense(final String name, final String description, final double price, final int amount, final LocalDate date) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.date = date;
	}

	public Expense(final long id, final String name, final String description, final double price, final int amount, final LocalDate date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.date = date;
	}

	@Override
	public Long getId() {
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

	// TODO: Without these methods getTotalPrice and getTotalPriceIncludingVat can't be invoked by reflection
	public void setTotalPrice(final double totalPrice) {
	}
	// TODO: Even the argument is required... getTotalPriceIncludingVat wont print as of now
	public void setTotalPriceIncludingVat() {
	}
	public double getTotalPrice() {
		return price * amount;
	}

	public double getTotalPriceIncludingVat() {
		return Settings.VAT * getTotalPrice();
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}
}
