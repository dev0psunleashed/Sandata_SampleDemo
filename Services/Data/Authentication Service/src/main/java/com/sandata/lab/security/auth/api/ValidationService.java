package com.sandata.lab.security.auth.api;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

import java.util.List;
import java.util.Map;

public interface ValidationService{

   boolean validate(Map<String, List<String>> headers) throws Exception;

}
