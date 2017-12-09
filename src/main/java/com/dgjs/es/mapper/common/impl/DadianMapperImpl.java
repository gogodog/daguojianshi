package com.dgjs.es.mapper.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.valuecount.InternalValueCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.common.DadianMapper;
import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.result.view.DadianView;
import com.dgjs.utils.StringUtils;

@Service
public class DadianMapperImpl implements DadianMapper{

	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dgjs_log_v1";
	
	final static String type = "dadian";
	
	@Override
	public int insert(DadianView dadianView) {
		TransportClient client=transportClient.getClient();
		IndexRequestBuilder indexRequestBuilder =client.prepareIndex(index, type);
	    @SuppressWarnings("deprecation")
		IndexResponse response = indexRequestBuilder.setSource(dadianView.toString()).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public long pageIdCount(String pageid) {
		TransportClient client=transportClient.getClient();
		SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type);
		responsebuilder.setQuery(QueryBuilders.termQuery("pageid", pageid));
		SearchResponse myresponse =responsebuilder.execute().actionGet();
		SearchHits hits = myresponse.getHits();
		return hits.getTotalHits();
	}

	@Override
	public List<PagedocidsCountResult> pagedocidsCount(List<String> pagedocids) {
		 TransportClient client=transportClient.getClient();
		 AggregationBuilder aggregationBuilder = AggregationBuilders  
	                .terms("dadian_agg").field("pagedocids")  
	                .subAggregation(AggregationBuilders.count("agg_count").field("pagedocids"));  
         SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type).addAggregation(aggregationBuilder); 
         SearchResponse sr=responsebuilder.setSearchType(SearchType.DEFAULT).setQuery(QueryBuilders.termsQuery("pagedocids", pagedocids))
        		 .setExplain(true).execute().actionGet();
         Terms names = sr.getAggregations().get("dadian_agg");
         List<PagedocidsCountResult> list = new ArrayList<PagedocidsCountResult>();
         for (Terms.Bucket entry : names.getBuckets()) {
	        InternalValueCount internames =entry.getAggregations().get("agg_count");
	        PagedocidsCountResult result = new PagedocidsCountResult();
	        result.setPagedocids(String.valueOf(entry.getKey()));
	        result.setVisits((long)internames.getValue());
	        list.add(result);
         }
		return list;
	}

	@Override
	public void getArticleDaysVisits() {
		 TransportClient client=transportClient.getClient();
		 AggregationBuilder aggregationBuilder = AggregationBuilders  
	                .terms("dadian_agg").field("pagedocids")  
	                .subAggregation(AggregationBuilders.count("agg_count").field("pagedocids"));  
	}

}
