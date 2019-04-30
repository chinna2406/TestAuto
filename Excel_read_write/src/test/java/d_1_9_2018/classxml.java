package d_1_9_2018;

//import ipm.IPMcomm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




public class classxml {
	String timeStamp;
	String dateStamp;
	NodeList nodes;
public HashMap<String,String> updateInstallLocationTSOXml(String inputFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException, ClassNotFoundException, SQLException{
		
	
		
		//String EnterPriseID = IPMcomm.importParam("EnterPriseID");
		//String TrunkID = IPMcomm.importParam("TrunkID");
		
		NodeList nodes,nodes1;					
		
		String[] parts = inputFile.split(".xml");
		String outputFile = parts[0]+"_New.xml";
		
		Random rndNum= new Random();  
		
		File f = new File("../VOIPCommon/XML/"+inputFile);
		File fo = new File("../VOIPCommon/XML/"+outputFile);
        System.out.println("path: "+f.getPath());
        System.out.println("abs path: "+f.getAbsolutePath());
        System.out.println("can path: "+f.getCanonicalPath());
        String inputFilepath=f.getCanonicalPath();
		String outputFilepath=fo.getCanonicalPath();
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputFilepath));
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		HashMap<String, String> mapObj = new HashMap<String, String>();
		

		
		nodes = (NodeList)xpath.evaluate("//*[@schemeName='InstanceID']", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		String Inst = String.format("%05d", rndNum.nextInt(100000));
		char c=Inst.charAt(0);
		 if(String.valueOf(c).equalsIgnoreCase("0")){
			 Inst=Inst.replace("0", "6");
		 }
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			if(!value.equals("0")){
				value = Inst  + value.substring(5); 
				//value = "9" + timeStamp + value.substring(timeStamp.length()); 
				//System.out.println("Change # 1 - "+idx+" "+value);
				nodes.item(idx).setTextContent(value);
			}
		}
		}

	   
			
	/*	nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_IDS']", doc, XPathConstants.NODESET);

		//System.out.println("Change # 1 - "+idx+" "+value);
		if (nodes.getLength()!=0)
	 {nodes.item(1).setTextContent("88115725");}
		
		
		
		
		nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_PIP_SVC']", doc, XPathConstants.NODESET);

					//System.out.println("Change # 1 - "+idx+" "+value);
		if (nodes.getLength()!=0)
		{	nodes.item(1).setTextContent("88115725");} */
				
				
				nodes = (NodeList)xpath.evaluate("//*[@schemeName='EnterpriseID']", doc, XPathConstants.NODESET);
				if(nodes.getLength()!=0){
				for (int idx = 0; idx < nodes.getLength(); idx++) {			
						//System.out.println("Change # 1 - "+idx+" "+value);
						nodes.item(idx).setTextContent(EnterPriseID);
				}
				}
			 
				
				nodes = (NodeList)xpath.evaluate("//*[text()='ESP_ENTERPRISE_ID']//following-sibling::Value", doc, XPathConstants.NODESET);				
				if(nodes.getLength()!=0){
				for (int idx = 0; idx < nodes.getLength(); idx++) {
						nodes.item(idx).setTextContent(EnterPriseID);				
				}
				}
				
	
		
		
		nodes = (NodeList)xpath.evaluate("//*[@schemeName='WorkOrderNumber']", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeID").getNodeValue();
			String id = String.format("%07d", rndNum.nextInt(10000000));
			value = "9" + id ;
			//System.out.println("Change # 2 - "+schemeID+" "+value);
			mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(value);
			
			nodes = (NodeList)xpath.evaluate("//*[text()='ESP_VOIP_LOCATION_NAME']//following-sibling::Value", doc, XPathConstants.NODESET);		
					
			nodes.item(0).setTextContent("LOC"+value);	
			IPMcomm.exportParam("WorkOrderNumber",value);
		}
		}
				  
		  
		
	/*	nodes = (NodeList)xpath.evaluate("//*[@schemeName='RelatedWorkOrderNumber']", doc, XPathConstants.NODESET);

		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeID").getNodeValue();
			value = "9" + timeStamp + value.substring(timeStamp.length());
			//System.out.println("Change # 3 - "+schemeID+" "+value);
			mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(value);
		} */

		nodes = (NodeList)xpath.evaluate("//*[contains(@schemeName,'UNOServiceOrder')]", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%07d", rndNum.nextInt(10000000));
			value = "8" + id ;
			//System.out.println("Change # 5 - "+schemeID+" "+value);
			mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(value);
			IPMcomm.exportParam("ServiceOrder",value);
			IPMcomm.logMessage(IPMcomm.TEST_CASE, IPMcomm.PASSED, "Service Order :" + value, "Service Order");
			
		}
		}

/*		nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='SP_VOIP_TELE_NUM']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			System.out.println(nodes.getLength());
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%04d", rndNum.nextInt(10000));
			value = value.substring(1,6) + id ;				
			//System.out.println("Change # 5 - "+schemeID+" "+value);
	//		mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(value);
			
			nodes1 = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_TSP_APPROVAL_CODE']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);	
			
			System.out.println(value);
			nodes1.item(idx).setTextContent("T"+value);
		} */
	

		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_VOICEMAIL_ACCESS_NUM_PRIVATE']//following-sibling::Value", doc, XPathConstants.NODESET);			
		if (nodes.getLength()!=0){
		nodes.item(0).setTextContent(String.format("%04d", rndNum.nextInt(10000)));
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			System.out.println("nodes.getLength()"+nodes.getLength());
			System.out.println("value"+value);
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%05d", rndNum.nextInt(1000000));
			System.out.println("value"+value);
			if(value.length()==6)
			{value = value.substring(0,1) + id ;}
			else
			{value = value.substring(0,2) + id ;}
			nodes.item(idx).setTextContent(value);
						}
		}
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_VOICEMAIL_ACCESS_NUM_PUBLIC']//following-sibling::Value", doc, XPathConstants.NODESET);				
		if (nodes.getLength()!=0){
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			System.out.println("nodes.getLength(): "+nodes.getLength());
			System.out.println("value"+value);
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%04d", rndNum.nextInt(10000));
		
			System.out.println("value"+value);
			if(value.length()==10)
			{value = value.substring(0,6) + id ;}
			else
			{value = value.substring(0,5) + id ;}
			nodes.item(idx).setTextContent(value);
						}
		}
	
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_DEVICE_SCREENED_TN']//following-sibling::Value", doc, XPathConstants.NODESET);				
		if (nodes.getLength()!=0){
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			System.out.println("nodes.getLength()"+nodes.getLength());
			System.out.println("value"+value);
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%04d", rndNum.nextInt(10000));
		
			System.out.println("value"+value);
			if(value.length()==10)
			{value = value.substring(0,6) + id ;}
			else
			{value = value.substring(0,5) + id ;}
			nodes.item(idx).setTextContent(value);
						}
		}
		
		 String contryCode="";
		 nodes = (NodeList)xpath.evaluate("//*[text()='ESP_DIALING_COUNTRY_CODE']//following-sibling::Value", doc, XPathConstants.NODESET);
		 if(nodes.getLength()!=0){
			contryCode = nodes.item(0).getTextContent();	 
		 }
		 nodes = (NodeList)xpath.evaluate("//*[text()='ESP_CALLER_ID_NUMBER']//following-sibling::Value", doc, XPathConstants.NODESET);
			if(nodes.getLength()!=0){
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				String value = nodes.item(idx).getTextContent();
				System.out.println("Existing ESP_CALLER_ID_NUMBER: "+value);
				String id = String.format("%05d", rndNum.nextInt(10000));
				String contryCodeTn=contryCode+value;
				System.out.println("Existing ConCode and TN "+contryCodeTn);
				String newCCODE="";
				int TnLength=contryCodeTn.length()-contryCode.length();
				TnLength=TnLength-5;
				value = value.substring(0,TnLength) + id ;
				newCCODE=contryCode+value;
			
					System.out.println("ESP_CALLER_ID_NUMBER CountryCode and TN :"+contryCode+"-"+value);
					nodes.item(idx).setTextContent(value);
				
				nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_EMERGENCY_CALLING_LINE']//following-sibling::Value", doc, XPathConstants.NODESET);	
				if(nodes1.getLength()!=0){nodes1.item(idx).setTextContent(value);}
				
			}
			}
		nodes = (NodeList)xpath.evaluate("//*[text()='SP_VOIP_TELE_NUM']//following-sibling::Value", doc, XPathConstants.NODESET);				
		if(nodes.getLength()!=0){
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
			System.out.println("No TN: "+nodes.getLength()+" EXISTING TN: "+value);		
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%05d", rndNum.nextInt(10000));
			String contryCodeTn=contryCode+value;
			String newCCODE=null;
			int TnLength=contryCodeTn.length()-contryCode.length();
			TnLength=TnLength-5;
			value = value.substring(0,TnLength) + id ;
			newCCODE=contryCode+value;
		
				System.out.println("CountryCode and TN :"+contryCode+"-"+value);
				nodes.item(idx).setTextContent(value);		
			nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_TSP_APPROVAL_CODE']//following-sibling::Value", doc, XPathConstants.NODESET);	
			if(nodes1.getLength()!=0){nodes1.item(idx).setTextContent("T"+value);System.out.println("ESP_TSP_APPROVAL_CODE NO: "+value);}
			if(idx==0){
				nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_LSR_BTN']//following-sibling::Value", doc, XPathConstants.NODESET);	
				if(nodes1.getLength()!=0){nodes1.item(0).setTextContent(value);System.out.println("ESP_LSR_BTN: "+value);}
	
				nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_CALLER_ID_NUMBER']//following-sibling::Value", doc, XPathConstants.NODESET);
				if(nodes1 .getLength()!=0){nodes1.item(0).setTextContent(value);System.out.println("ESP_CALLER_ID_NUMBER: "+value);}
			}
			if(idx==1){
				nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_LOCATION_SCREENED_TN']//following-sibling::Value", doc, XPathConstants.NODESET);	
				if(nodes1.getLength()!=0){nodes1.item(0).setTextContent(value);System.out.println("ESP_LOCATION_SCREENED_TN: "+value);}
			}			
		}	
	}
		 
		 nodes = (NodeList)xpath.evaluate("//*[text()='SP_VOIP_NUM_ENTER_LOC_CD']//following-sibling::Value", doc, XPathConstants.NODESET);
		 if (nodes.getLength()!=0){
			 String id = String.format("%01d", rndNum.nextInt(10));
			 for (int idx = 0; idx < nodes.getLength(); idx++) {
			 nodes.item(idx).setTextContent(id);}
		 }
		 
		 /*	nodes = (NodeList)xpath.evaluate("//*[text()='ESP_VOICEMAIL_ACCESS_NUM_PUBLIC']//following-sibling::Value", doc, XPathConstants.NODESET);				
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			String value = nodes.item(idx).getTextContent();
		//	String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			String id = String.format("%04d", rndNum.nextInt(10000));
			value = value.substring(0,6) + id ;				
			//System.out.println("Change # 5 - "+schemeID+" "+value);
		//	mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(value);		
			
		}
		
	
		String value = "9" + timeStamp + String.format("%02d", rndNum.nextInt(100));
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_TRUNK_GROUP_INSTANCE_ID']//following-sibling::Value", doc, XPathConstants.NODESET);				
		for (int idx = 0; idx < nodes.getLength(); idx++) {
		
			nodes.item(idx).setTextContent(value);	
			
		} */
		
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_ENT_TRUNK_INST_ID']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0){nodes.item(0).setTextContent(TrunkID);}
		
		
		System.out.println("ESP_ENT_TRUNK_INST_ID");
		
		
		String RelatedServiceInstanceID= IPMcomm.importParam("PR_IDS_AND_PR_PIP_SVC");
		String PR_IDS_PR_PIP_SVC[]= RelatedServiceInstanceID.split(",");
		
		nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_PIP_SVC']", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				nodes.item(idx).setTextContent(PR_IDS_PR_PIP_SVC[idx]);
			}
		}
		//if (0 < nodes.getLength())
		//{//nodes.item(0).setTextContent("5724997");
			//nodes.item(0).setTextContent("100421989");
		//}
		//}
		nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_IDS']", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			System.out.println("Lenth: "+nodes.getLength());
		for (int idx = 0; idx < nodes.getLength(); idx++) {	nodes.item(idx).setTextContent(PR_IDS_PR_PIP_SVC[idx]);}	
		}
		//if (0 < nodes.getLength())
		//{
			//nodes.item(0).setTextContent("5727430");
			//nodes.item(0).setTextContent("100421989");
			//}
		String Ins= String.format("%08d", rndNum.nextInt(100000000));
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_TRUNK_GROUP_INSTANCE_ID']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if(nodes.getLength()!=0){
			for (int idx = 0; idx < nodes.getLength(); idx++) {nodes.item(idx).setTextContent(Ins);	}
			}	

		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_LOGICAL_DEVICE_NAME']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)				
		{	System.out.println("Dev"+Ins);
			nodes.item(0).setTextContent("Dev"+Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_CPE_DEVICE_ID']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent(Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_TRUNK_GROUP_NAME']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent("PBX"+Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_GATEWAY_DEVICE_ID']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent(Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='FET_DE']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent(Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_DEVICE_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent(Ins);}
		
		nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_TRUNK_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{nodes.item(0).setTextContent(Ins);}	
			
	/*	nodes = (NodeList)xpath.evaluate("//*[contains(@schemeName,'EnterpriseID')]", doc, XPathConstants.NODESET);
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			 System.out.println(nodes.getLength());
			//String value1 = nodes.item(idx).getTextContent();
			//String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
			//value = "9" + timeStamp + value.substring(timeStamp.length());
			//System.out.println("Change # 5 - "+schemeID+" "+value);
			//mapObj.put(schemeID, value);
			nodes.item(idx).setTextContent(TrunkID);
		} */
		
	/*	nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='CustDesiredDueDate']][local-name()='Date'][not(@*)]", doc, XPathConstants.NODESET);

		for (int idx = 0; idx < nodes.getLength(); idx++) {
			//String value = nodes.item(idx).getNodeName();
			//System.out.println("Change # 4 - "+idx+" "+dateStamp);
			nodes.item(idx).setTextContent(dateStamp);
		}
		
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENTERPRISE_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);

		//for (int idx = 0; idx < nodes.getLength(); idx++) {
			//String value = nodes.item(idx).getNodeName();
			//System.out.println("Change # 4 - "+idx+" "+dateStamp);
			 System.out.println(nodes.getLength());
			String value1 = null;	
			// System.out.println(value);
			value1 = "9" + timeStamp;
			String ENTERPRISE_NAME="ENT" + value1;
			nodes.item(0).setTextContent(ENTERPRISE_NAME);
		//}
		
	/*		nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_EMAIL']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "9" + timeStamp + "@GMAIL.COM";			
				nodes.item(0).setTextContent(value1);
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_FST_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);	
				value1 = "9" + timeStamp + "SENTHIL";			
				nodes.item(0).setTextContent(value1);
				
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_LST_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);			
				value1 = "9" + timeStamp + "RAJAN";			
				nodes.item(0).setTextContent(value1);
				
				
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_PW']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);
				value1 = "9" + timeStamp + "PWD";			
				nodes.item(0).setTextContent(value1);
				
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_LOGIN_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);
				value1 = "9" + timeStamp + "SENTHIL";			
				nodes.item(0).setTextContent(value1);
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_SIP_DOMAIN']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "9" + timeStamp + ".test02.kiehl.org";							
				nodes.item(0).setTextContent(value1);		
				
		/*	if ( parts[0].contains("_TSO_ENT") )	
			{
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENTERPRISE_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "9" + timeStamp ;							
				nodes.item(0).setTextContent(value1);	
				
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_ENT_TRUNK_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "9" + timeStamp ;							
				nodes.item(0).setTextContent(value1);
				
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENT_TRUNK_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "ET" + timeStamp ;							
				nodes.item(0).setTextContent(value1);
				
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_EBI_IP']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "9" + timeStamp ;
				value1 = value1.substring(0,1) +"."+value1.substring(2,3) +"."+value1.substring(4,5) +"."+value1.substring(6,7) +"." ;
				nodes.item(0).setTextContent(value1);
												
				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_EBI_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				value1 = "EBI" + timeStamp ;
				nodes.item(0).setTextContent(value1);
				
			}
				*/
		
		Transformer xformer = TransformerFactory.newInstance().newTransformer();
		xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFilepath)));
		
		mapObj.put("outputFilepath", outputFilepath);
		
		System.out.print(outputFilepath);
		
		
		
		return mapObj;

	}
//Function for Adding TNs in Existing xml file
public HashMap<String,String> changeOrderAddingTNsXml(String inputFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException{
				String[] parts = inputFile.split(".xml");
				System.out.println("Input File name:"+ inputFile);
				String outputFile = parts[0]+"_AddTn.xml";
				
				Random rndNum= new Random(); 
				File f = new File("../VOIPCommon/XML/"+inputFile);
				File fo = new File("../VOIPCommon/XML/"+outputFile);
		        System.out.println("path: "+f.getPath());
		        System.out.println("abs path: "+f.getAbsolutePath());
		        System.out.println("can path: "+f.getCanonicalPath());
		        String inputFilepath=f.getCanonicalPath();
				String outputFilepath=fo.getCanonicalPath();
				
				HashMap<String, String> mapObj = new HashMap<String, String>();	
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				docFactory.setValidating(true);
				docFactory.setExpandEntityReferences(false);
				docFactory.setNamespaceAware(true);
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(inputFilepath);
				XPath xpath = XPathFactory.newInstance().newXPath();
				
				doc.getDocumentElement().normalize();
				System.out.println(doc.getDocumentElement());
				Random ran = new Random();
				NodeList list = null;
				Node staff = null;
				String value=null;
				System.out.println(doc.getElementsByTagName("ServiceCapability").getLength());
				
				String expression = "//*[text()='EFET_VOIP_TN_LVL']/..";
				XPathFactory xPathfactory = XPathFactory.newInstance();
				xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile(expression);
				NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
				System.out.println("Node Length is:"+nl.getLength());
				
				staff = nl.item(0);
				Node k =staff.getParentNode();
				Node n =staff.cloneNode(true);

				NodeList nList = doc.getElementsByTagName("RevisionID");
		        for (int idx = 0; idx < nList.getLength(); idx++) {
					 value = nList.item(idx).getTextContent();
					nList.item(idx).setTextContent("1");
				}
		      
				nodes = (NodeList)xpath.evaluate("//*[@listName='CHANGE']", doc, XPathConstants.NODESET);
				
				for (int idx = 0; idx < nodes.getLength(); idx++) {
							//System.out.println("Change # 2 - "+schemeID+" "+value);
				
					nodes.item(idx).setTextContent("SUPP");			
								
				}
		        
		        NodeList nList2 = doc.getElementsByTagName("ActionCode");
		        Node nList3=  nList2.item(0);
			     
		        Element eElement = (Element) nList3;         
		        eElement.setAttribute("name","S3");
			   		
				//Change the Instance ID
				Element ele = (Element) n;
				System.out.println("ele Length is:"+ele.getElementsByTagName("ID").getLength());
				Node idNode = ele.getElementsByTagName("ID").item(0);
				
				String id=String.format("%09d",ran.nextInt(100000000));
				value="3"+id;
				System.out.println("Instance Id:"+value);
				idNode.setTextContent(value);
				
				//Change Telephone number
				
				ele = (Element) n;
				System.out.println("ele Length is:"+ele.getElementsByTagName("Value").getLength());
				idNode = ele.getElementsByTagName("Value").item(0);
				String tpNumber=String.format("%09d",ran.nextInt(100000000));
				value="9"+tpNumber;
				idNode.setTextContent(value);
				
				//Adding Tn
				
				  ele=(Element)n;
				  System.out.println("ele Length is:"+ele.getElementsByTagName("ActionCode").getLength());
				  idNode = ele.getElementsByTagName("ActionCode").item(0);
				  value = idNode.getTextContent();
					if(value.equals("NONE")){
						idNode.setTextContent("ADD");
					}
				
				
				//Change Ported Indicator Value
				ele = (Element) n;
				System.out.println("ele Length is:"+ele.getElementsByTagName("Value").getLength());
				idNode = ele.getElementsByTagName("Value").item(1);
				String text=idNode.getTextContent();
				if(text.equals("Yes"))
				{
				idNode.setTextContent("No");
				System.out.println("Added Native Tn");
				
				}
				else if(text.equals("No"))
				{
					idNode.setTextContent("No");
					System.out.println("Added Ported Tn");
				}
				
				//Copy Existing Trunk group id
				ele = (Element) n;
				System.out.println("ele Length is:"+ele.getElementsByTagName("Value").getLength());
				idNode = ele.getElementsByTagName("Value").item(9);
				text=idNode.getTextContent();
				idNode.setTextContent(text);
				System.out.println("Added Trunk group id:"+text);
				k.appendChild(n);
				System.out.println(doc.getElementsByTagName("ServiceCapability").getLength());
				DOMSource source = new DOMSource(doc);
			    FileWriter writer = new FileWriter(new File(outputFilepath));
			    StreamResult result = new StreamResult(writer);

			    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    transformer.transform(source, result);
			
				
				Transformer xformer = TransformerFactory.newInstance().newTransformer();
				xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFilepath)));
				
				mapObj.put("outputFilepath", outputFilepath);

				return mapObj;
			}

public HashMap<String,String> updateChangeOrderPushXml(String inputFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException, ClassNotFoundException, SQLException{
	//log.info("updateChangeOrderPushXml is involved...");
	//Statement stat=DBValidFunction.connectEsapDB("esap","etomesap","ESAPETOM");
	NodeList nodes1;
	String[] parts = inputFile.split(".xml");
	String outputFile = parts[0]+"_Change.xml";
	Random rndNum= new Random(); 
	String changeType="Changed";// IPMcomm.importParam("ChangeType");
	File f = new File("../VOIPCommon/XML/"+inputFile);
	File fo = new File("../VOIPCommon/XML/"+outputFile);
    System.out.println("path: "+f.getPath());
    System.out.println("abs path: "+f.getAbsolutePath());
    System.out.println("can path: "+f.getCanonicalPath());
    String inputFilepath=f.getCanonicalPath();
	String outputFilepath=fo.getCanonicalPath();
	String id;
	String ChangeTypeValue=IPMcomm.importParam("ChangeTypeValue");
	ChangeTypeValue="SP_CUST_CPE_SGNLG_IP_ADDR";
	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputFilepath));
	XPath xpath = XPathFactory.newInstance().newXPath();
	
	HashMap<String, String> mapObj = new HashMap<String, String>();		

	System.out.println(changeType+"Change....");
	nodes = (NodeList)xpath.evaluate("//ActionCode", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		if(!value.equals("0")){
				nodes.item(idx).setTextContent("NONE");
		}
	}
			
	nodes = (NodeList)xpath.evaluate("//*[@listName='INSTALL']", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		nodes.item(idx).setTextContent("CHANGE");
	}

	nodes = (NodeList)xpath.evaluate("//*[@listName='INSTALL']", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		org.w3c.dom.Node value = nodes.item(idx).getAttributes().getNamedItem("listName");
	    String val = value.getNodeValue();
	    System.out.print(val);
	    value.setNodeValue(val.replaceAll("INSTALL", "CHANGE"));   
	}

	nodes = (NodeList)xpath.evaluate("//*[@schemeName='WorkOrderNumber']", doc, XPathConstants.NODESET);

	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeID").getNodeValue();
		id = String.format("%07d", rndNum.nextInt(10000000));
		value = "9" + id ;
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		IPMcomm.exportParam("WorkOrderNumber",value);
	}

	nodes = (NodeList)xpath.evaluate("//*[contains(@schemeName,'ServiceOrder')]", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
		id = String.format("%07d", rndNum.nextInt(10000000));
		value = "8" + id ;
		if (schemeID.equals("UNOServiceOrder")){
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		IPMcomm.exportParam("ServiceOrder",value);
		IPMcomm.logMessage(IPMcomm.TEST_CASE, IPMcomm.PASSED, "Service Order :" + value, "Service Order");
	} else{
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		System.out.println(schemeID+" *********ESAP**"+value);
	}
	}
	// Delete the IPO
	if(changeType.contains("Removed")){
		
	nodes = (NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
	if (nodes.getLength()!=0)	
	{String FEATURE_CODE=nodes.item(0).getTextContent();
	IPMcomm.exportParam("FET_INS_ID", FEATURE_CODE);
	IPMcomm.exportParam("FEATURE_CODE", "FET_IPCC_IP_TERM_LVL");
	}
	nodes = (NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']//following-sibling::ActionCode", doc, XPathConstants.NODESET);
	if(nodes.getLength()!=0){
		nodes.item(0).setTextContent("REMOVE");
		System.out.println("DELETED...");}
	
	nodes = (NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
	if (nodes.getLength()!=0)	
	{String FEATURE_CODE=nodes.item(0).getTextContent();
	IPMcomm.exportParam("FET_INS_ID", FEATURE_CODE);
	IPMcomm.exportParam("FEATURE_CODE", "FET_IPCC_IP_TERM_LVL");
	}
	nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_TN_LVL']//following-sibling::ActionCode", doc, XPathConstants.NODESET);
	if(nodes.getLength()!=0){
		nodes.item(0).setTextContent("REMOVE");
		System.out.println("DELETED...");}

	}else if(changeType.contains("Changed")){
		nodes = (NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
		if (nodes.getLength()!=0)	
		{String FEATURE_CODE=nodes.item(0).getTextContent();
		IPMcomm.exportParam("FET_INS_ID", FEATURE_CODE);
		IPMcomm.exportParam("FEATURE_CODE", "FET_IPCC_IP_TERM_LVL");
		}

		nodes = (NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']//following-sibling::ActionCode", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			nodes.item(0).setTextContent("CHANGE");
			System.out.println("CHANGED...");
		}
		
		// Modify the IP Address
		if(ChangeTypeValue.contains("SP_CUST_CPE_SGNLG_IP_ADDR")){
		nodes = (NodeList)xpath.evaluate("//*[text()='SP_CUST_CPE_SGNLG_IP_ADDR']//following-sibling::Value", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			id = String.format("%02d", rndNum.nextInt(100));
			if(id.charAt(0)=='0')
				id="18";
			String value=nodes.item(0).getTextContent();
			IPMcomm.exportParam("ORIGINAL_VALUE", value);
			value=value.substring(id.length());
			value=id+value;
			IPMcomm.exportParam("NEW_VALUE", value);
			nodes.item(0).setTextContent(value);
			System.out.println("SP_CUST_CPE_SGNLG_IP_ADDR: "+value);
			}
		}
		//Modify the IPO...
		if(ChangeTypeValue.contains("SP_IP_ORIGINATION")){
		nodes = (NodeList)xpath.evaluate("//*[text()='SP_IP_ORIGINATION']//following-sibling::Value", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			for(int idx=0;idx<nodes.getLength();idx++){
				String IPO=nodes.item(idx).getTextContent();
				IPMcomm.exportParam("ORIGINAL_VALUE", IPO);
				if(IPO.equals("Yes")){
					nodes.item(idx).setTextContent("No");
					nodes1 = (NodeList)xpath.evaluate("//*[text()='SP_IP_ORIGINATION']//following-sibling::Value", doc, XPathConstants.NODESET);
					if (nodes1.getLength()!=0){
						IPMcomm.exportParam("NEW_VALUE", "NO");
						Node n_value = nodes1.item(idx).getAttributes().getNamedItem("code");
					    n_value.setNodeValue("No");   
					}
					System.out.println("IPO MODIFIED AS No");
				}else{
					IPMcomm.exportParam("NEW_VALUE", "YES");
					nodes.item(idx).setTextContent("Yes");
					nodes1 = (NodeList)xpath.evaluate("//*[text()='SP_IP_ORIGINATION']//following-sibling::Value", doc, XPathConstants.NODESET);
					if (nodes1.getLength()!=0){
						Node n_value = nodes1.item(idx).getAttributes().getNamedItem("code");
					    n_value.setNodeValue("Yes");   
				}
					System.out.println("IPO MODIFIED AS Yes");}}}
		}
		
		// Change the TN Details
		if(ChangeTypeValue.contains("EFET_VOIP_TN_LVL")){
			
			nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_TN_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);	
			if (nodes.getLength()!=0)	
			{String FEATURE_CODE=nodes.item(0).getTextContent();
			IPMcomm.exportParam("FET_INS_ID", FEATURE_CODE);
			IPMcomm.exportParam("FEATURE_CODE", "EFET_VOIP_TN_LVL");
			}
			
		nodes= (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_TN_LVL']//following-sibling::ActionCode", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		nodes.item(0).setTextContent("CHANGE");
		}
		String contryCode=null;
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_DIALING_COUNTRY_CODE']//following-sibling::Value", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		 for (int idx = 0; idx < nodes.getLength(); idx++) {contryCode = nodes.item(idx).getTextContent();}	 
		 }
		nodes = (NodeList)xpath.evaluate("//*[text()='SP_VOIP_TELE_NUM']//following-sibling::Value", doc, XPathConstants.NODESET);				
		if(nodes.getLength()!=0){
		String value = nodes.item(0).getTextContent();
		IPMcomm.exportParam("ORIGINAL_VALUE", value);
		System.out.println("Existing TN: "+value);
		id = String.format("%05d", rndNum.nextInt(10000));
		String contryCodeTn=contryCode+value;
		String newCCODE=null;
		int TnLength=contryCodeTn.length()-contryCode.length();
		TnLength=TnLength-5;
		value = value.substring(0,TnLength) + id ;
		newCCODE=contryCode+value;
		//while(!DBValidFunction.ExistingTNCheck(stat,newCCODE)){
		//id = String.format("%05d", rndNum.nextInt(10000));
		//value = value.substring(0,TnLength) + id ;
		//newCCODE=contryCode+value;
		//}
		System.out.println("CountryCode and TN :"+contryCode+"-"+value);
		nodes.item(0).setTextContent(value);
		IPMcomm.exportParam("NEW_VALUE", value);
		nodes1 = (NodeList)xpath.evaluate("//*[text()='ESP_TSP_APPROVAL_CODE']//following-sibling::Value", doc, XPathConstants.NODESET);	
		if(nodes1.getLength()!=0){nodes1.item(0).setTextContent("T"+value);System.out.println("ESP_TSP_APPROVAL_CODE: T"+value);}
		System.out.println("TN CHANGED...");	
	}
	}
	}
 //new logic 
	Transformer xformer = TransformerFactory.newInstance().newTransformer();
	xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFilepath)));
	
	mapObj.put("outputFilepath", outputFilepath);
	System.out.println("completed..");
	return mapObj;
}

public HashMap<String,String> updateInstallXml(String inputFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException{

	System.out.println(inputFile);
	String ESP_ACC_TYPE = IPMcomm.importParam("ESP_ACC_TYPE");
	String IP_ORIGINATION = IPMcomm.importParam("IP_ORIGINATION");
	String[] parts = inputFile.split(".xml");
	String outputFile = parts[0]+"_Newchinna.xml";
	
	Random rndNum= new Random();  
	
	String OrderNumber = null;
	
	File f = new File("../VOIPCommon/XML/"+inputFile);
	File fo = new File("../VOIPCommon/XML/"+outputFile);
    System.out.println("path: "+f.getPath());
    System.out.println("abs path: "+f.getAbsolutePath());
    System.out.println("can path: "+f.getCanonicalPath());
    System.out.println("");
    String inputFilepath=f.getCanonicalPath();
	String outputFilepath=fo.getCanonicalPath();
	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputFilepath));
	XPath xpath = XPathFactory.newInstance().newXPath();
	
	HashMap<String, String> mapObj = new HashMap<String, String>();
	
	nodes = (NodeList)xpath.evaluate("//*[@schemeName='InstanceID']", doc, XPathConstants.NODESET);
	String id = String.format("%04d", rndNum.nextInt(10000));
	char c=id.charAt(0);
	 if(String.valueOf(c).equalsIgnoreCase("0")){
		 id=id.replace("0", "7");
	 }
	if(nodes.getLength()!=0){
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		//System.out.println(value);
		if(!value.equals("0")){							
			value = id  + value.substring(4);
			//value = "9" + timeStamp + value.substring(timeStamp.length()); 
			//System.out.println("Change # 1 - "+idx+" "+value);
			nodes.item(idx).setTextContent(value);
			//System.out.println(value);
		}
	}
	}

	nodes = (NodeList)xpath.evaluate("//*[@schemeName='WorkOrderNumber']", doc, XPathConstants.NODESET);
	if(nodes.getLength()!=0){
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeID").getNodeValue();
	//	value = "9" + timeStamp + value.substring(timeStamp.length());
		//System.out.println("Change # 2 - "+schemeID+" "+value);
		id = String.format("%07d", rndNum.nextInt(10000000));
		value = "9" + id ;
		OrderNumber=value;
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		//IPMcomm.exportParam("WorkOrderNumber",value);
	}
	}
	nodes = (NodeList)xpath.evaluate("//*[contains(@schemeName,'EnterpriseID')]", doc, XPathConstants.NODESET);
	if(nodes.getLength()!=0){
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		 //System.out.println(nodes.getLength());
		//String value = nodes.item(idx).getTextContent();
		String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
		//value = "9" + timeStamp + value.substring(timeStamp.length());
		//System.out.println("Change # 5 - "+schemeID+" "+value);
		mapObj.put(schemeID, OrderNumber);
		nodes.item(idx).setTextContent(OrderNumber);
	}
	}		

	nodes = (NodeList)xpath.evaluate("//*[contains(@schemeName,'ServiceOrder')]", doc, XPathConstants.NODESET);
	if(nodes.getLength()!=0){
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		String value = nodes.item(idx).getTextContent();
		String schemeID = nodes.item(idx).getAttributes().getNamedItem("schemeName").getNodeValue();
		id = String.format("%07d", rndNum.nextInt(10000000));
		value = "8" + id ;
		//value = "8" + timeStamp + value.substring(timeStamp.length());
		//System.out.println("Change # 5 - "+schemeID+" "+value);
		if (schemeID.equals("UNOServiceOrder")){
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		//IPMcomm.exportParam("ServiceOrder",value);
		//IPMcomm.logMessage(IPMcomm.TEST_CASE, IPMcomm.PASSED, "Service Order :" + value, "Service Order");
	} else{
		mapObj.put(schemeID, value);
		nodes.item(idx).setTextContent(value);
		System.out.println(schemeID+" *********$$$$**"+value);
	}
	}
	}
	
		String value = null;
		nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENTERPRISE_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		//for (int idx = 0; idx < nodes.getLength(); idx++) {
		//String value = nodes.item(idx).getNodeName();
		//System.out.println("Change # 4 - "+idx+" "+dateStamp);
		 System.out.println("ESP_ENTERPRISE_NAME LENGTH: "+nodes.getLength());
		 id = String.format("%07d", rndNum.nextInt(10000000));
		// System.out.println(value);
		//value = "9" + timeStamp;
		value = "9" + id;
		String ENTERPRISE_NAME="ENT" + value;
		nodes.item(0).setTextContent(ENTERPRISE_NAME);
	//}
		}

			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_EMAIL']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
					
			if(nodes.getLength()!=0){value = "9" + timeStamp + "@GMAIL.COM";	nodes.item(0).setTextContent(value);}
			
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_FST_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
			if(nodes.getLength()!=0){	value = "9" + timeStamp + "SENTHIL";nodes.item(0).setTextContent(value);}
		
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_LST_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);					
			if(nodes.getLength()!=0){ value = "9" + timeStamp + "RAJAN";	nodes.item(0).setTextContent(value);}
		
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_PW']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);
			if(nodes.getLength()!=0){value = "9" + timeStamp + "PWD";nodes.item(0).setTextContent(value);}
	
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ADM_LOGIN_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);
			if(nodes.getLength()!=0){value = "9" + timeStamp + "SENTHIL";nodes.item(0).setTextContent(value);}
			
			
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_SIP_DOMAIN']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
			value = "9" + timeStamp + ".test02.kiehl.org";	
			if(nodes.getLength()!=0){nodes.item(0).setTextContent(value);	}
	
		//ipccc PUSH XML
		if(parts[0].contains("_IPCC")){
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_NCAR_ID']//following-sibling::Value", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){
					id = String.format("%04d", rndNum.nextInt(10000));
					value=nodes.item(0).getTextContent();
					value=value.substring(0,id.length());
					value=value+id;
					nodes.item(0).setTextContent(value);
					System.out.println("SP_NCAR_ID: "+value);
					}
			
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_CUST_CPE_SGNLG_IP_ADDR']//following-sibling::Value", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){
				for(int idx=0;idx<nodes.getLength();idx++){
				id = String.format("%02d", rndNum.nextInt(100));
				System.out.println(id.charAt(0));
				if(id.charAt(0)=='0')
					id="18";
				 value=nodes.item(0).getTextContent();
				value=value.substring(id.length());
				System.out.println(value);
				value=id+value;
				nodes.item(idx).setTextContent(value);
				System.out.println("SP_CUST_CPE_SGNLG_IP_ADDR: "+value);
				}
			}
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_FQDN']//following-sibling::Value", doc, XPathConstants.NODESET);	
					if(nodes.getLength()!=0){
						for(int idx=0;idx<nodes.getLength();idx++){
							id = String.format("%07d", rndNum.nextInt(10000000));
							value = "9" + id + ".test02.kiehl.org";
						nodes.item(0).setTextContent(value);	
					System.out.println("SP_FQDN: "+value);
					}
					}
				nodes = (NodeList)xpath.evaluate("//*[text()='SP_CUSTOMER_DOMAIN']//following-sibling::Value", doc, XPathConstants.NODESET);	
					if(nodes.getLength()!=0){
						for(int idx=0;idx<nodes.getLength();idx++){
						id = String.format("%07d", rndNum.nextInt(1000000));
						value = "9" + id + ".test02.kiehl.org";
						nodes.item(0).setTextContent(value);	
						System.out.println("SP_CUSTOMER_DOMAIN: "+value);
						}
					}
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_IPCC_SERVICE_NAME']//following-sibling::Value", doc, XPathConstants.NODESET);
			if(nodes.getLength()!=0){
				 System.out.println("SP_IPCC_SERVICE_NAME LENGTH: "+nodes.getLength());
				 id = String.format("%07d", rndNum.nextInt(10000000));
				value = "9" + id;
				String IPCC_SERVICE_NAME="IPCC" + value;
				nodes.item(0).setTextContent(IPCC_SERVICE_NAME);
				System.out.println("SP_IPCC_SERVICE_NAME : "+IPCC_SERVICE_NAME);
				}
			nodes = (NodeList)xpath.evaluate("//*[text()='ESP_ACC_TYPE']//following-sibling::Value", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){
				for(int idx=0;idx<nodes.getLength();idx++){
				nodes.item(idx).setTextContent(ESP_ACC_TYPE);
				}
			}
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_IP_ORIGINATION']//following-sibling::Value", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){
				for(int idx=0;idx<nodes.getLength();idx++){
				nodes.item(idx).setTextContent("NO");
				}
			}
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_IP_ORIGINATION']//following-sibling::Value", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				org.w3c.dom.Node n_value = nodes.item(idx).getAttributes().getNamedItem("code");
			    String val = n_value.getNodeValue();
			    System.out.print(val);
			    n_value.setNodeValue("NO");   
			}
			} //new logic
			nodes = (NodeList)xpath.evaluate("//*[text()='SP_TERM_NAME']//following-sibling::Value", doc, XPathConstants.NODESET);
			if(nodes.getLength()!=0){
				for(int idx=0;idx<nodes.getLength();idx++){
				 id = String.format("%07d", rndNum.nextInt(10000000));
				value = "8" + id;
				String SP_TERM_NAME="CHIN" + value;
				nodes.item(idx).setTextContent(SP_TERM_NAME);
				System.out.println("SP_TERM_NAME : "+SP_TERM_NAME);
				}
			}
			//code  end ipcc 
			
		}
		System.out.println("chinna");
		if ( parts[0].contains("_TSO_ENT") )	
		{
			//String RelatedServiceInstanceID= IPMcomm.importParam("PR_IDS_AND_PR_PIP_SVC");
			//String PR_IDS_PR_PIP_SVC[]= RelatedServiceInstanceID.split(",");
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENTERPRISE_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);							
			if (nodes.getLength()!=0){nodes.item(0).setTextContent(OrderNumber);}	
		
			nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_IDS']", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0)
		 {  
				for (int idx = 0; idx < nodes.getLength(); idx++) {
					//nodes.item(0).setTextContent("5724997");
					nodes.item(0).setTextContent("100421989");
					//nodes.item(idx).setTextContent(PR_IDS_PR_PIP_SVC[idx]);
					//nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_EBI_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);	
	
					//	nodes.item(0).setTextContent("5724997");
	
				}
				
				//nodes.item(0).setTextContent("91958533");
			
				//nodes.item(0).setTextContent("5727430");
				//nodes.item(0).setTextContent("100421989");
		//	nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_EBI_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);	
			
		//	nodes.item(0).setTextContent("91958533");
		
		 }
			
			nodes = (NodeList)xpath.evaluate("//*[@schemeAgencyName='PR_PIP_SVC']", doc, XPathConstants.NODESET);
			//System.out.println("Change # 1 - "+idx+" "+value);
			if (nodes.getLength()!=0)
			{	for (int idx = 0; idx < nodes.getLength(); idx++) {
				//nodes.item(0).setTextContent("5724997");
				nodes.item(0).setTextContent("100421989");
				//nodes.item(idx).setTextContent(PR_IDS_PR_PIP_SVC[idx]);
				//nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_EBI_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);	

				//	nodes.item(0).setTextContent("5724997");

			}
			}
	

			nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_ENT_TRUNK']//preceding-sibling::ID", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){value= nodes.item(0).getTextContent();}

			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_ENT_TRUNK_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);										
			if (nodes.getLength()!=0){nodes.item(0).setTextContent(value);}
			System.out.println("EFET_VOIP_ENT_TRUNK: "+value);
				
				
			nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_ENT_EBI_PRIORITY_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);										
				if (nodes.getLength()!=0){
				value= nodes.item(0).getTextContent();}

				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_EBI_PRIORITY_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);										
		   		if (nodes.getLength()!=0){nodes.item(0).setTextContent(value);}
		   		System.out.println("EFET_VOIP_ENT_EBI_PRIORITY_LVL: "+value );

					
			nodes = (NodeList)xpath.evaluate("//*[text()='EFET_VOIP_EBI_INFO']//preceding-sibling::ID", doc, XPathConstants.NODESET);
			if (nodes.getLength()!=0){value= nodes.item(0).getTextContent();}	

				nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_VOIP_ENT_REL_EBI_ID']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
				if (nodes.getLength()!=0){nodes.item(0).setTextContent(value);}
				System.out.println("EFET_VOIP_EBI_INFO: "+value);
								
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_ENT_TRUNK_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
			value = "ET" + timeStamp ;							
			if (nodes.getLength()!=0){nodes.item(0).setTextContent(value);}
			
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_EBI_IP']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
			if (nodes.getLength()!=0){
			value = "91" + timeStamp ;
			
			//value = value.substring(0,1) +"."+value.substring(2,3) +"."+value.substring(4,5) +"."+value.substring(6,7) ;
			//System.out.println(value);
			//nodes.item(0).setTextContent(value);
			}							
			nodes = (NodeList)xpath.evaluate("//*[preceding::*[text()='ESP_EBI_NAME']][local-name()='Value'][not(@*)]", doc, XPathConstants.NODESET);				
			if (nodes.getLength()!=0){
			value = "EBI" + timeStamp ;
			nodes.item(0).setTextContent(value);
			}
		}
		String Ncar_value="";
		nodes = (NodeList)xpath.evaluate("//*[@type='PR_VOIP']//following-sibling::ID", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			Ncar_value=nodes.item(0).getTextContent();
		System.out.println("ENtr_ID>>>>>>"+Ncar_value);
		}
		nodes = (NodeList)xpath.evaluate("//*[text()='ESP_NCAR_ID']//following-sibling::Value", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			for(int idx=0;idx<nodes.getLength();idx++){//668295762
				id = String.format("%05d", rndNum.nextInt(100000));
				id="6682"+id;
				nodes.item(idx).setTextContent(Ncar_value);
				System.out.println("ESP_NCAR_ID: "+Ncar_value);
			}
		}
	Transformer xformer = TransformerFactory.newInstance().newTransformer();
	System.out.println(outputFilepath);
	
	xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFilepath)));
	
	mapObj.put("outputFilepath", outputFilepath);
System.out.println(outputFilepath);
	return mapObj;
}

public HashMap<String,String> updateSuppOthersInstallXml(String inputFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException{
	String[] parts = inputFile.split(".xml");
	String outputFile = parts[0]+"_Suppotherschinna.xml";
	Node curNode = null;
	NodeList nodes1=null;
	Random rndNum= new Random();
	Element element=null;
	Node newNode =null;
	Node newNode2=null;
	String id=null;
	String value=null;
	String value1=null;
	File f = new File("../VOIPCommon/XML/"+inputFile);
	File fo = new File("../VOIPCommon/XML/"+outputFile);
    System.out.println("path: "+f.getPath());
    System.out.println("abs path: "+f.getAbsolutePath());
    System.out.println("can path: "+f.getCanonicalPath());
    String inputFilepath=f.getCanonicalPath();
	String outputFilepath=fo.getCanonicalPath();
	Random ran = new Random();
	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputFilepath));
	XPath xpath = XPathFactory.newInstance().newXPath();
	
	HashMap<String, String> mapObj = new HashMap<String, String>();		

	nodes = (NodeList)xpath.evaluate("//RevisionID", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		 value = nodes.item(idx).getTextContent();
				nodes.item(idx).setTextContent("1");	
	}		
	nodes = (NodeList)xpath.evaluate("//*[@listName='INSTALL']", doc, XPathConstants.NODESET);
	for (int idx = 0; idx < nodes.getLength(); idx++) {
		nodes.item(idx).setTextContent("SUPP");							
	}  
     NodeList nList2 = doc.getElementsByTagName("ActionCode");
     Node nList3=  nList2.item(0);
     
     Element eElement = (Element) nList3;         
     eElement.setAttribute("name","S3");
   
  // USAGE Plan modify tag
     nodes = (NodeList)xpath.evaluate("//*[text()='SP_VOIP_ENT_USAGE_PLAN']//following-sibling::Value", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			Node code=nodes.item(0).getAttributes().getNamedItem("code");
			String usageCode=code.getNodeValue();
			System.out.println("Orginal UsageCode: "+usageCode);
			String usageText=nodes.item(0).getTextContent();
			if(usageText.equalsIgnoreCase("Metered") && usageCode.equalsIgnoreCase("11")){
				nodes.item(0).setTextContent("Tiered");
				code.setNodeValue(usageCode.replaceAll("11", "12"));
				System.out.println(usageCode.replaceAll("11", "12"));
				System.out.println("Tiered");
				System.out.println("UsageCode: "+usageCode.replaceAll("11", "12"));
			}else{
				nodes.item(0).setTextContent("Metered");
				code.setNodeValue(usageCode.replaceAll("12", "11"));
				System.out.println("Metered");
				System.out.println("UsageCode: "+usageCode.replaceAll("12", "11"));
			}
		}
//add usagePlan
		nodes=(NodeList)xpath.evaluate("//*[text()='EFET_VOIP_ENT_LVL']//preceding-sibling::ID", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
			System.out.println(nodes.item(0).getTextContent());
		System.out.println("EFET_VOIP_EBI_INFO Length is:"+nodes.getLength());
		curNode = nodes.item(0);
		Node Parent =curNode.getParentNode();
		System.out.println(Parent);
		Element Spec=doc.createElement("Spec");
		Element Code=doc.createElement("Code");
		Element Value=doc.createElement("Value");
		Spec.setAttribute("rateDeterminant","true");
		Code.setAttribute("name","Enterprise Usage Plan");
		Code.appendChild(doc.createTextNode("SP_VOIP_ENT_USAGE_PLAN"));
		Value.setAttribute("code","11");
		Value.appendChild(doc.createTextNode("Metered"));
		Spec.appendChild(Code);
		Spec.appendChild(Value);
		Parent.appendChild(Spec);
		System.out.println("chinna try");
		}
// IP ADDRESS Modification 
nodes = (NodeList)xpath.evaluate("//*[text()='SP_CUST_CPE_SGNLG_IP_ADDR']//following-sibling::Value", doc, XPathConstants.NODESET);
		if (nodes.getLength()!=0){
			 id = String.format("%02d", rndNum.nextInt(100));
			if(id.charAt(0)=='0')
				id="18";
			 value=nodes.item(0).getTextContent();
			value=value.substring(id.length());
			value=id+value;
			nodes.item(0).setTextContent(value);
			System.out.println("SP_CUST_CPE_SGNLG_IP_ADDR: "+value);
			}
	
//Add NEW IPTERM SPECS NODES 
nodes=(NodeList)xpath.evaluate("//*[text()='FET_IPCC_IP_TERM_LVL']/..", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		System.out.println("Node Length is:"+nodes.getLength());
		curNode = nodes.item(0);
		Node Parent =curNode.getParentNode();
		Node clone =curNode.cloneNode(true);
		
		//Change the FET_IPCC_IP_TERM_LVL
		 element=(Element)clone;
		System.out.println("ele Length is:"+element.getElementsByTagName("ID").getLength());
		newNode =  element.getElementsByTagName("ID").item(0);
		id=String.format("%08d",ran.nextInt(100000000));
		newNode.setTextContent(id);
		
		//Change the SP_CUST_CPE_SGNLG_IP_ADDR
		newNode = element.getElementsByTagName("Value").item(14);
		id=String.format("%02d",ran.nextInt(100));
		if(id.charAt(0)=='0')
			id="18";
		value=newNode.getTextContent();
		value=value.substring(id.length());
		value=id+value;
		newNode.setTextContent(value);
		System.out.println("SP_CUST_CPE_SGNLG_IP_ADDR: "+value);
		
		//Change the SP_FQDN
		newNode = element.getElementsByTagName("Value").item(15);
		id = String.format("%07d", rndNum.nextInt(10000000));
		value = "9" + id + ".test02.kiehl.org";
		newNode.setTextContent(value);	
		System.out.println("SP_FQDN: "+value);
		
		//Change the SP_IP_ORIGINATION
		newNode = element.getElementsByTagName("Value").item(16);
		newNode.setTextContent("No");
		 element = (Element) newNode;         
		 element.setAttribute("code","No");
		Parent.appendChild(clone);
		System.out.println("FET_IPCC_IP_TERM_LVL Spec cloned..");
		}
//Add new  EBI Info on the Enterprise order
nodes=(NodeList)xpath.evaluate("//*[text()='EFET_VOIP_EBI_INFO']/..", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		System.out.println("Node Length is:"+nodes.getLength());
		curNode = nodes.item(0);
		Node Parent =curNode.getParentNode();
		Node clone =curNode.cloneNode(true);
		element=(Element)clone;
	
		// Change the EFET_VOIP_EBI_INFO Instance
		System.out.println("ID TAG COUNT: "+element.getElementsByTagName("ID").getLength());
		newNode =  element.getElementsByTagName("ID").item(0);
		id=String.format("%08d",ran.nextInt(100000000));
		value1=id;
		newNode.setTextContent(value1);
		System.out.println("EBI_ID: "+value1);
		
		//Change EBI IP address		
		System.out.println("Value Tag Count: "+element.getElementsByTagName("Value").getLength());
		newNode = element.getElementsByTagName("Value").item(3);
		id=String.format("%08d",ran.nextInt(100000000));
		value = "10" +id;
		//18.03.28.98
		value = value.substring(0,2) +"."+value.substring(3,5) +"."+value.substring(6,8) +"."+value.substring(7,9) ;
		newNode.setTextContent(value);
		System.out.println("EBI IP Address: "+value);
		
		//Change EBI Name
		newNode = element.getElementsByTagName("Value").item(2);
		id=String.format("%05d",ran.nextInt(100000));
		value="EBI"+id;
		newNode.setTextContent(value);
		System.out.println("EBINAME: "+value);
		
		//update  the PR_PIP_SVC 
		newNode = element.getElementsByTagName("ID").item(2);
		newNode.setTextContent("100421989");
		value=newNode.getTextContent();
		System.out.println("PR_PIP_SVC: "+value);
		Parent.appendChild(clone);
		}
//ESP_VOIP_ENT_REL_EBI_ID		
nodes=(NodeList)xpath.evaluate("//*[text()='EFET_VOIP_ENT_EBI_PRI_WEI_LVL']/..", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		System.out.println("Node Length is:"+nodes.getLength());
		curNode = nodes.item(0);
		Node Parent =curNode.getParentNode();
		Node clone =curNode.cloneNode(true);
		element=(Element)clone;
		
		// Change the EFET_VOIP_ENT_EBI_PRI_WEI_LVL Instance
		System.out.println("ID TAG COUNT: "+element.getElementsByTagName("ID").getLength());
		newNode =  element.getElementsByTagName("ID").item(0);
		id=String.format("%08d",ran.nextInt(100000000));
		newNode.setTextContent(id);
		System.out.println("EFET_VOIP_ENT_EBI_PRI_WEI_LVL  : "+id);
		//ESP_VOIP_ENT_REL_EBI_ID	
		newNode = element.getElementsByTagName("Value").item(3);
		newNode.setTextContent(value1);
		value=newNode.getTextContent();
		System.out.println("ESP_VOIP_ENT_REL_EBI_ID: "+value);
		
		Parent.appendChild(clone);
		}
	
//Add NEW TN FOR LOCAL ORDER
nodes=(NodeList)xpath.evaluate("//*[text()='EFET_VOIP_TN_LVL']/..", doc, XPathConstants.NODESET);
		if(nodes.getLength()!=0){
		System.out.println("EFET_VOIP_EBI_INFO Length is:"+nodes.getLength());
		curNode = nodes.item(0);
		Node Parent =curNode.getParentNode();
		Node clone =curNode.cloneNode(true);
		element=(Element)clone;
		
		// update EFET_VOIP_TN_LVL value
		System.out.println("ID TAG COUNT: "+element.getElementsByTagName("ID").getLength());
		newNode =  element.getElementsByTagName("ID").item(0);
		id=String.format("%08d",ran.nextInt(100000000));
		newNode.setTextContent(id+"chinna");
		
		 String contryCode=null;
		 nodes = (NodeList)xpath.evaluate("//*[text()='ESP_DIALING_COUNTRY_CODE']//following-sibling::Value", doc, XPathConstants.NODESET);
		 if(nodes.getLength()!=0){
			 for (int idx = 0; idx < nodes.getLength(); idx++) {contryCode = nodes.item(idx).getTextContent();}	 
		 }
		//CHANGE THE TN NUMBER.
		newNode = element.getElementsByTagName("Value").item(0);
		System.out.println("Value Tag: "+element.getElementsByTagName("Value").getLength());
		value = newNode.getTextContent();
		System.out.println("value"+value);
		id = String.format("%05d", rndNum.nextInt(10000));
		String contryCodeTn=contryCode+value;
		String newCCODE=null;
		int TnLength=contryCodeTn.length()-contryCode.length();
		TnLength=TnLength-5;
		value = value.substring(0,TnLength) + id ;
		newCCODE=contryCode+value;
		//while(!DBValidFunction.ExistingTNCheck(stat,newCCODE)){
		//id = String.format("%05d", rndNum.nextInt(10000));
		//TnLength=TnLength-5;
		//value = value.substring(0,TnLength) + id ;
		//newCCODE=contryCode+value;
		//}
		System.out.println("CountryCode and TN :"+contryCode+"-"+value);
		newNode.setTextContent(value);
		//The ESP_TSP_APPROVAL_CODE
		newNode2 =  element.getElementsByTagName("Value").item(4);	
		newNode2.setTextContent("T"+value);System.out.println("ESP_TSP_APPROVAL_CODE: T"+value);
	
		//Change Ported Indicator Value
		newNode = element.getElementsByTagName("Value").item(1);
		newNode.setTextContent("No");
		 element = (Element) newNode;         
		 element.setAttribute("code","N");
		Parent.appendChild(clone);	
		}
		
	Transformer xformer = TransformerFactory.newInstance().newTransformer();
	xformer.transform(new DOMSource(doc), new StreamResult(new File(outputFilepath)));
	
	mapObj.put("outputFilepath", outputFilepath);
	System.out.println(outputFilepath);
	return mapObj;
}


		
public static void main(String a[]) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, SQLException{
	classxml xml=new classxml();
	//xml.updateChangeOrderPushXml("IPCC_BASE_New.xml");
	//xml.updateSuppOthersInstallXml("US_TSO_LOC_IDS.xml"); 
	//xml.updateChangeOrderPushXml("EMEA_NONTSO_LOC_IDS_INSTALL.xml"); 
	//xml.updateSuppOthersInstallXml("IPCC_BASE_New.xml"); 
	xml.updateSuppOthersInstallXml("US_NONTSO_ENT_INSTALL.xml");
	//xml.updateInstallXml("US_TSO_ENT_WITH_1EBI_1ET_PIP_NCAR_New.xml");
	
	
}
}
