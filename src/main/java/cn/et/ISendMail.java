package cn.et;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("REGMAIL")
public interface ISendMail {

	@GetMapping("/user/{userId}")
	public Map getUser(@PathVariable("userId") String userId);
	
	@GetMapping("/send")
	public String send(@RequestBody Map<String, Object> map);
}
