set ORACLE_HOME=C:\app\Oracle\product\11.2.0\client_1
set ORA_JAR_HOME=H:\projects\Sandata\LAB\dev\george.java\Commons\OracleCommons\src\main\resources\lib

%ORACLE_HOME%\BIN\loadjava.bat -u %1/%2@%3:%4:%5 -force -v -r -t -debug -verbose %ORA_JAR_HOME%\sandata-oracle-commons.jar
