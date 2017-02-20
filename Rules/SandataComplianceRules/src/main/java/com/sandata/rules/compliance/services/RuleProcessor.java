package com.sandata.rules.compliance.services;

import com.sandata.rules.compliance.domain.StaffFact;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jasons on 9/9/2016.
 */
public class RuleProcessor {

    private KieContainer kieContainer = RuleProcessor.Factory.get();
    private static Logger logger = LoggerFactory.getLogger(RuleProcessor.class);

    private static class Factory {
        private static KieContainer kContainer;

        static {
            try {
                // if the local maven repository is not in the default ${user.home}/.m2
                // need to provide the custom settings.xml
                // pass property value
                // -Dkie.maven.settings.custom={custom.settings.location.full.path}

                KieServices kServices = KieServices.Factory.get();
                ReleaseId releaseId = kServices.newReleaseId("com.sandata.lab.rules.compliance", "sandata-compliance-rules", "1.0-SNAPSHOT");

                //OR -- add maven dependency to rules to package locally and read from classpath
                //kContainer = kServices.newKieContainer(releaseId, Factory.class.getClassLoader());
                kContainer = kServices.newKieContainer(releaseId);
                KieScanner kScanner = kServices.newKieScanner(kContainer);

                // Start the KieScanner polling the maven repository every 10 seconds
                kScanner.start(10000L);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("{}", e);
            }
        }

        public static KieContainer get() {
            return kContainer;
        }
    }

    private static final String AGENDA_COMPLIANCE_SETUP = "compliance-setup";
    private static final String AGENDA_COMPLIANCE_RULES = "compliance-rules";
    private static final String AGENDA_COMPLIANCE_COLLECT = "compliance-collect";

    private AgendaListener agendaListener = new AgendaListener();
    private RuleListener ruleListener = new RuleListener();

    public KieSession createNewSession(boolean addListeners) {
        KieSession kieSession = kieContainer.newKieSession();

        if (addListeners) {
            kieSession.addEventListener(agendaListener);
            kieSession.addEventListener(ruleListener);
        }

        return kieSession;
    }

    public List<StaffFact> fireAllRules(List list) {

        List<StaffFact> output = new ArrayList<StaffFact>();
        KieSession kieSession = createNewSession(true);

        for (Object object : list) {
            kieSession.insert(object);
        }

        logger.info("Fire {}", AGENDA_COMPLIANCE_SETUP);
        kieSession.getAgenda().getAgendaGroup(AGENDA_COMPLIANCE_SETUP).setFocus();
        kieSession.fireAllRules();

        logger.info("Fire {}", AGENDA_COMPLIANCE_RULES);
        kieSession.getAgenda().getAgendaGroup(AGENDA_COMPLIANCE_RULES).setFocus();
        kieSession.fireAllRules();

        logger.info("Fire {}", AGENDA_COMPLIANCE_COLLECT);
        kieSession.getAgenda().getAgendaGroup(AGENDA_COMPLIANCE_COLLECT).setFocus();
        kieSession.fireAllRules();

        Collection<?> results = kieSession.getObjects(new ClassObjectFilter(StaffFact.class));

        if (results != null) {
            for (Object result : results) {
                output.add((StaffFact) result);
            }
        }

        return output;
    }
}
