package com.sandata.lab.data.model.mapping.beanio;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sandata.lab.data.model.schedule.HCPlusSchedule;
import com.sandata.lab.data.model.schedule.Schedule;
import org.beanio.*;

import com.google.gson.Gson;

import org.beanio.types.BooleanTypeHandler;
import org.joda.time.DateTime;
import org.junit.Test;

public class ScheduleBeanIOTest {

    @Test
    public void testHandleSchedule() {
        //beanio mapping file
        String mappingFile = "com/sandata/lab/data/model/mapping/beanio/scheduleBeanIOMapping.xml";
//        String mappingFile = "scheduleBeanIOMapping.xml";

        //scheduleFile will be found in classpath : src/main/resources
        String scheduleFile = "data/schedule.csv";
        //The stream from the beanio mapping file.
        String streamName = "scheduleFile";

        BeanReader beanReader = null;
        Reader reader = null;
        StreamFactory factory = null;
        InputStream in = null;
        Gson gson = new Gson();
        try {


            System.out.println("**** RESULT FOR " + scheduleFile + " *******");

            //create stream factory
            factory = StreamFactory.newInstance();
            //load setting file
            in = this.getClass().getClassLoader().getResourceAsStream(mappingFile);


            //get reader of scheduleFile
            reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(scheduleFile));

            factory.load(in);

            beanReader = factory.createReader(streamName, reader);

            beanReader.setErrorHandler(new BeanReaderErrorHandlerSupport() {
                public void invalidRecord(InvalidRecordException ex ) throws Exception {
                    //if object is mapped to a record group,
                    //the exception may contain more than one record
                    for (int i = 0; i < ex.getRecordCount();i++) {
                        System.out.println(ex.getRecordContext(i).getRecordText());
                        Map<String, Collection<String>> m = ex.getRecordContext(i).getFieldErrors();
                        Set set = m.entrySet();
                        for(Object o : set)
                        {
                            System.out.println(o);
                        }


                    }
                }
            });
            Schedule record = null;

            while((record = (Schedule) beanReader.read()) != null) {
                System.out.println(beanReader.getRecordName() + ": " + gson.toJson(record));
            }


        }
        catch(BeanIOConfigurationException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
                if(beanReader != null) {
                    beanReader.close();
                }
                reader.close();
            }catch( IOException e) {
                e.printStackTrace();
            }
        }

    }
}

