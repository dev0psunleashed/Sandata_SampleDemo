package com.sandata.lab.intake.staff.api;

import com.sandata.lab.data.model.staff.Staff;
import com.sandata.lab.intake.staff.BaseTestSupport;
import com.sandata.lab.intake.staff.impl.RestStaffIntakeService;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test the StaffIntakeServiceApi.
 * <p/>
 *
 * @author David Rutgos
 */
@RunWith(JMockit.class)
public class StaffIntakeServiceTests extends BaseTestSupport {

    private StaffIntakeService service;
    private Staff staffMember;
    private List<Staff> staffMembers;
    
    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_member_for_insert() throws Exception {

        exchange.getIn().setBody(this.staffMember);
        service.insertStaffMember(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_members_for_insert() throws Exception {

        exchange.getIn().setBody(this.staffMembers);
        service.insertStaffMembers(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_member_for_update() throws Exception {

        exchange.getIn().setBody(this.staffMember);
        service.updateStaffMember(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_members_for_update() throws Exception {

        exchange.getIn().setBody(this.staffMembers);
        service.updateStaffMembers(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_member_for_delete() throws Exception {

        exchange.getIn().setBody(this.staffMember);
        service.deleteStaffMember(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    @Test
    public void should_return_guid_in_body_when_successfully_queued_staff_members_for_delete() throws Exception {

        exchange.getIn().setBody(this.staffMembers);
        service.deleteStaffMembers(exchange);

        validateGuid((String) exchange.getIn().getBody());
    }

    private void validateGuid(String guid) {
        Assert.notNull(guid);
        Assert.isTrue(guid.length() == 36);
    }

    @Override
    protected void onSetup() throws IOException {
        this.service = new RestStaffIntakeService();
        this.staffMember = new Staff();
        staffMember.setStaffId(123456789);
        staffMember.setFirstName("JUnit");
        staffMember.setLastName("Test Case");
        staffMember.setEmail("junit@sandata.com");

        this.staffMembers = new ArrayList<>();
        staffMembers.add(this.staffMember);
    }
}
