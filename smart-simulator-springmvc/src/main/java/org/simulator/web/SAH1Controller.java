package org.simulator.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/sha1")
@Controller
public class SAH1Controller {

	@RequestMapping(value = "/{text}")
	@ResponseBody
	public String encode(@PathVariable String text) {
		return DigestUtils.sha1Hex(text);
	}

}
