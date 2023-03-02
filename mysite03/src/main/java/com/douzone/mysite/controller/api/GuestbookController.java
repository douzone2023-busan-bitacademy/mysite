package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.service.GuestbookService;

@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("")
	public JSONResult list(@RequestParam(value="sno", required=true, defaultValue="0") Long startNo) {
		List<GuestbookVo> list = guestbookService.getMessageList(startNo);
		return JsonResult.success(list);
	}

	@PostMapping("")
	public JSONResult add(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}

	@DeleteMapping("/{no}")
	public JSONResult delete(
			@PathVariable("no") Long no,
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		return JsonResult.success(result ? no : -1);
	}	
	
}
