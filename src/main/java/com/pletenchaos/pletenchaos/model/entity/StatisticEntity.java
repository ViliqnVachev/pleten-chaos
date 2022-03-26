package com.pletenchaos.pletenchaos.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "statistics")
public class StatisticEntity extends BaseEntity {

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private Double expenses;

	@Column(nullable = false)
	private Double income;

	@Column(nullable = false)
	private Double profit;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity creator;

	public StatisticEntity() {
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

}
