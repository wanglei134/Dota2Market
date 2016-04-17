
package com.accp.myoa.web.form;

import org.apache.struts.action.ActionForm;

import com.accp.myoa.entity.Goods;


public class GoodsForm extends ActionForm {
	private Goods goods;
	
	public GoodsForm(){
		goods=new Goods();
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
	
