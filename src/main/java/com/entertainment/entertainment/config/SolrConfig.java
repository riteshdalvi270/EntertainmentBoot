package com.entertainment.entertainment.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

/**
 * @author Ritesh Dalvi
 **/
/*@Configuration
@EnableSolrRepositories(basePackages = "com.entertainment.entertainment.solr.repository",
namedQueriesLocation = "classpath:solr-queries.properties"
)
@ComponentScan
public class SolrConfig {

    static final String SOLR_HOST = "solr.host";

    @Resource
    private Environment environment;


    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder().withBaseSolrUrl(environment.getProperty(SOLR_HOST)).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}*/
