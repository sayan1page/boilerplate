package com.beta.replyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		try{
			if(message.indexOf("-") == -1){
				return new ReplyMessage(message);
			}else{
				String CharSequence = message.split("-")[0];
				String acMessage = message.split("-")[1];
				for(char c : CharSequence.toCharArray()){
					if(c=='1'){
						acMessage = new StringBuilder(acMessage).reverse().toString();  
					}
					if(c=='2'){
						MessageDigest md = MessageDigest.getInstance("MD5"); 
  
                       byte[] messageDigest = md.digest(acMessage.getBytes()); 
  
                        BigInteger no = new BigInteger(1, messageDigest); 
              			String hashtext = no.toString(16); 
              			
            			while (hashtext.length() < 32) { 
                			hashtext = "0" + hashtext; 
            			}
            			acMessage = hashtext; 			           			
					}
				}
				return new ReplyMessage(acMessage);
			}
	}catch(Exception e){
			return new ReplyMessage(e.toString());
		}
								
	}
}