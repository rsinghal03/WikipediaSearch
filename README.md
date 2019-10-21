# WikipediaSearch
Kotlin, Dagger, Retrofit, RxJava, Stetho, RoomDatabase, Gson, Constraint layout 

I have used Kotlin as the development language and MVP as architecture pattern.

View are designed using Constraint layout and made independent of mobile size using guide line.

For every query, response is cached using room database and shown when user is offline.
Full response is cached. For custom object TypeConverter is used. Gson is used to convert object to string(json) and vice versa.
Room database query return livedata object which is observed and Ui is updated.
Stetho is used for database inspection
Executor framework is used for background processing.

Dagger is used for dependency injection.

Webview is used to load the Wikipedia web page for the selected query result item.

Back navigation is handled for fragment and webview

Note: Github history is well maintained and not more than 15 files has been commited at single instance.






