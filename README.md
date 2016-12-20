# finagle-example
Finagle Java Example using Finagle 6.4.0

Implements a simple Echo Client & Server and HTTP Client & Server.

### Pre-requisites
- JDK 1.7/1.8
- Maven 2.x

### Build
Download all sources and build with maven. Maven will download the correct dependencies.

    $ git clone https://github.com/rahulsh1/finagle-example.git
    $ cd finagle-example
    $ mvn install
    
  This will create a jar `finagle.example-1.0-with-dependencies.jar` file will all the dependencies built it.

### Run

    $ java -jar target/finagle.example-1.0-with-dependencies.jar 
    java Main <1=HTTP, 2=Echo> <1=Client, 0=Server>

#### Echo 

    # Start Echo Server
    $ java -jar target/finagle.example-1.0-with-dependencies.jar 2 0

    # Start Echo Client
    $ java -jar target/finagle.example-1.0-with-dependencies.jar 2 1

#### HTTP 

    # Start HTTP Server
    $ java -jar target/finagle.example-1.0-with-dependencies.jar 1 0

    # Start HTTP Client
    $ java -jar target/finagle.example-1.0-with-dependencies.jar 1 1

