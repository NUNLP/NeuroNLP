log4j {
    appender.stdout = "org.apache.log4j.ConsoleAppender"
    appender."stdout.layout"="org.apache.log4j.PatternLayout"
    appender."stdout.layout.ConversionPattern"="%d %5p %c{1}:%L - %m%n"
    rootLogger = "debug,stdout"
}