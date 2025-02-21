# Compute Job Demo

## Overview
This program demostrates three different compute jobs.

The first compute job launches a single Ignite Runnable instance  
via the IgniteCompute.run() method. This task tests to see if the  
TRADE table exists. If it does not yet exist then the  
task creates the above table and populates 2 records for each of  
10 different buyers. This task executes on any one host of a multi  
host cluster.  

The second compute job launches a single Ignite Runnable instance  
via the IgniteCompute.run() method. This task tests to see if the  
BUYER_TOTAL_SPEND table exists. If it does not yet exist then the  
task creates the above table and populates 1 record for each buyer  
1 through 1024 and sets their total spend to 0.0. This task executes  
on any one host of a multi host cluster.  

The third compute job launches an Ignite Runnable compute task on  
every host of a multi host cluster via the IgniteCompute.broadcast()  
method. Each task executes a local query that reads the trade records  
for each buyer that reside on that host. Then each buyer's total spend  
is calculated and subsequently updated into the BUYER_TOTAL_SPEND  
table. This demonstrates the value of compute jobs by executing soley  
against local data on each host! Be aware that in order for this to work  
effectively you must KNOW that the data that you a summing up is located  
on one host because your table had an affinity columne specified that  
dictates that this data will reside on a designate host!

## Environment
This project have been built using Maven version 3.8.7  
This code has been compiled using Java version 11.0.20  
To build this project simply check out the project, navigate  
into the project directory and execute "mvn clean package"  

## GridGain Environment
This program is written to execute against GridGain Ultimate Edition version 8.9.14  
But higher versions of GridGain 8.x.x should also work!  

## Arguments
The program supports either no arguments or  
three defined arguments described as follows:  

No arguments:  
  The program displays a help screen that details how to execute all options.  

-help:  
  The program displays a help screen that details how to execute all options.  

-create_trade_schema:  
The program launches 1 task to create the TRADE table if it does not yet exist.      

-create_schema:  
  The program launches 1 task to create the BUYER_TOTAL_SPEND table if it  
  does not yet exist.  
  
-exec_once:  
  The program launches 1 task on each host to caclulate each buyer's current  
  total spend and to populate this into the BUYER_TOTAL_SPEND table.  
  
## Example Invocations
Example invocations are listed below (windows):  
  java -cp .\target\buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations  
  java -cp .\target\buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -help  
  java -cp .\target\buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -create_trade_schema  
  java -cp .\target\buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -create_schema  
  java -cp .\target\buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -exec_once  

Example invocations are listed below (linux):  
  java -cp ./target/buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations  
  java -cp ./target/buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -help  
  java -cp ./target/buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -create_trade_schema  
  java -cp ./target/buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -create_schema  
  java -cp ./target/buyer-total-spend-compute.jar org.gridgain.demo.BuyerTotalSpendOperations -exec_once  
