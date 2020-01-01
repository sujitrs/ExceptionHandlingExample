package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.*;

import org.springframework.validation.ObjectError;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Util {

	static final String STRT_STRNG_FR_FTCHNG_FLD_NM="field '";
	static final String END_STRNG_FR_FTCHNG_FLD_NM="': rejected";
	static final String STRT_STRNG_FOR_FTCHNG_REJ_VAL="rejected value [";
	static final String END_STRNG_FOR_FTCHNG_REJ_VAL="]";
	static final String STRT_STRNG_FOR_FTCHNG_ERR_MSG="default message [";
	static final String END_STRNG_FOR_FTCHNG_ERR_MSG="]";
	
	
	static String between(String searchString,String a, String b) {
		return searchString.substring(searchString.indexOf(a)+a.length(), searchString.indexOf(b));
	}
	
	static String betweenLast(String searchString,String a, String b) {
		return searchString.substring(searchString.lastIndexOf(a)+a.length(), searchString.lastIndexOf(b));
	}
	
	
	// This is helper function to fetch - field, rejected value and error message from ObjectError created after bean validation
	public static String getError(List <ObjectError> errors) {
		JSONArray ja = new JSONArray();
		Iterator it=errors.iterator();
		log.debug("Found {} errors ",errors.size());
		  while(it.hasNext())
		  {		
			  Object element=it.next(); // Get first element from array
			  String strElement=element.toString(); // Convert fetched element in string for string manipulation 
			  log.debug("Bifurcating attributes from error stream={}",strElement);
			  
			  JSONObject jo = new JSONObject();
			  
			  jo.put("field", between(strElement,STRT_STRNG_FR_FTCHNG_FLD_NM,END_STRNG_FR_FTCHNG_FLD_NM));
			  log.debug("Field {} fetched from error stream=",between(strElement,STRT_STRNG_FR_FTCHNG_FLD_NM,END_STRNG_FR_FTCHNG_FLD_NM),strElement);
			  
			  jo.put("rejectedValue", between(strElement,STRT_STRNG_FOR_FTCHNG_REJ_VAL, END_STRNG_FOR_FTCHNG_REJ_VAL));
			  log.debug("rejectedValue {} fetched from error stream ={}",between(strElement,STRT_STRNG_FOR_FTCHNG_REJ_VAL, END_STRNG_FOR_FTCHNG_REJ_VAL),strElement);
			  
			  jo.put("errorMessage", betweenLast(strElement,STRT_STRNG_FOR_FTCHNG_ERR_MSG,END_STRNG_FOR_FTCHNG_ERR_MSG));
			  log.debug("rejectedValue {} fetched from error stream={}",betweenLast(strElement,STRT_STRNG_FOR_FTCHNG_ERR_MSG,END_STRNG_FOR_FTCHNG_ERR_MSG),strElement);
			  
			  ja.put(jo);
		  }
		  return ja.toString(2); //2 specifies tab spaces to be included in json for readability
	}
	
}
