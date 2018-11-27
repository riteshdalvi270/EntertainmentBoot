package com.entertainment.entertainment.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @author Ritesh Dalvi
 **/
@Configuration
@EnableSolrRepositories(basePackages = "com.entertainment.entertainment.solr",
namedQueriesLocation = "classpath:solr-query.properties"
)
@ComponentScan
public class SolrConfig {

    static final String SOLR_HOST = "http://localhost:8983/solr/";

    /*@Resource
    private Environment environment;*/

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient(SOLR_HOST);
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
