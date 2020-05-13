package com.liuxg.elasticsearch.plugin.filter;

import java.util.Map;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.ConfigurationUtils;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

public class FilterWordProcessor extends AbstractProcessor {

   public static final String TYPE = "filter_word";

   private String filterWord;

   private String field;

   public FilterWordProcessor(String tag, String filterWord, String field) {
      super(tag);
      this.filterWord = filterWord;
      this.field = field;
   }

   @Override
   public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
      IngestDocument document = ingestDocument;
      String value = document.getFieldValue(field, String.class);
      String clearedValue = value.replace(filterWord, "");
      document.setFieldValue(field, clearedValue);
      return document;
   }

   @Override
   public String getType() {
      return TYPE;
   }

   public static final class Factory implements Processor.Factory {

      @Override
      public Processor create(Map<String, Processor.Factory> registry, String processorTag,
            Map<String, Object> config) throws Exception {

         String field = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "field");
         String filterWord = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "filterWord");

         return new FilterWordProcessor(processorTag, filterWord, field);
      }
   }

}