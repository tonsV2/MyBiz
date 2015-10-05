package dk.fitfit.mybiz.entities;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dk.fitfit.mybiz.Settings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Expense {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	private double price;
	private int amount = 1;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
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

	public Date getOldDate() {
		return toDate(date);
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}
/*
	public Date getDate() {
		return toDate(getLocalDate());
	}

	public void setDate(final Date date) {
		this.date = toLocalDate(date);
	}

	public LocalDate getLocalDate() {
		return date;
	}

	public void setLocalDate(final LocalDate date) {
		this.date = date;
	}

	private LocalDate toLocalDate(final Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
*/

	private Date toDate(final LocalDate localDate) {
		final Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

}
