# azure-sdk-in-data-bricks
Sample that uses Azure storage SDK v12 in azure-data-bricks


Azure Data bricks vendor specific Jackson versions that collide with Jackson version that Azure Storage SDK v12 uses. Due to this a spark job based on a Java application jar file that uses storage v12 will fail with NoClassDefFoundError.
To overcome this the Java application jar has to shade the dependencies. 

This sample demonstrate how to build such a shaded application that can run on Azure Data bricks.

Follow below steps to run the sample in Azure Data bricks:

1. Clone the repo 
2. Update the `App.java` to have a valid blob storage account connection string, container name and account name
3. Run mvn package to generate a shaded jar which produces `simple-app-1.0-SNAPSHOT-shaded.jar` in target directory
4. From azure portal navigate to Azure Data bricks portal (assuming you already has a ADB account, e.g. ADB portal: https://westus2.azuredatabricks.net/)
5. Create a cluster based on `Runtime: 6.1 (Scala 2.11, Spark 2.4.4)`
6. After cluster is ready, create a Job -> 
    1. Provide name for job 
    2. Choose task as "Set JAR", this will bring up option to upload the jar, point it to `simple-app-1.0-SNAPSHOT-shaded.jar` 
    3. Enter Main class name as: `anu.databricks.simpleapp.App`
7. Once the job is done, click on Logs and under standard output text box, you should see:

```
		Hello..
		https://<storage-account-name>.blob.core.windows.net/storageContainer/pending%2FLog.txt
```



