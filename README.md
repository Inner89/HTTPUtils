# HTTPUtils
Util classes to simplify Java HTTP requests.

This is to replace the dependency of Jsoup just for doing a simple task.

### Currently supported:
- [GET](https://github.com/SpongyBacon/HTTPUtils/blob/master/src/pw/sponges/httputils/GET.java)
- [POST](https://github.com/SpongyBacon/HTTPUtils/blob/master/src/pw/sponges/httputils/POST.java)

### Example code:
Here are some examples of this in use.

#### GET request:
```java
// Setting up the request
GET get = new Get();
get.setUrl("https://google.co.uk/search?q=dog%20food");
get.setSsl(true);
get.addProperty("User-Agent", "GetRequester2000");

// Requesting & getting the results
get.connect();
GETResult results = get.getResults();

// Using the results
int responseCode = results.getCode();
String content = results.getContent();
```
This can be simplified using the builder pattern implemented into the classes like this:
```java
GETResult results = new GET()
  .setUrl("https://google.co.uk/search?q=dog%20food")
  .setSsl(true)
  .addProperty("User-Agent", "GetRequester2000")
  .connect()
  .getResult();
  
// Now you can use the results!
```
