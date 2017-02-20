/*
 * Copyright 2012 Red Hat
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.sandata.lab.rules.exceptions.drools;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.FireUntilHaltCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to help create wrapper Drools Expert Command for use with
 * org.drools/drools-camel component.
 */
public class DroolsCommandHelper {

	private static Logger logger = LoggerFactory.getLogger(DroolsCommandHelper.class);
    public void insertAndFireAll(Exchange exchange) {
        final Message in = exchange.getIn();
        final Object body = in.getBody();
        
        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        final List<GenericCommand<?>> commands = command.getCommands();
        logger.info("count of commands was " + Integer.toString(commands.size()));
        commands.add(new InsertObjectCommand(body, "obj1"));
        commands.add(new FireAllRulesCommand());

        in.setBody(command);
    }
    
    public void FireAll(Exchange exchange) {
        final Message in = exchange.getIn();
        final Object body = in.getBody();

        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        command.setLookup("ksessioncallMatching1");
        final List<GenericCommand<?>> commands = command.getCommands();
        logger.info("count of commands was " + Integer.toString(commands.size()));
        //commands.add(new InsertObjectCommand(body, "obj1"));
        commands.add(new FireAllRulesCommand());

        in.setBody(command);
    }
    public void FireUntilHalt(Exchange exchange) {
        final Message in = exchange.getIn();
        final Object body = in.getBody();

        BatchExecutionCommandImpl command = new BatchExecutionCommandImpl();
        final List<GenericCommand<?>> commands = command.getCommands();
        //commands.add(new InsertObjectCommand(body, "obj1"));
        commands.add(new FireUntilHaltCommand());

        in.setBody(command);
    }

}
