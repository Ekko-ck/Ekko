package net.openobject.ekko.config;

import java.net.InetSocketAddress;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
	
	@Value("${elasticsearch.address}")
    private String address;
    @Value("${elasticsearch.port}")
    private Integer port;
	
	@Override
	public RestHighLevelClient elasticsearchClient() {
		log.info("Elasticsearch Address: {}, Port: {}", address, port);
		InetSocketAddress isa = new InetSocketAddress(address, port);
		return RestClients.create(ClientConfiguration.create(isa)).rest();
	}
	
}
