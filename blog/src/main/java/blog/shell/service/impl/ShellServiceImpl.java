package blog.shell.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import blog.shell.service.ShellService;
@Service
public class ShellServiceImpl implements ShellService{
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public String test() throws Exception {
		Process process = null;  
        List<String> processList = new ArrayList<String>();  
       
            process = Runtime.getRuntime().exec("ps -aux");  
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            String line1 = "";  
            while ((line1 = input.readLine()) != null) {  
                processList.add(line1);  
            }  
            input.close();  
         
  
        for (String line : processList) {  
        	logger.info(line);
            System.out.println(line);  
        }
		return null;
	}

}
