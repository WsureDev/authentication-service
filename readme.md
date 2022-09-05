# A simple authentication and authorization demo
```
└──src
   ├─main
   │  ├─java
   │  │  └─top
   │  │      └─wsure
   │  │          └─auth
   │  │              ├─api          // web api
   │  │              ├─cache        // memory cahe
   │  │              ├─entity       // entity (User and Role)
   │  │              ├─service      // service (interface 
   │  │              │  └─impl      // service (implements
   │  │              └─utils        // some util
   │  └─resources
   └─test
       └─java
           └─top
               └─wsure
                   └─auth
                       └─test       // unit test

```
> This is a simple authorization verification and authentication service. All data is stored in memory, using salted md5 for password storage.

### External Library
> This project use an external library javalin (https://javalin.io/) to ex http server.

> And use junit to implement unit test.

### How to start

You can use the main function of running `top.wsure.auth.AuthenticationServiceStater` to start an http service.

For the details of the call, please refer to the http test in the unit test
