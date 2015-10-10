package dk.fitfit.mybiz.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dk.fitfit.mybiz.entities.JsonDateDeserializer;
import dk.fitfit.mybiz.entities.JsonDateSerializer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.util.List;

public class ExpenseResource extends ResourceSupport {
	private String name;
	private String description;
	private double price;
	private int amount = 1;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private LocalDate date;

	private ExpenseResource(final String name, final String description, final double price, final int amount, final LocalDate date) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.date = date;
	}

	public final static class Builder {
		private String name;
		private String description;
		private double price;
		private int amount;
		private LocalDate date;

		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		public Builder setDescription(final String description) {
			this.description = description;
			return this;
		}

		public Builder setPrice(final double price) {
			this.price = price;
			return this;
		}

		public Builder setAmount(final int amount) {
			this.amount = amount;
			return this;
		}

		public Builder setDate(final LocalDate date) {
			this.date = date;
			return this;
		}

		public ExpenseResource build() {
			return new ExpenseResource(name, description, price, amount, date);
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public List<Link> getLinks() {
		return super.getLinks();
	}
}
