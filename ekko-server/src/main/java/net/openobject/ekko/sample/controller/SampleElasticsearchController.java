package net.openobject.ekko.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample/es")
public class SampleElasticsearchController {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	@RequestMapping("/search")
	public String search(@RequestParam(value = "query", required = true) String query) {
		
		return "";
	}
	
}
