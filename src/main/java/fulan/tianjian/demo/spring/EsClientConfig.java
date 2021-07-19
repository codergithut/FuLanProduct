package fulan.tianjian.demo.spring;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class EsClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.username}")
    private String USERNAME;
    @Value("${elasticsearch.password}")
    private String PASSWORD;
    @Value("${elasticsearch.urls}")
    private String URLS;// 都好分割 

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        if (StringUtils.isEmpty(URLS)) {
            throw new RuntimeException("配置有问题，elasticsearch.urls为空");
        }
        String[] urls = URLS.split(",");
        HttpHost[] httpHostArr = new HttpHost[urls.length];
        for (int i=0; i<urls.length; i++) {
            String urlStr = urls[i];
            if(StringUtils.isEmpty(urlStr)) {
                continue;
            }
            httpHostArr[i] = HttpHost.create(urlStr);
        }
        
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PASSWORD));  //es账号密码（默认用户名为elastic）
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(httpHostArr)
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        }));
        return client;
    }
}
