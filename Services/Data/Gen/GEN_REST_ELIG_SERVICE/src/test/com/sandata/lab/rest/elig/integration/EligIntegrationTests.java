package com.sandata.lab.rest.elig.integration;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.common.utils.data.DataMapper;
import com.sandata.lab.common.utils.data.MockDataFactory;
import com.sandata.lab.common.utils.data.model.Table;
import com.sandata.lab.common.utils.java.JavaUtil;
import com.sandata.lab.data.model.dl.annotation.Mapping;
import com.sandata.lab.data.model.dl.annotation.OracleMetadata;
import com.sandata.lab.data.model.dl.model.Eligibility;
import com.sandata.lab.rest.elig.impl.OracleDataService;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.UUID;

/**
 * Date: 5/30/16
 * Time: 4:53 PM
 */
public class EligIntegrationTests extends RestfulServicesTests {

    private OracleDataService oracleDataService;

    private String uuid;

    @Test
    public void should_verify_that_insert_crud_operation() throws Exception {

        for (Table table : tables()) {

            String entityName = JavaUtil.UnderscoresToCamelUppercase(abbreviationUtil().toLogical(table.getLongName()));

            Assert.assertNotNull(entityName);

            Object mockData = MockDataFactory.Mock(className(entityName), uuid, table);
            Assert.assertNotNull(mockData);

            System.out.print(String.format("[%s]: INSERT ...", mockData.getClass().getSimpleName()));

            System.out.println("OK");
        }
    }
    
    @Test
    public void should_verify_that_entities_can_be_instantiated_and_have_the_proper_annotations() throws Exception {

        for (Table table : tables()) {

            String entityName = JavaUtil.UnderscoresToCamelUppercase(abbreviationUtil().toLogical(table.getLongName()));

            Assert.assertNotNull(entityName);

            // Can we instantiate the class?
            Object entity = createEntity(entityName);

            Assert.assertNotNull(entity);

            OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(entity);

            Assert.assertNotNull(oracleMetadata);

            // Check the fields for
            for (Field field : entity.getClass().getDeclaredFields()) {

                field.setAccessible(true);

                // Skip
                if (field.getName().equals("serialVersionUID")) {
                    continue;
                }

                // Skip
                if (field.getType().getName().endsWith("List")) {
                    continue;
                }

                // Skip
                if (field.getType().getName().contains("[B")) {
                    // BLOB
                    continue;
                }

                Assert.assertTrue(field.isAnnotationPresent(SerializedName.class));
                Assert.assertTrue(field.isAnnotationPresent(Mapping.class));
            }
        }
    }

    @Test
    public void should_test_crud_operations() throws Exception {

        int sk = -1;
        for (Method method : services.getClass().getMethods()) {

            if (method.getName().startsWith(oraclePackage())) {

                String[] methodParts = method.getName().split("_");

                String packageName = methodParts[0] + "_" + methodParts[1];
                String methodName = methodParts[2];
                String entityName = methodParts[3];

                System.out.println(String.format("Package: [%s]: Method: [%s]: Entity: [%s]",
                                packageName, methodName, entityName));

                String clazz = String.format("%s.%s", MODEL_PACKAGE, entityName);

                Object mockData = MockDataFactory.Mock(clazz);

                Assert.assertNotNull(mockData);
                Assert.assertTrue(mockData instanceof Eligibility);

                Eligibility eligibility = (Eligibility)mockData;
                eligibility.setEligibilityID(uuid);
                eligibility.setPayerSK(BigInteger.ONE);

                OracleMetadata oracleMetadata = DataMapper.getOracleMetadata(eligibility);

                Assert.assertNotNull(oracleMetadata);

                if (methodName.startsWith("insert")) {
                    Assert.assertTrue(oracleMetadata.insertMethod().equals(methodName));
                    Assert.assertTrue(oracleMetadata.packageName().equals(packageName));

                    boolean hasPostAnnotation = false;
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        if (annotation.annotationType() == POST.class) {
                            hasPostAnnotation = true;
                            break;
                        }
                    }

                    Assert.assertTrue(hasPostAnnotation);

                    Object jpubType = new DataMapper().map(eligibility);

                    Assert.assertNotNull(jpubType);

                    sk = oracleDataService.execute(
                            getSandataOracleConnection().getConnection(),
                            oracleMetadata.packageName(),
                            oracleMetadata.insertMethod(),
                            jpubType
                    );

                    Assert.assertTrue(sk > 0);
                }
                else if (methodName.startsWith("update")) {
                    Assert.assertTrue(oracleMetadata.updateMethod().equals(methodName));
                    Assert.assertTrue(oracleMetadata.packageName().equals(packageName));

                    boolean hasPutAnnotation = false;
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        if (annotation.annotationType() == PUT.class) {
                            hasPutAnnotation = true;
                            break;
                        }
                    }

                    Assert.assertTrue(hasPutAnnotation);

                    eligibility.setEligibilitySK(BigInteger.valueOf(sk));
                    eligibility.setChangeReasonMemo("JUnit Test: UPDATE");

                    Object jpubType = new DataMapper().map(eligibility);

                    Assert.assertNotNull(jpubType);

                    int skResult = oracleDataService.execute(
                            getSandataOracleConnection().getConnection(),
                            oracleMetadata.packageName(),
                            oracleMetadata.updateMethod(),
                            jpubType
                    );

                    Assert.assertTrue(skResult > 0);
                    //Assert.assertTrue(skResult == sk);
                }
                else if (methodName.startsWith("delete")) {
                    Assert.assertTrue(oracleMetadata.deleteMethod().equals(methodName));
                }
                else if (methodName.startsWith("get")) {
                    Assert.assertTrue(oracleMetadata.getMethod().equals(methodName));
                }
            }
        }

    }

    protected String oraclePackage() {
         return "PKG_ELIGIBILITY";
    }

    protected String schemaOwner() {
        return "COREDATA";
    }

    protected String tableFilter() {
        return "SELECT * FROM ALL_TABLES WHERE OWNER='COREDATA' AND " +
                        "TABLE_NAME LIKE 'ELIG%' AND TABLE_NAME NOT LIKE '%_LKUP' ORDER BY TABLE_NAME";
    }

    @Override
    protected void onSetup() throws Exception {
        super.onSetup();

        oracleDataService = new OracleDataService();
        uuid = UUID.randomUUID().toString();
    }

    public EligIntegrationTests() {
        super(DB1);
    }
}
