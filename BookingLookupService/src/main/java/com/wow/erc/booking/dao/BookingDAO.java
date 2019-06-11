package com.wow.erc.booking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wow.erc.booking.model.Booking;
import com.wow.erc.booking.model.BookingAudit;

@Repository
public class BookingDAO  {

	
	   @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public List<Booking> findAll( String poNo, String dcNo, String orderDate, String deliveryDate, String vendorNo) {

	    	
	    	
	    	String sql="select B.BOOKING_ID, B.PO_NO,B.DC_NO, B.CREATE_DTETM as ORDER_DATE, B.DLVRY_DTE, B.DLVRY_DOCK, B.DLVRY_TIME,  " + 
	    			" B.VENDOR_NO, B.PALLET_QTY, BA.BOOKING_AUDIT_ID, BA.DLVRY_DTE as OLD_DLVRY_DTE, BA.DLVRY_TIME AS OLD_DLVRY_TIME, BA.DLVRY_DOCK AS OLD_DLVRY_DOCK, BA.CREATE_DTETM, BA.CREATE_USER_ID,BA.RSN_CODE " + 
	    			" ,CASE WHEN B.ORDR_TYPE = 'A' THEN 'FULFILMENT' ELSE 'MANUAL' END AS SOURCE " 
	    			+ " , CASE WHEN (SELECT MIN(ERROR_CODE) FROM PURCHASE_PLAN WHERE PO_NO = B.PO_NO) = 0 THEN 'Scheduled' ELSE 'Exception' END AS STATUS "
	    			+ " from booking B " + 
	    			"LEFT OUTER JOIN BOOKING_AUDIT BA ON B.PO_NO = BA.PO_NO WHERE 1=1 " ;
	    			
	    	if(poNo!=null&& poNo.length()>0)
	    		sql=sql+" and B.PO_NO='"+poNo+"'";
	    	
	    	if(dcNo!=null&& dcNo.length()>0)
	    		sql=sql+" and B.DC_NO='"+dcNo+"'";
	    	
	    	if(orderDate!=null&& orderDate.length()>0)
	    		sql=sql+" and B.CREATE_DTETM='"+orderDate+"'";
	    	
	    	if(deliveryDate!=null&& deliveryDate.length()>0)
	    		sql=sql+" and B.DLVRY_DTE='"+deliveryDate+"'";
	    	
	    	if(vendorNo!=null&& vendorNo.length()>0)
	    		sql=sql+" and B.VENDOR_NO='"+vendorNo+"'";
	    	
	    	sql=sql+ "ORDER BY B.PO_NO";

	    		    List<Booking> results = jdbcTemplate.query(sql, new BookingMapExtractor());
	    		    
	        return results;

	    }
}


final class BookingMapExtractor implements ResultSetExtractor<List<Booking>> {
	@Override
	public List<Booking> extractData(ResultSet rs) throws SQLException {
		List<Booking> bookingList = new ArrayList<Booking>();
		Map<String, List<BookingAudit>> bookingAuditMap = new HashMap<>();
		String oldPo = null;
		Booking bookPo = null;
		while (rs.next()) {
			String poNo = rs.getString("PO_NO");
			BookingAudit bookPoAudit = new BookingAudit();
			
			if(oldPo == null || !oldPo.equals(poNo)) {
				if(oldPo == null) {
					
				} else {
					bookPo.setAuditList(bookingAuditMap.get(oldPo));
					bookingList.add(bookPo);
				}
				bookPo = new Booking();
				
				oldPo = poNo;
				bookPo.setPoNo(poNo);
				//bookPo.setBookingId(rs.getString("BOOKING_ID"));
				bookPo.setDeliveryDate(rs.getString("DLVRY_DTE"));
				bookPo.setDeliveryTime(rs.getString("DLVRY_TIME"));
				bookPo.setDeliveryDock(rs.getString("DLVRY_DOCK"));
				
				bookPo.setDcNo(rs.getString("DC_NO"));
				bookPo.setOrderDate(rs.getString("ORDER_DATE"));
				bookPo.setVendorNo(rs.getString("VENDOR_NO"));
				
				bookPo.setSource(rs.getString("SOURCE"));
				bookPo.setNoOfPallets(rs.getString("PALLET_QTY"));
				bookPo.setStatus(rs.getString("STATUS"));
				
				String auditId = rs.getString("BOOKING_AUDIT_ID"); 
				if(auditId != null) {
					/*
					 * bookPoAudit.setBookingAuditId(auditId); bookPoAudit.setPoNo(poNo);
					 */
					bookPoAudit.setOldDeliveryDate(rs.getString("OLD_DLVRY_DTE"));
					bookPoAudit.setOldDeliveryTime(rs.getString("OLD_DLVRY_TIME"));
					bookPoAudit.setOldDeliveryDock(rs.getString("OLD_DLVRY_DOCK"));
					
					bookPoAudit.setChangedOn(rs.getString("CREATE_DTETM"));
					bookPoAudit.setChangedBy(rs.getString("CREATE_USER_ID"));
					bookPoAudit.setReasonCode(rs.getString("RSN_CODE"));
					
					List<BookingAudit> auditList = new ArrayList<BookingAudit>();
					auditList.add(bookPoAudit);
					bookingAuditMap.put(poNo, auditList);
				}
			} else {
				List<BookingAudit> audits = bookingAuditMap.get(poNo);
				
				/*
				 * bookPoAudit.setBookingAuditId(rs.getString("BOOKING_AUDIT_ID"));
				 * bookPoAudit.setPoNo(poNo);
				 */
				bookPoAudit.setOldDeliveryDate(rs.getString("OLD_DLVRY_DTE"));
				bookPoAudit.setOldDeliveryTime(rs.getString("OLD_DLVRY_TIME"));
				bookPoAudit.setOldDeliveryDock(rs.getString("OLD_DLVRY_DOCK"));
				bookPoAudit.setChangedOn(rs.getString("CREATE_DTETM"));
				bookPoAudit.setChangedBy(rs.getString("CREATE_USER_ID"));
				bookPoAudit.setReasonCode(rs.getString("RSN_CODE"));
				audits.add(bookPoAudit);
				
				bookingAuditMap.put(poNo, audits);
			}
		}
		if(oldPo != null) {
			bookPo.setAuditList(bookingAuditMap.get(oldPo));
			bookingList.add(bookPo);
		}
		return bookingList;
	}
}