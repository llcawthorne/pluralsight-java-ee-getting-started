# Pluralsight - Java EE: Getting Started

This is just where I'm keeping my work as I go through the Pluralsight course 
Java EE: Getting Started.  I've completed the course, but I didn't commit the
code where it used the Swagger Codegen to create the service to access the
actual REST API.  When I did as shown in the video, swagger codegen generated
code that wouldn't compile when I tried to do `ng serve`.  The first time I
did it, the code compiled, but the service wouldn't inject, but with the 
current version of swagger codegen it isn't even getting past compilation.

The REST service in bookstore-back works fine and generates swagger.  It
runs in Wildfly 10.  It won't run in Wildfly 14, because they updated the
REST API in some way that broke it.  But if you want a working REST API for
Wildfly 10, it's there.  The Angular front-end in bookstore-front does what
it does with the mock data.  It doesn't actually make any rest calls, since
there is no service.  I might go back later and try adding a service by
hand.  (Angular Tutorial)[https://angular.io/tutorial] shows you how to 
make calls by hand with the HttpClient.

I should probably make one pluralsight repo to contain all my coursework
from PluralSight courses.  But my trial is only a month, and in general,
I found the courses to be overly dated.  The EE stuff didn't really
cover anything I didn't understand fairly well either.  I ended up
watching a lot more LinkedIn Learning courses, which were more up
to date and had a good variety on Spring topics.  So this will
probably be my only PluralSight course repo.
