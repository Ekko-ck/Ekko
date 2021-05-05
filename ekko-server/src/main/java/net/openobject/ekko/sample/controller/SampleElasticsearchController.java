package net.openobject.ekko.sample.controller;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.sample.document.TestDoc;
import net.openobject.ekko.sample.dto.SampleSearchRequest;

@RequestMapping("/api/sample/es")
@RestController
@Slf4j
public class SampleElasticsearchController {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	@GetMapping("/search")
	public List<TestDoc> search(@RequestBody SampleSearchRequest searchRequest) {
		QueryBuilder queryBuilder = QueryBuilders
										.matchQuery("message.nori", searchRequest.getQuery());
	    Query searchQuery = new NativeSearchQueryBuilder()
									.withQuery(queryBuilder)
									.build();
		SearchHits<TestDoc> searchHits = elasticsearchOperations.search(searchQuery, TestDoc.class);
		
		List<TestDoc> resultList = searchHits.map(hit -> hit.getContent()).toList();
		
		searchHits.forEach(hit -> {
			TestDoc doc = hit.getContent();
			log.info("score: {}, doc: {}", hit.getScore(), doc.toString());
		});
		
		return resultList;
	}
	
}
