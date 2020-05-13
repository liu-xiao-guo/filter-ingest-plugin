package com.liuxg.elasticsearch.plugin.filter;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.ingest.Processor;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

public class FilterIngestPlugin extends Plugin implements IngestPlugin {

@Override
public Map<String, Processor.Factory> getProcessors(Processor.Parameters parameters) {
      Map<String, Processor.Factory> processors = new HashMap<>();
      processors.put(FilterWordProcessor.TYPE, new FilterWordProcessor.Factory());
      return processors;
   }
}