package com.accp.myoa.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.accp.myoa.service.GoodsService;

public class GoodsAction extends DispatchAction {
	private GoodsService goodsService;

	public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public ActionForward toIndividualInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {		
		return mapping.findForward("individualInfo");
	}
	

}
