package com.sandata.one.aggregator.documents.routes;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.StreamCache;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.converter.stream.FileInputStreamCache;
import org.apache.camel.util.ExchangeHelper;
import org.apache.camel.util.IOHelper;

public class Cloner implements Processor{

        @Override
        public void process(Exchange exchange) throws Exception {

                  if (exchange.getIn().getBody() instanceof FileInputStreamCache) {
                          //the file stream must be copied, otherwise you get errors because the stream file is removed when the parent route is finished
                         FileInputStreamCache streamCache = (FileInputStreamCache) exchange.getIn().getBody();
                        CachedOutputStream cos = new CachedOutputStream(exchange);
                             try {
                                  IOHelper.copy(streamCache, cos);
                                } finally {
                                  IOHelper.close(streamCache, cos);
                                 streamCache.reset();
                               }
                            exchange.getIn().setBody(cos.newStreamCache());
                        }

        }
    }

