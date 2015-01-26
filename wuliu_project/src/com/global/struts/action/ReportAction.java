/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.global.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.global.dao.BaseDao;
import com.global.dao.DeliverySpotDao;
import com.global.dao.ReportDao;
import com.global.struts.form.ReportForm;
import com.global.vo.Deliveryspot;

/** 
 * MyEclipse Struts
 * Creation date: 11-24-2008
 * 
 * XDoclet definition:
 * @struts.action path="/report" name="reportForm" input="/baoBiaoManage/viewBaoBiao.jsp" scope="request" validate="true"
 */
public class ReportAction extends BaseDispatchAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward queryHQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportForm reportForm = (ReportForm) form;// TODO Auto-generated method stub
		
		String fromTime = request.getParameter("txtDate1");
		String toTime = request.getParameter("txtDate2");
		
		ReportDao rdao = (ReportDao) super.getBean("ReportDao");
		BaseDao bdao = (BaseDao) super.getBean("BaseDao");
		
		double total = rdao.queryHQTurnover(fromTime, toTime);
		List reportList = rdao.queryHQ(fromTime, toTime);
		String msg = "�������͵�:" +fromTime + "---" + toTime + "����";
		String time = bdao.getCurrentTimeFromDB();
		
		int size = reportList.size();
		
		request.setAttribute("size", size);
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("total", total);
		request.setAttribute("msg", msg);
		request.setAttribute("time", time);
		 
		return mapping.getInputForward();
	}
	
	public ActionForward queryDeliverySpot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportForm reportForm = (ReportForm) form;// TODO Auto-generated method stub
		
		String fromTime = request.getParameter("txtDate1");
		String toTime = request.getParameter("txtDate2");
		String deliverySpotID = request.getParameter("dsid");
		
		ReportDao rdao = (ReportDao) super.getBean("ReportDao");
		DeliverySpotDao dsdao = (DeliverySpotDao) super.getBean("DeliverySpotDao");
		BaseDao bdao = (BaseDao) super.getBean("BaseDao");
		
		double total = rdao.queryDeliverySpotTurnover(fromTime, toTime, new Integer(deliverySpotID));
		List reportList = rdao.queryDeliverySpot(fromTime, toTime, new Integer(deliverySpotID));
		Deliveryspot ds = dsdao.queryDeliverySpotByDeliverySpotID(new Integer(deliverySpotID));
		String time = bdao.getCurrentTimeFromDB();
		String name = ds.getName();
		String msg = name+ ":" +fromTime + "---" + toTime + "����";
		
		int size = reportList.size();
		
		request.setAttribute("size", size);
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("total", total);
		request.setAttribute("msg", msg);
		request.setAttribute("time", time);
		
		return mapping.getInputForward();
	}
}