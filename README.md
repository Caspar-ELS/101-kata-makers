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
 
3. Annotate string that is/are different

4. (Optional) Create UI for using this application 


