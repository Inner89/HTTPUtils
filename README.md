# HTTPUtils
Util classes to simplify Java HTTP requests.

This is to replace the dependency of Jsoup just for doing a simple task.

## Currently supported:
- [GET](https://github.com/SpongyBacon/HTTPUtils/blob/master/src/pw/sponges/httputils/GET.java)
- [POST](https://github.com/SpongyBacon/HTTPUtils/blob/master/src/pw/sponges/httputils/POST.java)

## Example code:
Here are some examples of this in use.

#### GET request:
Creating a simple GET request to google, for search results on dog food.

```java
// Setting up the request
GET get = new GET();
get.setURL("https://google.co.uk/search?q=dog%20food");
get.setSSL(true);
get.addProperty("User-Agent", "GetRequester2000");

// Requesting & getting the results
get.connect();
Result results = get.getResults();

// Using the results
int responseCode = results.getCode();
String content = results.getContent();
```
This can be simplified using the builder pattern implemented into the classes like this:
```java
Result results = new GET()
  .setURL("https://google.co.uk/search?q=dog%20food")
  .setSSL(true)
  .addProperty("User-Agent", "GetRequester2000")
  .connect()
  .getResult();
  
// Now you can use the results!
```

#### POST request:
Creating a simple POST request to hastebin with a cool message.

```java
// Setting up the request
POST post = new POST();
post.setURL("http://hastebin.com/documents");
post.setSSL(true);
post.addProperty("User-Agent", "PostRequester2000");
post.setBody("a cool message");

// Requesting & getting the results
post.connect();
Result results = post.getResults();

// Using the results
int responseCode = results.getCode();
String content = results.getContent();
```
This can be simplified using the builder pattern implemented into the classes like this:
```java
Result results = new POST()
  .setURL("https://google.co.uk/search?q=dog%20food")
  .setSSL(true)
  .addProperty("User-Agent", "PostRequester2000")
  .setBody("a cool message")
  .connect()
  .getResult();
  
// Now you can use the results!
```

## Licensing
This project is licensed under The MIT License (MIT).

![tl;dr license](http://i.imgur.com/BHIJWYh.png)
Provided by https://tldrlegal.com/license/mit-license
