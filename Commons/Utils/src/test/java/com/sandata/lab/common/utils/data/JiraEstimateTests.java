package com.sandata.lab.common.utils.data;

import com.sandata.lab.common.utils.data.model.JiraItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class JiraEstimateTests {

    @Test
    public void should_compare_two_csv_jira_files() throws Exception {

        String georgeFilePath = getClass().getClassLoader().getResource("OhioTab.csv").getFile();
        File georgeTab = new File(georgeFilePath);

        Assert.assertNotNull(georgeTab);

        Map<String, JiraItem> jiraItemMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(georgeTab))) {

            int row = 0;
            for (String line; (line = br.readLine()) != null; row++) {

                if (row == 0)
                    continue;

                String[] fields = line.split(",");

                JiraItem jiraItem = new JiraItem();
                jiraItem.setSummary(fields[0]);
                jiraItem.setIssueKey(fields[1]);
                jiraItem.setIssueType(fields[2]);
                jiraItem.setStatus(fields[3]);
                jiraItem.setAssignee(fields[4]);
                jiraItem.setReporter(fields[5]);
                jiraItem.setFrontEndDevEstimate(fields[6]);
                jiraItem.setFrontEndDependencies(fields[7]);
                jiraItem.setMiddlewareDevEstimate(fields[8]);
                jiraItem.setMiddlewareDependencies(fields[9]);
                jiraItem.setDatabaseEstimates(fields[10]);
                jiraItem.setPriority(fields[11]);

                jiraItemMap.put(jiraItem.getIssueKey(), jiraItem);
            }

            //System.out.println(jiraItemMap.size());
        }

        String billingArFilePath = getClass().getClassLoader().getResource("MWGEOMVP.csv").getFile();
        File billingArTab = new File(billingArFilePath);

        Assert.assertNotNull(billingArTab);

        try(BufferedReader br = new BufferedReader(new FileReader(billingArTab))) {

            int row = 0;
            for (String line; (line = br.readLine()) != null; row++) {

                if (row == 0)
                    continue;

                String[] fields = line.split(",");

                if (fields.length < 4) {
                    continue;
                }

                String key = fields[1];
                if (jiraItemMap.containsKey(key)) {
                    if (fields.length >= 12) {
                        JiraItem jiraItem = (jiraItemMap.get(key));
                        jiraItem.setMiddlewareDevEstimate(fields[11]);
                        System.out.println(String.format("%s: %s", jiraItem.getIssueKey(), jiraItem.getMiddlewareDevEstimate()));
                    }
                }
            }
        }
    }
}
