package cn.et;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.governator.annotations.binding.Response;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

@Controller
public class SendController {
	
	@Autowired
	private ISendMail sendMail;
	
	@ResponseBody
	@GetMapping("/invokeUser")
	public String invokeUser(String id){
		Map result = sendMail.getUser(id);
		return result.get("name").toString();
	}
	
	@GetMapping("/sendClient")
	public String send(String email_to,String email_subject,String email_content){
		//通过注册中心客户端负载均衡，获取一台主机俩调用
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email_to", email_to);
			map.put("email_subject", email_subject);
			map.put("email_content", email_content);
			sendMail.send(map);
		} catch (RestClientException e) {
			e.printStackTrace();
			return "redirect:/error.html";
		}
		return "redirect:/suc.html";
	}
}
