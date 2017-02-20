package com.sandata.lab.exports.staff.impl;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.data.model.dl.model.*;
import com.sandata.lab.data.model.dl.model.Staff;
import com.sandata.lab.exports.staff.model.*;
import org.apache.camel.Exchange;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StaffTransformer {

    /**
     * @param exchange
     */
    public void staffToPayProStaff(Exchange exchange) {

        try {

            HashMap<String, String> bsnInfo = (HashMap) exchange.getIn().getHeader("bsnEntInfo");
            String location = bsnInfo.get("vendorBEID");
            String beState = bsnInfo.get("bsnEntState");

            List<Staff> staffList = (ArrayList<Staff>) exchange.getIn().getBody();

            List<com.sandata.lab.exports.staff.model.Staff> staffs = new ArrayList<>();

            for (Staff staff : staffList) {

                com.sandata.lab.exports.staff.model.Staff staffExport = new com.sandata.lab.exports.staff.model.Staff();

                staffExport.setFirstName(staff.getStaffFirstName());
                staffExport.setMiddleName(staff.getStaffMiddleName());
                staffExport.setLastName(staff.getStaffLastName());

                List<StaffContactAddress> contactAddresses = staff.getStaffContactAddress();

                if (contactAddresses != null && contactAddresses.size() > 0) {

                    for (StaffContactAddress staffContactAddress : contactAddresses) {


                        staffExport.setAddress(staffContactAddress.getStaffAddressLine1());
                        staffExport.setAddress2(staffContactAddress.getStaffAddressLine2());

                        staffExport.setCity(staffContactAddress.getStaffCity());

                        StateCode stateCode = staffContactAddress.getStaffState();

                        if (stateCode != null) {
                            staffExport.setState(staffContactAddress.getStaffState().value());
                        }

                        staffExport.setZipCode(staffContactAddress.getStaffPostalCode());

                        if (staffContactAddress.getAddressPriorityName() != null && staffContactAddress.getAddressPriorityName().value().equals(AddressPriorityName.PRIMARY.value())) {
                            break;
                        }

                    }
                }

                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

                Date hireDate = staff.getStaffHireDate();

                if (hireDate != null) {

                    staffExport.setHireDate(format.format(hireDate));
                }

                Date birthDate = staff.getStaffDateOfBirth();

                if (birthDate != null) {

                    staffExport.setBirthDate(format.format(birthDate));
                }

                Date termDate = staff.getStaffTerminationDate();

                if (termDate != null) {

                    staffExport.setTermDate(format.format(termDate));
                }

                if (staff.getStaffPositionName() != null) {

                    staffExport.setPosition(staff.getStaffPositionName().value());
                }

                MaritalStatusName maritalStatusName = staff.getStaffMaritalStatusName();

                if (maritalStatusName != null) {

                    staffExport.setMaritalStatus(staff.getStaffMaritalStatusName().value());
                }

                staffExport.setCc2(beState);
                staffExport.setCc3(location);

                staffs.add(staffExport);

            }

            Transaction transaction = new Transaction();
            transaction.setStaffs(staffs);



            String company = bsnInfo.get("vendorParentBEID");
            transaction.setCompany(company);

            PayPro payPro = new PayPro();
            payPro.setTransaction(transaction);

            exchange.getIn().setHeader("vendorParentBEID", company);
            exchange.getIn().setBody(payPro);


        } catch (Exception ex) {

            ex.printStackTrace();
            throw new SandataRuntimeException(ex.getMessage());
        }
    }
}
