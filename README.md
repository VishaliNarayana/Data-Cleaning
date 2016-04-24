# Data-Cleaning

README FILE FOR DATA CLEANING PROJECT PHASE 4 :

Phase III update ------------------------------

Updated tableExists() and createTable() methods. Included the location of log file while creating external hive table in order to load user information into the hive table.

ProvenanceTracker -----------------------------

main(arguments) accepts combination of arguments from user and reslts in displaying information after querying the hive table using information provided by user.

main method accepts combination of arguments from user(maximum 3 arguments), verifies for the correctness of argument(-u,-t,-d,-type) and then calls required methods for querying the data and obtaining the results.

ProvenanceTracker.sh --------------------------
 
path in git: https://github.com/VishaliNarayana/Data-Cleaning/blob/master/hadoop/hadoop-common-project/hadoop-kms/src/main/sbin/ProvenanceTracker.sh
 
path in git: https://github.com/VishaliNarayana/Data-Cleaning/blob/master/hadoop/hadoop-hdfs-project/hadoop-hdfs-httpfs/src/main/sbin/ProvenanceTracker.sh

This accepts the arguments from command line given by user(maximum 3 arguments) and then calls java program(ProvenanceTracker) passing the obtained results and giving classpath where java program exists.

Inclusions:

datasets path: https://github.com/VishaliNarayana/Data-Cleaning/blob/master/hadoop/my_datasets/

jar: https://github.com/VishaliNarayana/Data-Cleaning/blob/master/DataCleaning.jar

Above jar files includes all the other jar files reuired to run modified hadoop source code.
