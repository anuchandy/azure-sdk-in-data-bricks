package anu.databricks.simpleapp;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.apache.spark.SparkContext;

public class App {
    public static void main( String[] args ) {
        SparkContext sc =  SparkContext.getOrCreate();

        System.out.println("Hello..");

        String connectionString = "<storage-connection-string>";
        String storageContainer = "storageContainer";

        BlobClient client = new BlobClientBuilder()
                .endpoint("https://<account-name>.blob.core.windows.net/")
                .connectionString(connectionString)
                .containerName(storageContainer)
                .blobName("pending/Log.txt")
                .buildClient();

        System.out.println(client.getBlobUrl());
        //
        sc.stop();
    }
}
