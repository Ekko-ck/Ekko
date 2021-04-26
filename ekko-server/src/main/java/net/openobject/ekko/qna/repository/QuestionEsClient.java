package net.openobject.ekko.qna.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import net.openobject.ekko.qna.document.Question;

@Component
public class QuestionEsClient {
	
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	private static final String[] SEARCH_FIELDS = new String[] { "title.nori", "contents.nori", "answers.contents.nori" };
	
	public List<Question> findAll(PageRequest pageRequest) {
		Query searchQuery = new NativeSearchQueryBuilder()
									.withSort(new FieldSortBuilder("createdAt").order(SortOrder.ASC))
									.withPageable(pageRequest)
									.build();
		SearchHits<Question> searchHits = elasticsearchOperations.search(searchQuery, Question.class);
		List<Question> resultList = searchHits.map(hit -> hit.getContent()).toList();
		return resultList;
	}
	
	public List<Question> search(Pageable pageable, String query) {
		QueryBuilder queryBuilder = QueryBuilders
										.multiMatchQuery(query, SEARCH_FIELDS);
		Query searchQuery = new NativeSearchQueryBuilder()
									.withQuery(queryBuilder)
									.withSort(new FieldSortBuilder("createdAt").order(SortOrder.ASC))
									.withPageable(pageable)
									.build();
		SearchHits<Question> searchHits = elasticsearchOperations.search(searchQuery, Question.class);
		List<Question> resultList = searchHits.map(hit -> hit.getContent()).toList();
		return resultList;
	}
	
}
