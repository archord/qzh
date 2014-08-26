package com.mseeworld.qzh.model.dto;

import com.mseeworld.qzh.model.SaleItem;
import java.util.List;

public class OrderFormDto {
	private List<SaleItem> saleitems;

	public List<SaleItem> getSaleitems() {
		return saleitems;
	}

	public void setSaleitems(List<SaleItem> saleitems) {
		this.saleitems = saleitems;
	}
	
	
}
