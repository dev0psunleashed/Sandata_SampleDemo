package com.sandata.lab.security.auth.utils.oracle;

import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.data.model.dl.model.ApplicationModule;
import com.sandata.lab.data.model.dl.model.ApplicationSecureGroup;
import com.sandata.lab.data.model.dl.model.ApplicationSecureGroupRoleMapping;
import com.sandata.lab.data.model.dl.model.extended.ApplicationModuleExt;
import com.sandata.lab.data.model.dl.model.extended.ApplicationRoleExt;
import com.sandata.lab.security.auth.model.jpub.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataTransfomer {

    public TmpAppRoleTyp applicationRoletoTmpAppRoleTyp(ApplicationRoleExt applicationRole) throws Exception {

        BigDecimal appRoleSk = null;

        if ((applicationRole.getApplicationRoleSK() != null) && (applicationRole.getApplicationRoleSK().intValue() > 0)) {
            appRoleSk = BigDecimal.valueOf(applicationRole.getApplicationRoleSK().intValue());
        }

        BigDecimal appTenantSk = null;

        if (applicationRole.getApplicationTenantSK() != null) {
            appTenantSk = BigDecimal.valueOf(applicationRole.getApplicationTenantSK().intValue());
        }

        TmpAppSecrGrpTab tmpAppSecrGrpTab = null;

        if (applicationRole.getApplicationSecureGroups() != null) {
            int secureGroupLength = applicationRole.getApplicationSecureGroups().size();

            if (secureGroupLength > 0) {

                TmpAppSecrGrpTyp[] tmpAppSecrGrpTyps = new TmpAppSecrGrpTyp[secureGroupLength];

                int i = 0;

                for (ApplicationSecureGroup applicationSecureGroup : applicationRole.getApplicationSecureGroups()) {

                    TmpAppSecrGrpTyp tmpAppSecrGrpTyp = new TmpAppSecrGrpTyp(applicationSecureGroup.getSecureGroupName());
                    tmpAppSecrGrpTyps[i] = tmpAppSecrGrpTyp;
                    i++;
                }

                tmpAppSecrGrpTab = new TmpAppSecrGrpTab();
                tmpAppSecrGrpTab.setArray(tmpAppSecrGrpTyps);
            }
        }

        TmpAppModPrivTab tmpAppModPrivTab = null;

        int moduleLength = applicationRole.getApplicationModules().size();

        //Add submodules if there are any
        if (moduleLength > 0) {
            TmpAppModPrivTyp[] tmpAppModPrivTyps = new TmpAppModPrivTyp[moduleLength];

            int i = 0;

            for (ApplicationModuleExt applicationModule : applicationRole.getApplicationModules()) {

                //Get the module sk
                BigDecimal appModSk = null;
                if (applicationModule.getApplicationModuleSK() != null) {
                    appModSk = BigDecimal.valueOf(applicationModule.getApplicationModuleSK().intValue());
                }

                String subModuleName = null;
                BigDecimal subModSk = null;

                TmpAppModTab tmpAppModTab = null;

                if (applicationModule.getApplicationModules() != null && applicationModule.getApplicationModules().size() > 0) {

                    ArrayList<TmpAppModTyp> tmpAppModTypArrayList = new ArrayList<>();

                    TmpAppModTyp[] tmpAppModTyps = null;

                    for (ApplicationModule applicationModuleSub : applicationModule.getApplicationModules()) {

                        if (applicationModuleSub.getApplicationModuleSK() != null) {
                            subModSk = BigDecimal.valueOf(applicationModuleSub.getApplicationModuleSK().intValue());
                        }

                        subModuleName = applicationModuleSub.getModuleName();

                        TmpAppModTyp tmpAppModTyp = new TmpAppModTyp();
                        tmpAppModTyp.setAppModSk(subModSk);
                        tmpAppModTyp.setAppModParSk(appModSk);
                        tmpAppModTyp.setModName(subModuleName);

                        tmpAppModTypArrayList.add(tmpAppModTyp);
                    }

                    tmpAppModTyps = tmpAppModTypArrayList.toArray(new TmpAppModTyp[tmpAppModTypArrayList.size()]);

                    tmpAppModTab = new TmpAppModTab();
                    tmpAppModTab.setArray(tmpAppModTyps);
                }

                BigDecimal view = (applicationModule.isViewPermission()) ? BigDecimal.valueOf(1) : BigDecimal.valueOf(0);
                BigDecimal edit = (applicationModule.isEditPermission()) ? BigDecimal.valueOf(1) : BigDecimal.valueOf(0);


                TmpAppModPrivTyp tmpAppModPrivTyp = new TmpAppModPrivTyp(appTenantSk, appModSk, applicationModule.getModuleName(),
                        view, edit, tmpAppModTab);

                tmpAppModPrivTyps[i] = tmpAppModPrivTyp;

                //Increment counter
                i++;
            }

            tmpAppModPrivTab = new TmpAppModPrivTab();
            tmpAppModPrivTab.setArray(tmpAppModPrivTyps);
        }

        TmpAppRoleTyp tmpAppRoleTyp = new TmpAppRoleTyp(appRoleSk, appTenantSk,

                applicationRole.getRoleName(), BigDecimal.valueOf(0), tmpAppModPrivTab, tmpAppSecrGrpTab);

        return tmpAppRoleTyp;
    }

    public ApplicationRoleExt tmpAppRoleTypToApplicationRole(TmpAppRoleTyp tmpAppRoleTyp) throws Exception {
        ApplicationRoleExt applicationRoleExt = new ApplicationRoleExt();

        applicationRoleExt.setRoleName(tmpAppRoleTyp.getRoleName());
        applicationRoleExt.setApplicationRoleSK((tmpAppRoleTyp.getAppRoleSk().toBigInteger()));
        applicationRoleExt.setRecordUpdateTimestamp(tmpAppRoleTyp.getRecUpdateTmstp());
        applicationRoleExt.setRecordCreateTimestamp(tmpAppRoleTyp.getRecCreateTmstp());
        applicationRoleExt.setApplicationRoleSK(tmpAppRoleTyp.getAppRoleSk().toBigInteger());
        applicationRoleExt.setApplicationTenantSK(tmpAppRoleTyp.getAppTenantSk().toBigInteger());

        if (tmpAppRoleTyp.getAppModPrivList() != null && tmpAppRoleTyp.getAppModPrivList().getArray() != null) {
            int moduleArraySize = tmpAppRoleTyp.getAppModPrivList().getArray().length;

            if (moduleArraySize > 0) {

                List<ApplicationModuleExt> applicationModuleExts = new ArrayList<>();

                TmpAppModPrivTyp[] tmpAppModPrivTyps = tmpAppRoleTyp.getAppModPrivList().getArray();

                for (TmpAppModPrivTyp tmpAppModPrivTyp : tmpAppModPrivTyps) {
                    ApplicationModuleExt applicationModuleExt = new ApplicationModuleExt();


                    applicationModuleExt.setRecordCreateTimestamp(tmpAppRoleTyp.getRecCreateTmstp());
                    applicationModuleExt.setRecordUpdateTimestamp(tmpAppRoleTyp.getRecUpdateTmstp());

                    applicationModuleExt.setApplicationModuleSK(tmpAppModPrivTyp.getAppModSk().toBigInteger());
                    applicationModuleExt.setModuleName(tmpAppModPrivTyp.getModName());

                    boolean edit = tmpAppModPrivTyp.getEditInd().intValue() == 1 ? true : false;
                    boolean view = tmpAppModPrivTyp.getViewInd().intValue() == 1 ? true : false;


                    applicationModuleExt.setEditPermission(edit);
                    applicationModuleExt.setViewPermission(view);

                    TmpAppModTab tmpAppModTab = tmpAppModPrivTyp.getSubAppModList();

                    if (tmpAppModTab != null) {

                        List<ApplicationModule> applicationModules = new ArrayList<>();

                        TmpAppModTyp[] tmpAppModTyps = tmpAppModTab.getArray();

                        if (tmpAppModTyps != null && tmpAppModTyps.length > 0) {

                            for (int i = 0; i < tmpAppModTyps.length; i++) {

                                TmpAppModTyp tmpAppModTyp = tmpAppModTyps[i];

                                ApplicationModule applicationModuleSub = new ApplicationModule();
                                applicationModuleSub.setModuleName(tmpAppModTyp.getModName());
                                applicationModuleSub.setApplicationModuleParentSK(tmpAppRoleTyp.getAppRoleSk().toBigInteger());
                                applicationModuleSub.setApplicationModuleSK(tmpAppModTyp.getAppModSk().toBigInteger());
                                applicationModuleSub.setRecordCreateTimestamp(tmpAppRoleTyp.getRecCreateTmstp());
                                applicationModuleSub.setRecordUpdateTimestamp(tmpAppRoleTyp.getRecUpdateTmstp());

                                applicationModules.add(applicationModuleSub);

                                applicationModuleExt.setApplicationModules(applicationModules);
                            }
                        }
                    }

                    applicationModuleExts.add(applicationModuleExt);
                }

                applicationRoleExt.setApplicationModules(applicationModuleExts);

            }
        }


        if (tmpAppRoleTyp.getAppSecrGrpList() != null && tmpAppRoleTyp.getAppSecrGrpList().getArray() != null) {

            if (tmpAppRoleTyp.getAppSecrGrpList().getArray().length > 0) {

                List<ApplicationSecureGroup> applicationSecureGroups = new ArrayList<>();

                for (TmpAppSecrGrpTyp tmpAppSecrGrpTyp : tmpAppRoleTyp.getAppSecrGrpList().getArray()) {

                    ApplicationSecureGroup applicationSecureGroup = new ApplicationSecureGroup();

                    applicationSecureGroup.setSecureGroupName(tmpAppSecrGrpTyp.getSecrGrpName());

                    BigDecimal sk = tmpAppSecrGrpTyp.getAppSecrGrpSk();

                    if (sk != null) {

                        applicationSecureGroup.setApplicationSecureGroupSK(sk.toBigInteger());
                    }

                    applicationSecureGroup.setRecordCreateTimestamp(tmpAppSecrGrpTyp.getRecCreateTmstp());
                    applicationSecureGroup.setRecordUpdateTimestamp(tmpAppSecrGrpTyp.getRecUpdateTmstp());

                    applicationSecureGroups.add(applicationSecureGroup);
                }

                applicationRoleExt.setApplicationSecureGroups(applicationSecureGroups);
            }
        }

        return applicationRoleExt;
    }
}
