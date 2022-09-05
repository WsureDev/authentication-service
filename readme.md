# A simple authentication and authorization demo
```
└─top
    └─wsure
        └─auth
            ├─api           // web api
            ├─cache         // memory cahe
            ├─entity        // entity (User and Role)
            ├─service       // service (interface 
            │  └─impl       // service (implements
            └─utils         // some util

```
This is a simple authorization verification and authentication service. All data is stored in memory, using salted md5 for password storage.

This project use an external library javalin (https://javalin.io/) to ex http server.
And use junit to implement unit test.

