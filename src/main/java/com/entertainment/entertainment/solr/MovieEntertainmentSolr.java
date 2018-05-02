package com.entertainment.entertainment.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MovieEntertainmentSolr {

    public SolrDocument retrieveFromSolr() throws IOException, SolrServerException {

        String urlString = "http://localhost:8983/solr/#/entertainmentboot";

        SolrClient solrClient =  new HttpSolrClient(urlString);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "*:*");
        solrQuery.set("fl", "movieTypeId,movieType");

        QueryResponse query = solrClient.query(solrQuery);

        SolrDocumentList results = query.getResults();

        SolrDocument entries = results.get(0);

        return entries;
    }
}
