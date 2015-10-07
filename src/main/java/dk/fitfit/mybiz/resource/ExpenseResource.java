package dk.fitfit.mybiz.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dk.fitfit.mybiz.entities.Expense;
import dk.fitfit.mybiz.entities.JsonDateDeserializer;
import dk.fitfit.mybiz.entities.JsonDateSerializer;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;

public class ExpenseResource extends ResourceSupport {
	@JsonInclude
	private long id;
	@JsonInclude
	private String name;
	@JsonInclude
	private String description;
	@JsonInclude
	private double price;
	@JsonInclude
	private int amount = 1;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private LocalDate date;

	public ExpenseResource(final long id, final String name, final String description, final double price, final int amount, final LocalDate date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.date = date;
	}

	public final static class Builder {
		private long id;
		private String name;
		private String description;
		private double price;
		private int amount;
		private LocalDate date;

		public Builder from(final Expense expense) {
			this.id = expense.getId();
			this.name = expense.getName();
			this.description = expense.getDescription();
			this.price = expense.getPrice();
			this.amount = expense.getAmount();
			this.date = expense.getDate();
			return this;
		}
		public Builder setId(final long id) {
			this.id = id;
			return this;
		}

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
			final ExpenseResource resource = new ExpenseResource(id, name, description, price, amount, date);
			return resource;
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
}
