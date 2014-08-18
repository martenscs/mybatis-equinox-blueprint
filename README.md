mybatis-equinox-blueprint
=========================

Sample Mybatis Blueprint project.


This is a simple example of using Aries Blueprint to instantiate a Mybatis session and do a simple query.

This example does the following:
1. Creates a DataSource definition for a Derby DB.
2. Instantiates the Database and adds a table Category to the DB and adds one tuple with the values 1,Apparel to the table.
3. Then the process queries the DB for the value id value of "1" using Mybatis.



To run the application do the following steps.
1. Clone the Git repo to your local computer using Eclipse.
2. Right Click on the Working directory in the Eclipse Git perspective and import projects.
3. When completed you should see the following projects:
a. build - the Tycho parent build project
b. mybatis-equinox-blueprint - the containing project
c. net.martenscs.osgi.derby.db.init - the project that contains the code for instantiating the DB
d. net.martenscs.osgi.derby.ds - the datasource project
e. net.martenscs.osgi.example.domain - the bussiness domain project
f. net.martenscs.osgi.mybatis - the eclipse feature for the project set
g. net.martenscs.osgi.mybatis.blueprint - the project that contains the delegate for instantiating Mybatis with blueprint
h. net.martenscs.osgi.mybatis.blueprint.example - the impl 
i. release-repo - a simple Tycho P2 repo project

Once these projects are visible open the project mybatis-equinox-blueprint and open the file mybatis.target(Eclipse target def).
In the upper right-hand corner select "Set as target Platform". Once that process has completed you should not have any visible errors in your projects.
Next right-click on the file  Mybatis Runner.launch(Eclipse launch configuration file) and select Run As --> Mybatis Runner.
The process should execute an OSGi runtime that completes successfully and outputs a word "Apparel".


