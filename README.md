## Application for find file difference

For comparing data with sensitive data, we should try to avoid using any 3rd party tool. As we don’t are they using our data or not, a risk of sensitive data being leaked.

However, something we have to do comparison related to some sensitive data(eg: comparing log / request in production), to solve this problem, we can create a tool by ourselves.

To give you some more idea, you may reference to https://www.diffchecker.com/text-compare/

### Requirement:

1. Read two files

2. Compare two files, annotate them if they different

### Roadmap
1. Read two files into the application

2. Find is their any different between lines
 
3. Annotate word that is/are different

4. (Optional) Create UI for using this application

### Expected outcome
Original file:
```
I like Java a lot.
platform platform implement platform platform
This goes great with server.
Java is oops based.
```

Updated file:
```
I like Javascript very very much.
platform platform platform platform plank plank platform platform
Browser loves it.
Javascript is procedural based.
```

Comparison result(`||` is separator between two different version, just for better display):
```
I like [Java a lot.] || I like {Javascript very very much.}
platform platform [implement] platform [platform] || platform platform {platform} platform {plank plank platform platform}
[This goes great with server.] || {Browser loves it.}
[Java] is [oops] based. || {Javascript} is {procedural} based.
```


