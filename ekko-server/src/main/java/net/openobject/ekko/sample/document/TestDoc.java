package net.openobject.ekko.sample.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "leaf")
@Data
public class TestDoc {
	
	@Id
	private String id;
	
	@Field(type = FieldType.Text, name = "name")
	private String name;
	
	@Field(type = FieldType.Text, name = "message")
	private String message;
	
}
