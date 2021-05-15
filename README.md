# Get Pixiv Following From Html To RSS

You followed many many great Pixiv artists
and now you want to subcribe them all in your RSS readers?  
This script can help you 😉

## How to use

0. you need Java 11 properly setup
1. Go to `https://www.pixiv.net/users/<your-user-id>/following`
2. For each page, fetch the HTML source and save them in [`html`](./html) folder
3. Reformat all your HTML sources (important)
4. Run `./mvnw spring-boot:run` (for mac and linux user, you may need chmod)
5. Once finished, your OPML segment is ready in [`rss.xml`](./rss.xml)
6. Open your RSS feed reader, export your feed into OPML file
7. Learn the structure of your OPML file. Add all contents from `rss.xml`
   to proper locations of your OPML file.
8. Import your edited OPML file back into your RSS feed reader


## Some notes

- The program relies on formatted HTML source where each HTML tag on its own line.
  Then the program will try to find the line that contains both user id and user name
  - Check examples in [`html`](./html) folder
- You don't have to save the whole web page, partial HTML source works as well,
as long as it contains all artists in the source file
- There are many many online HTML formatting site available for you
- Since this is a spring-boot project, 
  you can customize the program behavior using spring-configuration's recommended 
  [way](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config-files)
  - Here is all hackable configuration -> [`application.yml`](./src/main/resources/application.yml)
- I personally tested on TTRSS, it actually works 😂
