package com.sandata.lab.tools.oracle.mapper;

import com.sandata.lab.common.utils.data.AbbreviationUtil;
import com.sandata.lab.common.utils.java.JavaUtil;
import com.sandata.lab.common.utils.string.StringUtil;
import com.sandata.lab.tools.oracle.util.FileUtil;
import com.sandata.lab.tools.oracle.util.TypeUtil;

import java.io.File;

/**
 * Date: 9/5/15
 * Time: 6:22 AM
 */

public class AbbreviationMapper extends ModelMapper {

    public static void main(String[] args) throws Exception {
        AbbreviationUtil abbreviationUtil = new AbbreviationUtil(AbbreviationUtil.COREDATA_TYPE);
        AbbreviationMapper.getInstance().print(abbreviationUtil);
    }

    public void print(AbbreviationUtil abbreviationUtil) {

        int count = 0;
        for (String filePath : getTargetFiles()) {
            String modelFileName = FileUtil.RemoveFileExtension(new File(filePath), ".java").trim();

            if (modelFileName.startsWith("Adapter")) {
                continue;
            }

            if (!modelFileName.startsWith("Patient")
                    && !modelFileName.startsWith("Staff")) {
                continue;
            }

            modelFileName = StringUtil.SplitByCapital(modelFileName, "_");

            String physicalModel = abbreviationUtil.toPhysical(modelFileName);
            String typeModelName = TypeUtil.ToType(physicalModel);
            String logicalModel = abbreviationUtil.toLogical(physicalModel);
            String logicalClass = JavaUtil.UnderscoresToCamelUppercase(logicalModel);

            System.out.println(String.format("[%d] %s --> [%s][%s] --> [%s][%s]",
                    ++count, modelFileName,
                    physicalModel,
                    typeModelName,
                    logicalModel,
                    logicalClass));
        }
    }

    private AbbreviationMapper() throws Exception {
    }

    public static AbbreviationMapper getInstance() throws Exception {
        return new AbbreviationMapper();
    }

    @Override
    protected String modelFileName() {
        return null;
    }

    @Override
    protected String normalizedFileName() {
        return null;
    }
}
