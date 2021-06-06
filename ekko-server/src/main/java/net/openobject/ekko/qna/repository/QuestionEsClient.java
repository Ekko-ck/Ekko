package net.openobject.ekko.qna.repository;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import net.openobject.ekko.qna.document.Question;

@Component
public class QuestionEsClient {
	
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	private static final String INDEX_NAME_QUESTION = "question";
	private static final IndexCoordinates INDEX_QUESTION = IndexCoordinates.of(INDEX_NAME_QUESTION);
	private static final String[] SEARCH_FIELDS = new String[] { "title.nori", "contents.nori", "answers.contents.nori", "tags.nori" };
	
	public List<Question> findAll(Pageable pageable) {
		Query searchQuery = new NativeSearchQueryBuilder()
									.withSort(new FieldSortBuilder("registeredAt").order(SortOrder.DESC))
									.withPageable(pageable)
									.build();
		SearchHits<Question> searchHits = elasticsearchOperations.search(searchQuery, Question.class, INDEX_QUESTION);
		List<Question> resultList = searchHits.map(hit -> hit.getContent()).toList();
		return resultList;
	}
	
	public Question findById(String id) {
		return elasticsearchOperations.get(id, Question.class, INDEX_QUESTION);
	}
	
	public List<Question> search(Pageable pageable, String query) {
		QueryBuilder queryBuilder = QueryBuilders
										.multiMatchQuery(query, SEARCH_FIELDS);
		Query searchQuery = new NativeSearchQueryBuilder()
									.withQuery(queryBuilder)
									.withPageable(pageable)
									.build();
		SearchHits<Question> searchHits = elasticsearchOperations.search(searchQuery, Question.class);
		List<Question> resultList = searchHits.map(hit -> hit.getContent()).toList();
		return resultList;
	}
	
	public Question save(Question question) {
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withObject(question)
				.build();
		String docId = elasticsearchOperations.index(indexQuery, INDEX_QUESTION);
		return elasticsearchOperations.get(docId, Question.class);
	}
	
	public Question update(Question question) {
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(question.getId())
				.withObject(question)
				.build();
		String docId = elasticsearchOperations.index(indexQuery, INDEX_QUESTION);
		return elasticsearchOperations.get(docId, Question.class);
	}
	
	public void delete(String id) {
		elasticsearchOperations.delete(id, INDEX_QUESTION);
	}
	
}
