# Hibernate Bytecode instrumentation/enhancement

This is a sample project that shows how to enable hibernate bytecode
enhancements.

Hibernate will enhance the classes in an application’s domain
model in order to add one or more of the following capabilities:

- Lazy state initialization
- Dirtiness tracking
- Automatic bi-directional association management
- Performance optimizations

### HowTo 

There are two build files in that project that allow you to
choose between an enhanced version and a regular version:

- ``build-withEnhancement.gradle``   
- ``build-noEnhancement.gradle``

Use these build files (either by using the gradle option "-b" ir by simply
copying over the content to build.gradle) and then run the tests in the class
``AssetTest``.

You can either use containers (see the note in the testclass for more
information) for testing or a local postgresql database.

### Literature
https://docs.jboss.org/hibernate/orm/5.4/topical/html_single/bytecode/BytecodeEnhancement.html   
https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#BytecodeEnhancement   
https://dzone.com/articles/hibernate-bytecode-enhancement-association-managem   
https://dzone.com/articles/hibernate-bytecode-enhancement-dirty-tracking   