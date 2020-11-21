# Cranfield Query Parser      
Parsing queries and returning results from the [Cranfield Collection](http://ir.dcs.gla.ac.uk/resources/test_collections/cran/)     


### Compile     
```
mvn package
```

### Run          
```
java -jar target/CranfieldQueryParser-1.2.jar
```   

### Evaluate     
Note: You must run the program jar before evaluating it     
```
trec_eval-9.0.7/trec_eval data/cran/QRelsCorrectedforTRECeval results.txt
```    

### Dependencies       

1. [Maven](https://maven.apache.org/)    

    macOS installation
    ```
    brew install maven
    ```    

    Linux installation
    ```
    apt-get install maven
    ```   


2. [Lucene](https://lucene.apache.org/)  

    This automatically gets downloaded by maven during project compilation.
