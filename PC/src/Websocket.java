import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class Websocket implements Callback {

	Timestamp timesql1;
	Timestamp timesql2;
	Timestamp timesql3;
	private String limit;
	private String connet;
	private String strsend="";
	private String strdata;
	private SocketChannel chweb;
	private String websocketfail;
	private ArrayList<String> listarray2;
	private ArrayList<String> listarray3;
	private boolean datawritetype = false;
	private HashMap<String, Socket> websocket;
	private HashMap<String, SocketChannel> websocketlist = null;
	private ArrayList<String> dbdata = new ArrayList<String>();

	public Websocket(String str,java.sql.Statement stmt, HashMap<String, Socket> websocket, ArrayList<String> listarray2,ArrayList<String> listarray3, HashMap<String, SocketChannel> websocketlist, ArrayList<String> dbdata) {
		// TODO Auto-generated constructor stub

        this.strdata = str;
		this.websocket = websocket; 
        this.listarray2 = listarray2;
        this.listarray3 = listarray3;
        this.websocketlist = websocketlist;
        this.dbdata = dbdata;
		
        try {
			
			if(websocketlist==null || websocketlist.isEmpty()){
				
			}
			else
			{	
				if (str.length() == 110) { 
				
				 //校验第一位是否为FA末位是否为F5
	       	     String check1 =str.substring(0,2);
	       	     String check11=str.substring(108,110);
	       	     if(check1.equals("FA") && check11.equals("F5")){
		        		
	       	    	 //校验长度
	           	     int check2=str.length();
	           	     if(check2==110){
	           	        			
	           	    	 //校验位校验
	               	     String check3=str.substring(2,104);
	               	     String check5="";
	               	     int check4=0;
	               	     for (int i11 = 0; i11 < check3.length()/2; i11++)
	               	     {
	               	    	String tstr1=check3.substring(i11*2, i11*2+2);
	               	    	check4+=Integer.valueOf(tstr1,16);
	               	     }
	               	     if((Integer.toHexString(check4)).toUpperCase().length()==2){
	               	    	check5 = ((Integer.toHexString(check4)).toUpperCase());
	               	     }else{
	               	    	check5 = ((Integer.toHexString(check4)).toUpperCase()).substring(1,3);
	               	     }
	               	     String check6 = str.substring(104,106);
	               	     if(check5.equals(check6)){
	             
		               	     strdata=str;
		               	     //String weldname = strdata.substring(10,14);
		       				 int weldname1 = Integer.valueOf(strdata.subSequence(10, 14).toString(),16);
		       				 String weldname = String.valueOf(weldname1);
		       				 if(weldname.length()!=4){
		                       	 int lenth=4-weldname.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 weldname="0"+weldname;
		                       	 }
		                     }
		       				 
		       				 int welder1 = Integer.valueOf(strdata.subSequence(14, 18).toString(),16);
		       				 String welder = String.valueOf(welder1);
		       				 if(welder.length()!=4){
		                       	 int lenth=4-welder.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		welder="0"+welder;
		                       	 }
		                     }
		       				 
		       				 //String code = strdata.substring(18,26);
		       				 long code1 = Integer.valueOf(strdata.subSequence(18, 26).toString(),16);
		                     String code = String.valueOf(code1);
		                     if(code.length()!=8){
		                       	 int lenth=8-code.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 code="0"+code;
		                       	 }
		                     }
		                     
		                     int electricity11 = Integer.valueOf(strdata.subSequence(26,30).toString(),16);
		       				 String electricity1=String.valueOf(electricity11);
 		       				 if(electricity1.length()!=4){
		                       	 int lenth=4-electricity1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		electricity1="0"+electricity1;
		                       	 }
		                     }
		       				
 		       				 int voltage11 = Integer.valueOf(strdata.subSequence(30,34).toString(),16);
		       				 String voltage1=String.valueOf(voltage11);
		       				 if(voltage1.length()!=4){
		                       	 int lenth=4-voltage1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		voltage1="0"+voltage1;
		                       	 }
		                     }
		       				 
		       				 String status1=strdata.substring(38,40);
		       				 
		       				long year1 = Integer.valueOf(str.subSequence(40, 42).toString(),16);
	                        String yearstr1 = String.valueOf(year1);
	                        long month1 = Integer.valueOf(str.subSequence(42, 44).toString(),16);
	                        String monthstr1 = String.valueOf(month1);
	                        if(monthstr1.length()!=2){
	                       	 int lenth=2-monthstr1.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 monthstr1="0"+monthstr1;
	                       	 }
	                        }
	                        long day1 = Integer.valueOf(str.subSequence(44, 46).toString(),16);
	                        String daystr1 = String.valueOf(day1);
	                        if(daystr1.length()!=2){
	                       	 int lenth=2-daystr1.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 daystr1="0"+daystr1;
	                       	 }
	                        }
	                        long hour1 = Integer.valueOf(str.subSequence(46, 48).toString(),16);
	                        String hourstr1 = String.valueOf(hour1);
	                        if(hourstr1.length()!=2){
	                       	 int lenth=2-hourstr1.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 hourstr1="0"+hourstr1;
	                       	 }
	                        }
	                        long minute1 = Integer.valueOf(str.subSequence(48, 50).toString(),16);
	                        String minutestr1 = String.valueOf(minute1);
	                        if(minutestr1.length()!=2){
	                       	 int lenth=2-minutestr1.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 minutestr1="0"+minutestr1;
	                       	 }
	                        }
	                        long second1 = Integer.valueOf(str.subSequence(50, 52).toString(),16);
	                        String secondstr1 = String.valueOf(second1);
	                        if(secondstr1.length()!=2){
	                       	 int lenth=2-secondstr1.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 secondstr1="0"+secondstr1;
	                       	 }
	                        }
	          	    		 
	                        String timestr1 = yearstr1+"-"+monthstr1+"-"+daystr1+" "+hourstr1+":"+minutestr1+":"+secondstr1;
	                        SimpleDateFormat timeshow1 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
	                        try {
	       					
	       					Date time1 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr1);
	                       	//java.util.Date time4 = timeshow3.parse(timestr3);
	       					timesql1 = new Timestamp(time1.getTime());
	       					
	       				 } catch (ParseException e) {
	       					// TODO Auto-generated catch block
	       					e.printStackTrace();
	       				 }
	                        
	       				 
	                        int electricity22 = Integer.valueOf(strdata.subSequence(52,56).toString(),16);
		       				 String electricity2=String.valueOf(electricity22);
		       				 if(electricity2.length()!=4){
		                       	 int lenth=4-electricity2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		electricity2="0"+electricity2;
		                       	 }
		                     }
		       				
		       				 int voltage22 = Integer.valueOf(strdata.subSequence(56,60).toString(),16);
		       				 String voltage2=String.valueOf(voltage22);
		       				 if(voltage2.length()!=4){
		                       	 int lenth=4-voltage2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		voltage2="0"+voltage2;
		                       	 }
		                     }
	       				 String status2=strdata.substring(64,66);
	       				 
	       				 long year2 = Integer.valueOf(str.subSequence(66, 68).toString(),16);
	                        String yearstr2 = String.valueOf(year2);
	                        long month2 = Integer.valueOf(str.subSequence(68, 70).toString(),16);
	                        String monthstr2 = String.valueOf(month2);
	                        if(monthstr2.length()!=2){
	                       	 int lenth=2-monthstr2.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 monthstr2="0"+monthstr2;
	                       	 }
	                        }
	                        long day2 = Integer.valueOf(str.subSequence(70, 72).toString(),16);
	                        String daystr2 = String.valueOf(day2);
	                        if(daystr2.length()!=2){
	                       	 int lenth=2-daystr2.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 daystr2="0"+daystr2;
	                       	 }
	                        }
	                        long hour2 = Integer.valueOf(str.subSequence(72, 74).toString(),16);
	                        String hourstr2 = String.valueOf(hour2);
	                        if(hourstr2.length()!=2){
	                       	 int lenth=2-hourstr2.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 hourstr2="0"+hourstr2;
	                       	 }
	                        }
	                        long minute2 = Integer.valueOf(str.subSequence(74, 76).toString(),16);
	                        String minutestr2 = String.valueOf(minute2);
	                        if(minutestr2.length()!=2){
	                       	 int lenth=2-minutestr2.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 minutestr2="0"+minutestr2;
	                       	 }
	                        }
	                        long second2 = Integer.valueOf(str.subSequence(76, 78).toString(),16);
	                        String secondstr2 = String.valueOf(second2);
	                        if(secondstr2.length()!=2){
	                       	 int lenth=2-secondstr2.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 secondstr2="0"+secondstr2;
	                       	 }
	                        }
	          	    		 
	                        String timestr2 = yearstr2+"-"+monthstr2+"-"+daystr2+" "+hourstr2+":"+minutestr2+":"+secondstr2;
	                        SimpleDateFormat timeshow2 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
	                        try {

	       					Date time2 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr2);
	                       	//java.util.Date time4 = timeshow3.parse(timestr3);
	       					timesql2 = new Timestamp(time2.getTime());
	       					
	       				 } catch (ParseException e) {
	       					// TODO Auto-generated catch block
	       					e.printStackTrace();
	       				 }
		       				 
	       				 
	                        int electricity33 = Integer.valueOf(strdata.subSequence(52,56).toString(),16);
		       				 String electricity3=String.valueOf(electricity33);
		       				 if(electricity3.length()!=4){
		                       	 int lenth=4-electricity3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		electricity3="0"+electricity3;
		                       	 }
		                     }
		       				
		       				 int voltage33 = Integer.valueOf(strdata.subSequence(56,60).toString(),16);
		       				 String voltage3=String.valueOf(voltage33);
		       				 if(voltage3.length()!=4){
		                       	 int lenth=4-voltage3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		voltage3="0"+voltage3;
		                       	 }
		                     }
	       				 String status3=strdata.substring(90,92);
	       				 
	       				 long year3 = Integer.valueOf(str.subSequence(92, 94).toString(),16);
	                        String yearstr3 = String.valueOf(year3);
	                        long month3 = Integer.valueOf(str.subSequence(94, 96).toString(),16);
	                        String monthstr3 = String.valueOf(month3);
	                        if(monthstr3.length()!=2){
	                       	 int lenth=2-monthstr3.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 monthstr3="0"+monthstr3;
	                       	 }
	                        }
	                        long day3 = Integer.valueOf(str.subSequence(96, 98).toString(),16);
	                        String daystr3 = String.valueOf(day3);
	                        if(daystr3.length()!=2){
	                       	 int lenth=2-daystr3.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 daystr3="0"+daystr3;
	                       	 }
	                        }
	                        long hour3 = Integer.valueOf(str.subSequence(98, 100).toString(),16);
	                        String hourstr3 = String.valueOf(hour3);
	                        if(hourstr3.length()!=2){
	                       	 int lenth=2-hourstr3.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 hourstr3="0"+hourstr3;
	                       	 }
	                        }
	                        long minute3 = Integer.valueOf(str.subSequence(100, 102).toString(),16);
	                        String minutestr3 = String.valueOf(minute3);
	                        if(minutestr3.length()!=2){
	                       	 int lenth=2-minutestr3.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 minutestr3="0"+minutestr3;
	                       	 }
	                        }
	                        long second3 = Integer.valueOf(str.subSequence(102, 104).toString(),16);
	                        String secondstr3 = String.valueOf(second3);
	                        if(secondstr3.length()!=2){
	                       	 int lenth=2-secondstr3.length();
	                       	 for(int i=0;i<lenth;i++){
	                       		 secondstr3="0"+secondstr3;
	                       	 }
	                        }
	          	    		 
	                        String timestr3 = yearstr3+"-"+monthstr3+"-"+daystr3+" "+hourstr3+":"+minutestr3+":"+secondstr3;
	                        SimpleDateFormat timeshow3 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
	                        try {
	                       	Date time3 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr3);
	                       	//java.util.Date time4 = timeshow3.parse(timestr3);
	       					timesql3 = new Timestamp(time3.getTime());
	       				 } catch (ParseException e) {
	       					// TODO Auto-generated catch block
	       					e.printStackTrace();
	       				 }
		       				 
                        try{
                        	
	                       	 for(int i=0;i<listarray3.size();i+=5){
	                       		 String weldjunction = listarray3.get(i);
	                       		 if(weldjunction.equals(code)){
	                       			 String maxe = listarray3.get(i+1);
	                       			 String mixe = listarray3.get(i+2);
	                       			 String maxv = listarray3.get(i+3);
	                       			 String mixv = listarray3.get(i+4);
	                       			 limit = maxe + mixe + maxv + mixv;
	                       		 }
	                       	 }
	                       	 
	                       	 String worktime = "";
		                   	 String totaltime = "";
		                   	 String worktime1 = "";
		                   	 String totaltime1 = "";
		                   	 String workhour1,workminute1,worksecond1;
		                   	 String workhour2,workminute2,worksecond2;
		                   	 for(int i=0;i<listarray2.size();i+=3){
		                   		 String fequipment_no = listarray2.get(i);
		                   		 String fgather_no = listarray2.get(i+1);
		                   		 String finsframework_id = listarray2.get(i+2);
	
		                   		 if(weldname.equals(fgather_no)){
		                   			 
		                   			 for(int j=0;j<dbdata.size();j+=3){
		                       			 if(dbdata.get(j).equals(fequipment_no)){
		                       				 if(status1.equals("00")){
		                       					 worktime=Integer.toString(Integer.valueOf(dbdata.get(j+1)));
		                       					 totaltime=Integer.toString(Integer.valueOf(dbdata.get(j+2))+3);
		                       					 dbdata.set(j+2, totaltime);
		                       				 }else{
		                       					 worktime=Integer.toString(Integer.valueOf(dbdata.get(j+1))+3);
		                       					 totaltime=Integer.toString(Integer.valueOf(dbdata.get(j+2))+3);
	
		                       					 dbdata.set(j+1, worktime);
		                       					 dbdata.set(j+2, totaltime);
		                       				 }
		                       				 break;
		                       			 }
		                       		 }
		         
		                   			 long hour = (long)Integer.valueOf(worktime)/3600;
	                 		        if(hour<10){
	                 		        	workhour1 = "0" + String.valueOf(hour) + ":";
	                 		        }else{
	                 		        	workhour1 = String.valueOf(hour) + ":";
	                 		        }
	                 		        long last = (long)Integer.valueOf(worktime)%3600;
	                 		        long minute = last/60;
	                 		        if(minute<10){
	                 		        	workminute1 = "0" + String.valueOf(minute) + ":";
	                 		        }else{
	                 		        	workminute1 = String.valueOf(minute) + ":";
	                 		        }
	                 		        long second = last%60;
	                 		        if(second<10){
	                 		        	worksecond1 = "0" + String.valueOf(second);
	                 		        }else{
	                 		        	worksecond1 = String.valueOf(second);
	                 		        }
	                 		        worktime1 = workhour1 + workminute1 + worksecond1;
	            					
		               		        long hour4 = (long)Integer.valueOf(totaltime)/3600;
		               		        if(hour4<10){
		               		        	workhour2 = "0" + String.valueOf(hour4) + ":";
		               		        }else{
		               		        	workhour2 = String.valueOf(hour4) + ":";
		               		        }
		               		        long last4 = (long)Integer.valueOf(totaltime)%3600;
		               		        long minute4 = last4/60;
		               		        if(minute4<10){
		               		        	workminute2 = "0" + String.valueOf(minute4) + ":";
		               		        }else{
		               		        	workminute2 = String.valueOf(minute4) + ":";
		               		        }
		               		        long second4 = last4%60;
		               		        if(second4<10){
		               		        	worksecond2 = "0" + String.valueOf(second4);
		               		        }else{
		               		        	worksecond2 = String.valueOf(second4);
		               		        }
		               		         totaltime1 = workhour2 + workminute2 + worksecond2;
	
	               		         if(weldname.equals(fgather_no)){
	                       			if(finsframework_id==null || finsframework_id==""){
	                       				finsframework_id="nu";
	                       				strsend+=status1+finsframework_id+fequipment_no+welder+electricity1+voltage1+timesql1+limit+worktime1 + totaltime1
			   	                    			+status2+finsframework_id+fequipment_no+welder+electricity2+voltage2+timesql2+limit+worktime1 + totaltime1
			   	                    			+status3+finsframework_id+fequipment_no+welder+electricity3+voltage3+timesql3+limit+worktime1 + totaltime1;
	                       			}else{
	                       				strsend+=status1+finsframework_id+fequipment_no+welder+electricity1+voltage1+timesql1+limit+worktime1 + totaltime1
			   	                    			+status2+finsframework_id+fequipment_no+welder+electricity2+voltage2+timesql2+limit+worktime1 + totaltime1
			   	                    			+status3+finsframework_id+fequipment_no+welder+electricity3+voltage3+timesql3+limit+worktime1 + totaltime1;
	                       			}	
		   	                     }
	               		         
		   	                     else{
		   	                    	if(finsframework_id==null || finsframework_id==""){
	                       				 finsframework_id="nu";
	                       				strsend+="09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00"
			   	                    			+"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00"
			   	                    			+"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00";
	       	                    	}else{
	       	                    		strsend+="09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00"
			   	                    			+"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00"
			   	                    			+"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"+"00:00:00"+"00:00:00";
	       	                    	}
		   	                     }
		                   	 }
	                       	 
	                       	 /*for(int i=0;i<listarray2.size();i+=3){
	                       		 String fequipment_no = listarray2.get(i);
	                       		 String fgather_no = listarray2.get(i+1);
	                       		 String finsframework_id = listarray2.get(i+2);
	                       		 if(weldname.equals(fgather_no)){
	                       			if(finsframework_id==null || finsframework_id==""){
	                       				 finsframework_id="nu";
		       	                    	 strsend+=status1+finsframework_id+fequipment_no+welder+electricity1+voltage1+timesql1+limit
		       	                    			 +status2+finsframework_id+fequipment_no+welder+electricity2+voltage2+timesql2+limit
		       	                    			 +status3+finsframework_id+fequipment_no+welder+electricity3+voltage3+timesql3+limit;
	                       			}else{
	                       				 strsend+=status1+finsframework_id+fequipment_no+welder+electricity1+voltage1+timesql1+limit
		       	                    			 +status2+finsframework_id+fequipment_no+welder+electricity2+voltage2+timesql2+limit
		       	                    			 +status3+finsframework_id+fequipment_no+welder+electricity3+voltage3+timesql3+limit;
	                       			}	
	       	                     }
	       	                     else{
	       	                    	if(finsframework_id==null || finsframework_id==""){
	                       				 finsframework_id="nu";
	                       				 strsend+="09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
	                       						 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
	                       						 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000";
	       	                    	}else{
	       	                    		 strsend+="09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
	                       						 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
	                       						 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000";
	       	                    	}
	       	                     }*/
	                       		 //System.out.println("2");
	                       	 }

	                       	 //System.out.println(strsend);
                       	 
                        }catch (Exception e) {
       						// TODO Auto-generated catch block
	                       	System.out.println("数据库读取数据错误");
	                        e.printStackTrace();
       					}
           
                        datawritetype = true;
		                     
                        /*if(chweb!=null){
    			        	
    			        	try {
    							chweb.writeAndFlush(new TextWebSocketFrame(strsend)).sync();
    						} catch (InterruptedException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    			        	
    			        }*/
	                        
	                    /*   //数据发送
	                    byte[] bb3=strsend.getBytes();
	                         
	       				ByteBuffer byteBuf = ByteBuffer.allocate(bb3.length);
	       				
	       				for(int j=0;j<bb3.length;j++){
	       					
	       					byteBuf.put(bb3[j]);
	       					
	       				}
	       				
	       				byteBuf.flip();*/
	                        
                        Iterator<Entry<String, SocketChannel>> webiter = websocketlist.entrySet().iterator();
                        while(webiter.hasNext()){
                        	try{
	                        	Entry<String, SocketChannel> entry = (Entry<String, SocketChannel>) webiter.next();
	                        	websocketfail = entry.getKey();
	                        	SocketChannel websocketcon = entry.getValue();
	                        	websocketcon.writeAndFlush(new TextWebSocketFrame(strsend)).sync();
                        	}catch (Exception e) {
	       						if(datawritetype = true){
	       							websocketlist.remove(websocketfail);
	       							webiter = websocketlist.entrySet().iterator();
	       							datawritetype=false;
	       						 }
	       					 }
                        }
                        
                        strsend="";
                        
	       				/*for (Entry<String, SocketChannel> entry : websocketlist.entrySet()) {
	       					//将内容返回给客户端
	       					try{
		       					websocketfail=entry.getKey();
		       					entry.getValue().writeAndFlush(new TextWebSocketFrame(strsend)).sync();
	       					}catch (Exception e) {
	       						if(datawritetype = true){
	       							websocketlist.remove(websocketfail);
	       							datawritetype=false;
	       						 }
	       					 }
	       				 } */
                        
	               	     }	
	               	     else{
	               	    	 //校验位错误
	               	    	 System.out.println("数据接收校验位错误");
	               	    	 str="";
	               	     }
	                               
	           	     }
	           	        		
	           	     else{
	           	    	 //长度错误
	           	    	 System.out.println("数据接收长度错误");
	           	    	 str="";
	           	     }
	       	        		
	   	        	}
	   	        	else{
	   	        		//首位不是FE
	   	        		System.out.println("数据接收首末位错误");
	   	        		str="";
	   	        	}
                     
				 
                
			}else {
        	   
        	   str=""; 
               
		    }}}catch (Exception e) {
			
			if(datawritetype = true){
				
				chweb = null;
				websocket.remove(websocketfail);
				datawritetype=false;
				
			}
		}
        
	}

	@Override
	public void taskResult(String str,String connet,ArrayList<String> listarray1,ArrayList<String> listarray2,ArrayList<String> listarray3,HashMap<String, Socket> websocket,String ip1) {
		// TODO Auto-generated method stub
		this.websocket = websocket; 
        this.strdata = str;
        this.connet = connet;
        this.listarray2 = listarray2;
        this.listarray3 = listarray3;
        
        
        try {
			
			if(str==""){
				
			}
			else
			{	
				
				if (str.length() == 110) { 
				
				 //校验第一位是否为FA末位是否为F5
	       	     String check1 =str.substring(0,2);
	       	     String check11=str.substring(108,110);
	       	     if(check1.equals("FA") && check11.equals("F5")){
		        		
	       	    	 //校验长度
	           	     int check2=str.length();
	           	     if(check2==110){
	           	        			
	           	    	 //校验位校验
	               	     String check3=str.substring(2,104);
	               	     String check5="";
	               	     int check4=0;
	               	     for (int i11 = 0; i11 < check3.length()/2; i11++)
	               	     {
	               	    	String tstr1=check3.substring(i11*2, i11*2+2);
	               	    	check4+=Integer.valueOf(tstr1,16);
	               	     }
	               	     if((Integer.toHexString(check4)).toUpperCase().length()==2){
	               	    	check5 = ((Integer.toHexString(check4)).toUpperCase());
	               	     }else{
	               	    	check5 = ((Integer.toHexString(check4)).toUpperCase()).substring(1,3);
	               	     }
	               	     String check6 = str.substring(104,106);
	               	     if(check5.equals(check6)){
	             
		               	     strdata=str;
		       				 int weldname1 = Integer.valueOf(strdata.subSequence(10, 14).toString(),16);
		       				 String weldname = String.valueOf(weldname1);
		       				 if(weldname.length()!=4){
		                       	 int lenth=4-weldname.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 weldname="0"+weldname;
		                       	 }
		                        }
		       				 String welder=strdata.substring(14,18);
		       				 long code1 = Integer.valueOf(strdata.subSequence(18, 26).toString(),16);
		                        String code = String.valueOf(code1);
		                        if(code.length()!=8){
		                       	 int lenth=8-code.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 code="0"+code;
		                       	 }
		                        }
		       				 String electricity1=strdata.substring(26,30);
		       				 String voltage1=strdata.substring(30,34);
		       				 String status1=strdata.substring(38,40);
		       				 
		       				 long year1 = Integer.valueOf(str.subSequence(40, 42).toString(),16);
		                        String yearstr1 = String.valueOf(year1);
		                        long month1 = Integer.valueOf(str.subSequence(42, 44).toString(),16);
		                        String monthstr1 = String.valueOf(month1);
		                        if(monthstr1.length()!=2){
		                       	 int lenth=2-monthstr1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 monthstr1="0"+monthstr1;
		                       	 }
		                        }
		                        long day1 = Integer.valueOf(str.subSequence(44, 46).toString(),16);
		                        String daystr1 = String.valueOf(day1);
		                        if(daystr1.length()!=2){
		                       	 int lenth=2-daystr1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 daystr1="0"+daystr1;
		                       	 }
		                        }
		                        long hour1 = Integer.valueOf(str.subSequence(46, 48).toString(),16);
		                        String hourstr1 = String.valueOf(hour1);
		                        if(hourstr1.length()!=2){
		                       	 int lenth=2-hourstr1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 hourstr1="0"+hourstr1;
		                       	 }
		                        }
		                        long minute1 = Integer.valueOf(str.subSequence(48, 50).toString(),16);
		                        String minutestr1 = String.valueOf(minute1);
		                        if(minutestr1.length()!=2){
		                       	 int lenth=2-minutestr1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 minutestr1="0"+minutestr1;
		                       	 }
		                        }
		                        long second1 = Integer.valueOf(str.subSequence(50, 52).toString(),16);
		                        String secondstr1 = String.valueOf(second1);
		                        if(secondstr1.length()!=2){
		                       	 int lenth=2-secondstr1.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 secondstr1="0"+secondstr1;
		                       	 }
		                        }
		          	    		 
		                        String timestr1 = yearstr1+"-"+monthstr1+"-"+daystr1+" "+hourstr1+":"+minutestr1+":"+secondstr1;
		                        SimpleDateFormat timeshow1 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		                        try {
		       					
		       					Date time1 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr1);
		                       	//java.util.Date time4 = timeshow3.parse(timestr3);
		       					timesql1 = new Timestamp(time1.getTime());
		       					
		       				 } catch (ParseException e) {
		       					// TODO Auto-generated catch block
		       					e.printStackTrace();
		       				 }
		                        
		       				 
		       				 String electricity2=strdata.substring(52,56);
		       				 String voltage2=strdata.substring(56,60);
		       				 String status2=strdata.substring(64,66);
		       				 
		       				 long year2 = Integer.valueOf(str.subSequence(66, 68).toString(),16);
		                        String yearstr2 = String.valueOf(year2);
		                        long month2 = Integer.valueOf(str.subSequence(68, 70).toString(),16);
		                        String monthstr2 = String.valueOf(month2);
		                        if(monthstr2.length()!=2){
		                       	 int lenth=2-monthstr2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 monthstr2="0"+monthstr2;
		                       	 }
		                        }
		                        long day2 = Integer.valueOf(str.subSequence(70, 72).toString(),16);
		                        String daystr2 = String.valueOf(day2);
		                        if(daystr2.length()!=2){
		                       	 int lenth=2-daystr2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 daystr2="0"+daystr2;
		                       	 }
		                        }
		                        long hour2 = Integer.valueOf(str.subSequence(72, 74).toString(),16);
		                        String hourstr2 = String.valueOf(hour2);
		                        if(hourstr2.length()!=2){
		                       	 int lenth=2-hourstr2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 hourstr2="0"+hourstr2;
		                       	 }
		                        }
		                        long minute2 = Integer.valueOf(str.subSequence(74, 76).toString(),16);
		                        String minutestr2 = String.valueOf(minute2);
		                        if(minutestr2.length()!=2){
		                       	 int lenth=2-minutestr2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 minutestr2="0"+minutestr2;
		                       	 }
		                        }
		                        long second2 = Integer.valueOf(str.subSequence(76, 78).toString(),16);
		                        String secondstr2 = String.valueOf(second2);
		                        if(secondstr2.length()!=2){
		                       	 int lenth=2-secondstr2.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 secondstr2="0"+secondstr2;
		                       	 }
		                        }
		          	    		 
		                        String timestr2 = yearstr2+"-"+monthstr2+"-"+daystr2+" "+hourstr2+":"+minutestr2+":"+secondstr2;
		                        SimpleDateFormat timeshow2 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		                        try {
	
		       					Date time2 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr2);
		                       	//java.util.Date time4 = timeshow3.parse(timestr3);
		       					timesql2 = new Timestamp(time2.getTime());
		       					
		       				 } catch (ParseException e) {
		       					// TODO Auto-generated catch block
		       					e.printStackTrace();
		       				 }
		       				 
		       				 
		       				 String electricity3=strdata.substring(78,82);
		       				 String voltage3=strdata.substring(82,86);
		       				 String status3=strdata.substring(90,92);
		       				 
		       				 long year3 = Integer.valueOf(str.subSequence(92, 94).toString(),16);
		                        String yearstr3 = String.valueOf(year3);
		                        long month3 = Integer.valueOf(str.subSequence(94, 96).toString(),16);
		                        String monthstr3 = String.valueOf(month3);
		                        if(monthstr3.length()!=2){
		                       	 int lenth=2-monthstr3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 monthstr3="0"+monthstr3;
		                       	 }
		                        }
		                        long day3 = Integer.valueOf(str.subSequence(96, 98).toString(),16);
		                        String daystr3 = String.valueOf(day3);
		                        if(daystr3.length()!=2){
		                       	 int lenth=2-daystr3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 daystr3="0"+daystr3;
		                       	 }
		                        }
		                        long hour3 = Integer.valueOf(str.subSequence(98, 100).toString(),16);
		                        String hourstr3 = String.valueOf(hour3);
		                        if(hourstr3.length()!=2){
		                       	 int lenth=2-hourstr3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 hourstr3="0"+hourstr3;
		                       	 }
		                        }
		                        long minute3 = Integer.valueOf(str.subSequence(100, 102).toString(),16);
		                        String minutestr3 = String.valueOf(minute3);
		                        if(minutestr3.length()!=2){
		                       	 int lenth=2-minutestr3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 minutestr3="0"+minutestr3;
		                       	 }
		                        }
		                        long second3 = Integer.valueOf(str.subSequence(102, 104).toString(),16);
		                        String secondstr3 = String.valueOf(second3);
		                        if(secondstr3.length()!=2){
		                       	 int lenth=2-secondstr3.length();
		                       	 for(int i=0;i<lenth;i++){
		                       		 secondstr3="0"+secondstr3;
		                       	 }
		                        }
		          	    		 
		                        String timestr3 = yearstr3+"-"+monthstr3+"-"+daystr3+" "+hourstr3+":"+minutestr3+":"+secondstr3;
		                        SimpleDateFormat timeshow3 = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		                        try {
		                       	Date time3 = DateTools.parse("yy-MM-dd HH:mm:ss",timestr3);
		                       	//java.util.Date time4 = timeshow3.parse(timestr3);
		       					timesql3 = new Timestamp(time3.getTime());
		       				 } catch (ParseException e) {
		       					// TODO Auto-generated catch block
		       					e.printStackTrace();
		       				 }
		       				 
		                        try{
		                       	 
		                       	 for(int i=0;i<listarray3.size();i+=5){
		                       		 String weldjunction = listarray3.get(i);
		                       		 if(weldjunction.equals(code)){
		                       			 String maxe = listarray3.get(i+1);
		                       			 String mixe = listarray3.get(i+2);
		                       			 String maxv = listarray3.get(i+3);
		                       			 String mixv = listarray3.get(i+4);
		                       			 limit = maxe + mixe + maxv + mixv;
		                       			 
		                       		 }
		                       	 }
		                       	 
		                       	 for(int i=0;i<listarray2.size();i+=3){
		                       		 String fequipment_no = listarray2.get(i);
		                       		 String fgather_no = listarray2.get(i+1);
		                       		 String finsframework_id = listarray2.get(i+2);
		                       		 if(weldname.equals(fgather_no)){
		                       			 if(finsframework_id.isEmpty() || finsframework_id==null || finsframework_id==""){
		                       				finsframework_id="nu";
		                       				strsend+=status1+finsframework_id+fequipment_no+welder+electricity1+voltage1+timesql1+limit
			       	                    			 +status2+finsframework_id+fequipment_no+welder+electricity2+voltage2+timesql2+limit
			       	                    			 +status3+finsframework_id+fequipment_no+welder+electricity3+voltage3+timesql3+limit;
		                       			 }
		       	                     }
		       	                     else{
		       	                    	 if(finsframework_id.isEmpty() || finsframework_id==null || finsframework_id==""){
		       	                    		finsframework_id="nu";
		       	                    		strsend+="09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
			       	                    			 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000"
			       	                    			 +"09"+finsframework_id+fequipment_no+"0000"+"0000"+"0000"+"000000000000000000000"+"000000000000";
		       	                    	 }
		       	                     }
		                       	 }
	
		                        }catch (Exception e) {
		       						// TODO Auto-generated catch block
		                       	 System.out.println("数据库读取数据错误");
		                       	 e.printStackTrace();
		       					 }
		           
		                        datawritetype = true;
		                        
		                        if(chweb!=null){
		    			        	
		    			        	try {
		    							chweb.writeAndFlush(new TextWebSocketFrame(str)).sync();
		    						} catch (InterruptedException e) {
		    							// TODO Auto-generated catch block
		    							e.printStackTrace();
		    						}
		    			        	
		    			        }
		                        
		                     /*  //数据发送
		                       byte[] bb3=strsend.getBytes();
		                         
		       				ByteBuffer byteBuf = ByteBuffer.allocate(bb3.length);
		       				
		       				for(int j=0;j<bb3.length;j++){
		       					
		       					byteBuf.put(bb3[j]);
		       					
		       				}
		       				
		       				byteBuf.flip();
		       				
		       				strsend="";
		       				
		       				for (Entry<String, Socket> entry : websocket.entrySet()) {
		       					//将内容返回给客户端
		       					websocketfail=entry.getKey();
		       	                responseClient(byteBuf, true, entry.getValue());
		       				} */
	               	    	 
	               	     }	
	               	     else{
	               	    	 //校验位错误
	               	    	 System.out.println("数据接收校验位错误");
	               	    	 str="";
	               	     }
	                               
	           	     }
	           	        		
	           	     else{
	           	    	 //长度错误
	           	    	 System.out.println("数据接收长度错误");
	           	    	 str="";
	           	     }
	       	        		
	   	        	}
	   	        	else{
	   	        		//首位不是FE
	   	        		System.out.println("数据接收首末位错误");
	   	        		str="";
	   	        	}
                     
				 
                
			}else {
        	   
        	   str=""; 
               
		    }}}catch (Exception e) {
			
			if(datawritetype = true){
				
				websocket.remove(websocketfail);
				datawritetype=false;
				
			}
		}

	}
        
        public void responseClient(ByteBuffer byteBuf, boolean finalFragment,Socket socket) throws IOException {  
	        
	    	OutputStream out = socket.getOutputStream();  
	        int first = 0x00;  
	        
	        	//是否是输出最后的WebSocket响应片段 
	            if (finalFragment) {  
	                first = first + 0x80;  
	                first = first + 0x1;  
	            }  
	            
	            out.write(first); 
	            
	            if (byteBuf.limit() < 126) {  
	                out.write(byteBuf.limit());  
	            } else if (byteBuf.limit() < 65536) {  
		            out.write(126);  
		            out.write(byteBuf.limit() >>> 8);  
		            out.write(byteBuf.limit() & 0xFF);  
	            } else {  
	            // Will never be more than 2^31-1  
		            out.write(127);  
		            out.write(0);  
		            out.write(0);  
		            out.write(0);  
		            out.write(0);  
		            out.write(byteBuf.limit() >>> 24);  
		            out.write(byteBuf.limit() >>> 16);  
		            out.write(byteBuf.limit() >>> 8);  
		            out.write(byteBuf.limit() & 0xFF);  
	            }  
	            // Write the content  
	            out.write(byteBuf.array(), 0, byteBuf.limit());  
	            out.flush();  
	    }  

}
