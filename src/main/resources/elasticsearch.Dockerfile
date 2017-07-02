FROM docker.elastic.co/elasticsearch/elasticsearch:5.4.3

EXPOSE 9200
EXPOSE 9300

ENV cluster.name=microservice-cluster
ENV bootstrap.memory_lock=true
ENV "ES_JAVA_OPTS=-Xms512m -Xmx512m"

COPY elasticsearch-logging.yml /usr/share/elasticsearch/config/
COPY elasticsearch.yml /usr/share/elasticsearch/config/

USER elasticsearch

ENV PATH=$PATH:/usr/share/elasticsearch/bin

CMD ["elasticsearch"]

